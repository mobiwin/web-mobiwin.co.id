package com.mobiwin.websites.controllers.back;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.OurClientModel;
import com.mobiwin.websites.services.OurClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ClientController {

    @Autowired
    OurClientService ourClientService;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/admin/client", method = RequestMethod.GET)
    public String listClient(Model publicData, HttpSession sessi, HttpServletResponse httpResponse) {
        if (sessi.getAttribute("id_session") != null) {
            publicData.addAttribute("title", "Client");
            List<OurClientModel> ourClientListData = ourClientService.listAllClient();
            publicData.addAttribute("list_data", ourClientListData);
            return "public/cms/admin/pages/ourclient/list";
        }else{
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/client/new", method = RequestMethod.GET)
    public String newClient(Model publicData) {
        publicData.addAttribute("title", "New Client");
        return "public/cms/admin/pages/ourclient/new";
    }

    @RequestMapping(value = "/admin/client/save", method = RequestMethod.POST)
    public String saveClient(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam(value = "clientNameTxt", required = false) String clientNameTxt,
            @RequestParam(value = "yearsCooperationTxt", required = false) String yearsTxt,
            @RequestParam(value = "pilihLogoInp", required = false) MultipartFile logoFile) {

        String msg = "";

        if (logoFile.isEmpty()) {
            publicData.addAttribute("errmsg", "Empty file option");
        } else {

            String exten = logoFile.getContentType().toString();
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
                publicData.addAttribute("errmsg", "Choose file");
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
                    if (!Files.exists(Paths.get("src/main/resources/static/upload/client/"))) {
                        Files.createDirectories(Paths.get("src/main/resources/static/upload/client/"));
                    }

                    // UPLOAD
                    byte[] fileBytes = logoFile.getBytes();
                    String cleanClientNameTxt = clientNameTxt.replaceAll("[^a-zA-Z0-9]", "_");
                    String uploadPath = "src/main/resources/static/upload/temp/" + cleanClientNameTxt + "_" + random + "."
                            + ext;

                    // KALAU GAK MAU PAKAI COMRESS, AMBIL VARIABEL uploadPath

                    // WRITE FILE I/O
                    Files.write(Paths.get(uploadPath), fileBytes);

                    // COMRESS IMAGE
                    File imageFile = new File(uploadPath);

                    String uploadCompressPath = "src/main/resources/static/upload/client/" + cleanClientNameTxt + "_"
                            + random + "." + ext;
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
                        publicData.addAttribute("errmsg", imageWriters.hasNext());
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
                    String fixRealPath = "/client/" + cleanClientNameTxt + "_" + random + "." + ext;

                    // FINAL, namaKaryawanTxt
                    // FINAL, positionTxt
                    // FINAL, bioTxt
                    // FINAL, jika tidak mau pakai Compress pakai uploadPath untuk path image
                    // FINAL, jika mau pakai Compress pakai uploadCompressPath untuk path image

                    // Membuat Object Models Client
                    OurClientModel ourClientModel = new OurClientModel();
                    ourClientModel.setClientName(clientNameTxt);
                    ourClientModel.setPreviewPath(fixRealPath);
                    ourClientModel.setYear(yearsTxt);

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = format.format(new Date());
                    Date datenow = format.parse(dateString);

                    ourClientModel.setCreatedAt(datenow);

                    // SAVE TO DATABASE WITH MODELS OBJECT DATA
                    ourClientService.saveClient(ourClientModel);

                    msg = "Add Client success";
                } catch (Exception e) {
                    publicData.addAttribute("errmsg", e.getMessage());
                }
            }
        }

        return "redirect:/admin/client?msg=" + msg;
    }

    @RequestMapping(value = "/admin/client/edit/{id}", method = RequestMethod.GET)
    public String editClient(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @PathVariable("id") Long id) {
        publicData.addAttribute("title", "Edit Client");
        OurClientModel ourClientListDataWithId = ourClientService.listClientById(id);
        publicData.addAttribute("list_data", ourClientListDataWithId);

        return "public/cms/admin/pages/ourclient/edit";
    }

    @RequestMapping(value = "/admin/client/update", method = RequestMethod.POST)
    public String updateClient(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam(value = "idTxt", required = false) String id,
            @RequestParam(value = "clientNameTxt", required = false) String clientNameTxt,
            @RequestParam(value = "yearsCooperationTxt", required = false) String yearsTxt,
            @RequestParam(value = "pilihLogoInp", required = false) MultipartFile logoFile) {

        String msg = "";

        if (logoFile.isEmpty()) {
            try {
                ourClientService.updatePartDataClient(clientNameTxt, yearsTxt, id);
                msg = "Edit Data Client Berhasil";
            } catch (Exception e) {
                publicData.addAttribute("errmsg", e.getMessage());
            }
        } else {

            String exten = logoFile.getContentType().toString();
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
                publicData.addAttribute("errmsg", "Empty file option");
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
                    if (!Files.exists(Paths.get("src/main/resources/static/upload/client/"))) {
                        Files.createDirectories(Paths.get("src/main/resources/static/upload/client/"));
                    }

                    // UPLOAD
                    byte[] fileBytes = logoFile.getBytes();
                    String cleanClientNameTxt = clientNameTxt.replaceAll("[^a-zA-Z0-9]", "_");
                    String uploadPath = "src/main/resources/static/upload/temp/" + cleanClientNameTxt + "_" + random + "."
                            + ext;

                    // KALAU GAK MAU PAKAI COMRESS, AMBIL VARIABEL uploadPath

                    // WRITE FILE I/O
                    Files.write(Paths.get(uploadPath), fileBytes);

                    // COMRESS IMAGE
                    File imageFile = new File(uploadPath);

                    String uploadCompressPath = "src/main/resources/static/upload/client/" + cleanClientNameTxt + "_"
                            + random + "." + ext;
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
                        publicData.addAttribute("errmsg", imageWriters.hasNext());

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
                    String fixRealPath = "/client/" + cleanClientNameTxt + "_" + random + "." + ext;

                    // FINAL, namaKaryawanTxt
                    // FINAL, positionTxt
                    // FINAL, bioTxt
                    // FINAL, jika tidak mau pakai Compress pakai uploadPath untuk path image
                    // FINAL, jika mau pakai Compress pakai uploadCompressPath untuk path image

                    // Membuat Object Models Client
                    OurClientModel ourClientModel = new OurClientModel();
                    ourClientModel.setClientName(clientNameTxt);
                    ourClientModel.setPreviewPath(fixRealPath);
                    ourClientModel.setYear(yearsTxt);

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = format.format(new Date());
                    Date datenow = format.parse(dateString);

                    ourClientModel.setCreatedAt(datenow);

                    // SAVE TO DATABASE WITH MODELS OBJECT DATA
                    ourClientService.saveClient(ourClientModel);

                    msg = "Update Client data success";
                } catch (Exception e) {
                    publicData.addAttribute("errmsg", e.getMessage());
                }
            }
        }

        return "redirect:/admin/client?msg=" + msg;
    }

    @RequestMapping(value = "/admin/client/delete/{id}", method = RequestMethod.GET)
    public String deleteClient(Model publicData, HttpSession sessi, HttpServletResponse httpResponse, @PathVariable("id") Long id) {

        String msg = "";

        OurClientModel ourClientListDataWithId = ourClientService.listClientById(id);
        if(ourClientListDataWithId.getId() > 0) {
            try {
                ourClientService.deleteClient(id);
                msg = "Delete Client data success";
            } catch (Exception e) {
                msg = "Delete Client data failed";
            }
        } else {
            msg = "Data Client not found";
        }

        return "redirect:/admin/client?msg=" + msg;
    }
}
