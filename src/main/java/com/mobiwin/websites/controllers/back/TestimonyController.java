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

import com.mobiwin.websites.models.TestimonyModel;
import com.mobiwin.websites.services.TestimonyService;

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
public class TestimonyController {

    @Autowired
    TestimonyService testimonyService;

    @RequestMapping(value = "/admin/testimony", method = RequestMethod.GET)
    public String testimony(Model model,HttpSession sessi) {
        if (sessi.getAttribute("id_session") != null) {
            model.addAttribute("title","Testimonials");
            List<TestimonyModel> getData = testimonyService.listAll();
            model.addAttribute("testimony", getData);
            return "public/cms/admin/pages/testimony/testimony";
        }else{
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/testimony/new", method = RequestMethod.GET)
    public String testimonyNew(Model model) {
        model.addAttribute("title","New testimonials");
        return "public/cms/admin/pages/testimony/new";
    }

    @RequestMapping(value = "/admin/testimony/save", method = RequestMethod.POST)
    public String testimonySave(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam(value = "name_user", required = false) String name_user,
            @RequestParam(value = "company", required = false) String company,
            @RequestParam(value = "testimony_text", required = false) String testimony_text,
            @RequestParam(value = "user_ava_path") MultipartFile user_ava_path) {

        if (user_ava_path.isEmpty()) {
            System.out.println("File kosong");
        } else {

            String exten = user_ava_path.getContentType().toString();
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
                System.out.println("Ext kosong");
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
                    if (!Files.exists(Paths.get("src/main/resources/static/upload/testimony/"))) {
                        Files.createDirectories(Paths.get("src/main/resources/static/upload/testimony/"));
                    }

                    // UPLOAD
                    byte[] fileBytes = user_ava_path.getBytes();
                    name_user = name_user.replaceAll("[^a-zA-Z0-9]", "_");
                    String uploadPath = "src/main/resources/static/upload/temp/"+ name_user + "_" + random + "." + ext;
                    

                    // KALAU GAK MAU PAKAI COMRESS, AMBIL VARIABEL uploadPath

                    // WRITE FILE I/O
                    Files.write(Paths.get(uploadPath), fileBytes);

                    // COMRESS IMAGE
                    File imageFile = new File(uploadPath);

                    String uploadCompressPath = "src/main/resources/static/upload/testimony/"+ name_user + "_" + random + "."
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
                    String fixRealPath = "/testimony/"+ name_user + "_" + random + "." + ext;

                    
                    // FINAL, namaKaryawanTxt
                    // FINAL, positionTxt
                    // FINAL, bioTxt
                    // FINAL, jika tidak mau pakai Compress pakai uploadPath untuk path image
                    // FINAL, jika mau pakai Compress pakai uploadCompressPath untuk path image

                    // Membuat Object Models Team
                    TestimonyModel testimonyModel = new TestimonyModel();
                    testimonyModel.setUserAvaPath(fixRealPath);
                    testimonyModel.setNameUser(name_user);
                    testimonyModel.setCompany(company);
                    testimonyModel.setTestimonyText(testimony_text);

                    // SAVE TO DATABASE WITH MODELS OBJECT DATA
                    testimonyService.saveTestimony(testimonyModel);
                    System.out.println("Upload Berhasil");
                    publicData.addAttribute("sucmsg", "Upload Berhasil");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    publicData.addAttribute("errmsg", e.getMessage());
                }
            }
        }

        return "public/cms/admin/pages/testimony/new";
    }

    @RequestMapping(value = "/admin/testimony/edit/{id}", method = RequestMethod.GET)
    public String testimonyEdit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("title", "Testimony Edit");
        TestimonyModel testimonyModel = testimonyService.findOne(id);
        model.addAttribute("testimony", testimonyModel);
        model.addAttribute("testimonys", testimonyService.listAll());
        return "public/cms/admin/pages/testimony/edit";
    }

    @RequestMapping(value = "/admin/testimony/update/{id}", method = RequestMethod.POST, consumes = {
        "multipart/form-data" })
    public String testimonyUpdate(
        @RequestParam("id") long id,
        @RequestParam(value = "name_user", required = false) String name_user,
        @RequestParam(value = "company", required = false) String company,
        @RequestParam(value = "testimony_text", required = false) String testimony_text,
        @RequestParam(value = "user_ava_path") MultipartFile user_ava_path,RedirectAttributes attributes, Model model) {
        String exten = user_ava_path.getContentType().toString();
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
        if (user_ava_path.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            name_user = name_user.replaceAll("[^a-zA-Z0-9]", "_");
            testimonyService.testimonyUpdateWithOutImg(id,name_user,company,testimony_text);
            return "redirect:/admin/testimony";
        }
        // String fileName = StringUtils.cleanPath(carouselImage.getOriginalFilename());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date tanggal = new Date();
        String random = simpleDateFormat.format(tanggal).toString();
        name_user = name_user.replaceAll("[^a-zA-Z0-9]", "_");
        String nameImg = "/testimony/"+ name_user + "_" + random + "." + ext;
        try {
            Path path = Paths.get("src/main/resources/static/upload/testimony/" + name_user + "_" + random + "." + ext);
            Files.copy(user_ava_path.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            testimonyService.testimonyUpdate(id,name_user,company,testimony_text,nameImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", "You successfully uploaded " + random + "." + ext + '!');

        return "redirect:/admin/testimony";
    }

    @RequestMapping(value = "/admin/testimony/delete/{id}", method = RequestMethod.GET)
    public String testimonyDelete(@PathVariable("id") Integer id,
     Model model) {
        TestimonyModel testimonyModel = testimonyService.findOne(id);
        Path path = Paths.get("src/main/resources/static/upload/" + testimonyModel.getUserAvaPath());
         try{
            Files.deleteIfExists(path);
            testimonyService.delete(id);
            model.addAttribute("testimonys", testimonyService.listAll());
         }catch(IOException io){
            System.out.printf("No such file or directory: %s\n", path);
         }
        return "redirect:/admin/testimony";
    }

}
