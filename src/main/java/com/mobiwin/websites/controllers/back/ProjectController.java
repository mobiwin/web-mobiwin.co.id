package com.mobiwin.websites.controllers.back;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.OurProjectModel;
import com.mobiwin.websites.services.OurProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProjectController {

    @Autowired
    OurProjectService ourProjectService;

    @RequestMapping(value = "/admin/project", method = RequestMethod.GET)
    public String project(Model model,HttpSession sessi) {
        if (sessi.getAttribute("id_session") != null) {
            model.addAttribute("title","Project / Portofolio");
            List<OurProjectModel> getData = ourProjectService.listAll();
            model.addAttribute("project", getData);
            return "public/cms/admin/pages/project/project";
        }else{
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/project/new", method = RequestMethod.GET)
    public String projectNew(Model model) {
        model.addAttribute("title","New Project");
        return "public/cms/admin/pages/project/new";
    }

    @RequestMapping(value = "/admin/project/save", method = RequestMethod.POST)
    public String projectSave(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam(value = "project_title", required = false) String title,
            @RequestParam(value = "kind", required = false) String kind,
            @RequestParam(value = "client", required = false) String client,
            @RequestParam(value = "technology", required = false) String technology,
            @RequestParam(value = "preview_path") MultipartFile preview_path) {

        if (preview_path.isEmpty()) {
            System.out.println("File kosong");
        } else {

            String exten = preview_path.getContentType().toString();
            String ext = "";
            switch (exten) {
                case "image/png":
                    ext = "png";
                    break;

                case "image/jpeg":
                    ext = "jpg";
                    break;

                case "image/jpg":
                    ext = "jpg";
                    break;
                default:
                    ext = "";
                    break;
            }

            if (ext.isEmpty()) {
                publicData.addAttribute("errmsg", "File kosong");
            } else {

                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date tanggal = new Date();
                    String random = simpleDateFormat.format(tanggal).toString();

                    // MKDIR TEMP
                    if (!Files.exists(Paths.get("src/main/resources/static/upload/temp/"))) {
                        Files.createDirectories(Paths.get("src/main/resources/static/upload/temp/"));
                    }

                    // MKDIR PATH
                    if (!Files.exists(Paths.get("src/main/resources/static/upload/project/"))) {
                        Files.createDirectories(Paths.get("src/main/resources/static/upload/project/"));
                    }

                    // UPLOAD
                    byte[] fileBytes = preview_path.getBytes();
                    title = title.replaceAll("[^a-zA-Z0-9]", "_");
                    String uploadPath = "src/main/resources/static/upload/temp/"+ title + "_" + random + "." + ext;
                    

                    // KALAU GAK MAU PAKAI COMRESS, AMBIL VARIABEL uploadPath

                    // WRITE FILE I/O
                    Files.write(Paths.get(uploadPath), fileBytes);

                    // COMRESS IMAGE
                    File imageFile = new File(uploadPath);

                    String uploadCompressPath = "src/main/resources/static/upload/project/"+ title + "_" + random + "."
                            + ext;
                    File compressedImageFile = new File(uploadCompressPath);

                    // SET INPUT OUTPUT IMAGE
                    InputStream inputStream = new FileInputStream(imageFile);
                    OutputStream outputStream = new FileOutputStream(compressedImageFile);

                    float imageQuality = 0.3f;

                    // TULIS BUFFER IMAGE
                    BufferedImage bufferedImage = ImageIO.read(inputStream);

                    // TULIS DAN CONVERT KE JPG
                    Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg");

                    if (!imageWriters.hasNext()) {
                        publicData.addAttribute("errmsg", "imageWriters.hasNext");
                    }

                    ImageWriter imageWriter = (ImageWriter) imageWriters.next();
                    ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
                    imageWriter.setOutput(imageOutputStream);

                    ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

                    // COMPRESS IMAGE
                    imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    imageWriteParam.setCompressionQuality(imageQuality);

                    // MEMBUAT IMAGE BARU
                    imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);

                    // TUTUP SEMUA STREAM
                    inputStream.close();
                    outputStream.close();
                    imageOutputStream.close();
                    imageWriter.dispose();
                    // COMPRESS SELESAI


                    // INIT PATH
                    // String fixTempPath = "/temp/" + namaKaryawanTxt + "_" + random + "." + ext;
                    String fixRealPath = "/project/"+ title + "_" + random + "." + ext;

                    
                    // FINAL, namaKaryawanTxt
                    // FINAL, positionTxt
                    // FINAL, bioTxt
                    // FINAL, jika tidak mau pakai Compress pakai uploadPath untuk path image
                    // FINAL, jika mau pakai Compress pakai uploadCompressPath untuk path image

                    // Membuat Object Models Team
                    OurProjectModel ourProjectModel = new OurProjectModel();
                    ourProjectModel.setPreviewPath(fixRealPath);
                    ourProjectModel.setProjectTitle(title);
                    ourProjectModel.setKind(kind);
                    ourProjectModel.setClient(client);
                    ourProjectModel.setTechnology(technology);

                    // SAVE TO DATABASE WITH MODELS OBJECT DATA
                    ourProjectService.saveProject(ourProjectModel);
                    publicData.addAttribute("sucmsg", "Upload Berhasil");
                } catch (Exception e) {
                    publicData.addAttribute("errmsg", e.getMessage());
                }
            }
        }

        return "public/cms/admin/pages/project/new";
    }

    @RequestMapping(value = "/admin/project/edit/{id}", method = RequestMethod.GET)
    public String projectEdit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("title","Edit Project");
        OurProjectModel ourProjectModel = ourProjectService.findOne(id);
        model.addAttribute("project", ourProjectModel);
        model.addAttribute("projects", ourProjectService.listAll());
        return "public/cms/admin/pages/project/edit";
    }

    @RequestMapping(value = "/admin/project/update/{id}", method = RequestMethod.POST, consumes = {
        "multipart/form-data" })
    public String projectUpdate(
        @RequestParam("id") long id,
        @RequestParam(value = "project_title", required = false) String title,
        @RequestParam(value = "kind", required = false) String kind,
        @RequestParam(value = "client", required = false) String client,
        @RequestParam(value = "technology", required = false) String technology,
        @RequestParam(value = "preview_path") MultipartFile preview_path,RedirectAttributes attributes, Model model) {
        String exten = preview_path.getContentType().toString();
        String ext = "";
            switch (exten) {
                case "image/png":
                    ext = "png";
                    break;

                case "image/jpeg":
                    ext = "jpg";
                    break;

                case "image/jpg":
                    ext = "jpg";
                    break;
                default:
                    ext = "";
                    break;
            }
        if (preview_path.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            title = title.replaceAll("[^a-zA-Z0-9]", "_");
            ourProjectService.projectUpdateWithOutImg(id,title,kind,client,technology);
            return "redirect:/admin/project";
        }
        // String fileName = StringUtils.cleanPath(carouselImage.getOriginalFilename());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date tanggal = new Date();
        String random = simpleDateFormat.format(tanggal).toString();
        title = title.replaceAll("[^a-zA-Z0-9]", "_");
        String nameImg = "/project/"+ title + "_" + random + "." + ext;
        try {
            Path path = Paths.get("src/main/resources/static/upload/project/" + title + "_" + random + "." + ext);
            Files.copy(preview_path.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            ourProjectService.projectUpdate(id,title,kind,client,technology,nameImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", "You successfully uploaded " + random + "." + ext + '!');

        return "redirect:/admin/project";
    }

    @RequestMapping(value = "/admin/project/delete/{id}", method = RequestMethod.GET)
    public String sliderDelete(@PathVariable("id") Integer id,
     Model model) {
        OurProjectModel ourProjectModel = ourProjectService.findOne(id);
        Path path = Paths.get("src/main/resources/static/upload/" + ourProjectModel.getPreviewPath());
         try{
            Files.deleteIfExists(path);
            ourProjectService.delete(id);
            model.addAttribute("projects", ourProjectService.listAll());
         }catch(IOException io){
            System.out.printf("No such file or directory: %s\n", path);
         }
        return "redirect:/admin/project";
    }
}
