package com.mobiwin.websites.controllers.back;

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
import java.awt.image.BufferedImage;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.CarouselModel;
import com.mobiwin.websites.repositories.CarouselRepo;
import com.mobiwin.websites.services.CarouselService;
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
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    @Autowired
    CarouselRepo carouselRepo;

    @RequestMapping(value = "/admin/carousel", method = RequestMethod.GET)
    public String carousel(Model model,HttpSession sessi) {
        if (sessi.getAttribute("id_session") != null) {
            model.addAttribute("title", "Carousel");
            List<CarouselModel> tes = carouselService.listAll();
            model.addAttribute("carousel", tes);
            return "cms/admin/pages/carousel/carousel";
        }else{
            return"redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/carousel/new", method = RequestMethod.GET)
    public String carouselNew(Model model,HttpSession sessi) {
        if (sessi.getAttribute("id_session") != null) {
            model.addAttribute("title", "Carousel New");
            return "cms/admin/pages/carousel/new";
        }else{
            return"redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/carousel/save", method = RequestMethod.POST)
    public String carouselSave(RedirectAttributes attributes,Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam(value = "orders", required = true) long orders,
            @RequestParam(value = "caption", required = false) String caption,
            @RequestParam(value = "carouselImage") MultipartFile carouselImage) {

        if (carouselImage.isEmpty()) {
            System.out.println("File kosong");
        } else {

            String exten = carouselImage.getContentType().toString();
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
                    if (!Files.exists(Paths.get("src/main/resources/static/upload/carousel/"))) {
                        Files.createDirectories(Paths.get("src/main/resources/static/upload/carousel/"));
                    }

                    // UPLOAD
                    byte[] fileBytes = carouselImage.getBytes();
                    // carouselImage = carouselImage.getOriginalFilename();
                    String uploadPath = "src/main/resources/static/upload/temp/" + random + "." + ext;
                    

                    // KALAU GAK MAU PAKAI COMRESS, AMBIL VARIABEL uploadPath

                    // WRITE FILE I/O
                    Files.write(Paths.get(uploadPath), fileBytes);

                    // COMRESS IMAGE
                    File imageFile = new File(uploadPath);

                    String uploadCompressPath = "src/main/resources/static/upload/carousel/"+ random + "."
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
                    String fixRealPath = "/carousel/" + random + "." + ext;

                    
                    // FINAL, namaKaryawanTxt
                    // FINAL, positionTxt
                    // FINAL, bioTxt
                    // FINAL, jika tidak mau pakai Compress pakai uploadPath untuk path image
                    // FINAL, jika mau pakai Compress pakai uploadCompressPath untuk path image

                    // Membuat Object Models Team
                    CarouselModel carouselModel = new CarouselModel();
                    carouselModel.setOrders(orders);
                    carouselModel.setCarouselImage(fixRealPath);
                    carouselModel.setCaption(caption);

                    // SAVE TO DATABASE WITH MODELS OBJECT DATA
                    carouselService.saveSlider(carouselModel);
                    attributes.addFlashAttribute("msgsuc","Insert Successfully");
                } catch (Exception e) {
                    attributes.addFlashAttribute("msgerr",e);
                }
            }
        }

        return "redirect:/admin/carousel";
    }

    @RequestMapping(value = "/admin/carousel/edit/{id}", method = RequestMethod.GET)
    public String carouselEdit(@PathVariable("id") Integer id, Model model,HttpSession sessi) {
        if (sessi.getAttribute("id_session") != null) {
            model.addAttribute("title", "Carousel Edit");
            CarouselModel carouselModel = carouselService.findOne(id);
            model.addAttribute("carousel", carouselModel);
            model.addAttribute("carousels", carouselService.listAll());
            return "cms/admin/pages/carousel/edit";
        }else{
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/carousel/update/{id}", method = RequestMethod.POST, consumes = {
            "multipart/form-data" })
    public String carouselUpdate(@RequestParam("carouselImage") MultipartFile carouselImage, @RequestParam("id") long id,
        @RequestParam("orders") long order,@RequestParam("caption") String caption, RedirectAttributes attributes, Model model) {
        String exten = carouselImage.getContentType().toString();
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
        if (carouselImage.isEmpty()) {
            carouselService.sliderUpdateWithOutImg(id,order,caption);
            attributes.addFlashAttribute("msgsuc","Updated Successfully");
            return "redirect:/admin/carousel";
        }else{
            // String fileName = StringUtils.cleanPath(carouselImage.getOriginalFilename());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date tanggal = new Date();
            String random = simpleDateFormat.format(tanggal).toString();
            String nameImg = "/slider/" + random + "." + ext;
            try {
                Path path = Paths.get("src/main/resources/static/upload/carousel/" + random + "." + ext);
                Files.copy(carouselImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                carouselService.sliderUpdate(id,order, nameImg, caption);
                attributes.addFlashAttribute("msgsuc","Updated Successfully");
            } catch (Exception e) {
                attributes.addFlashAttribute("msgserr",e);
            }
            return "redirect:/admin/carousel";
        }
    }

    @RequestMapping(value = "/admin/carousel/delete/{id}", method = RequestMethod.GET)
    public String carouselDelete(RedirectAttributes attributes,@PathVariable("id") Integer id,
     Model model) {
        CarouselModel carouselModel = carouselService.findOne(id);
        Path path = Paths.get("src/main/resources/static/upload/" + carouselModel.getCarouselImage());
         try{
            Files.deleteIfExists(path);
            carouselService.delete(id);
            model.addAttribute("carousels", carouselService.listAll());
            attributes.addFlashAttribute("msgsuc","Deleted Successfully");
         }catch(Exception e){
            attributes.addFlashAttribute("msgerr",e);
         }
        return "redirect:/admin/carousel";
    }
}
