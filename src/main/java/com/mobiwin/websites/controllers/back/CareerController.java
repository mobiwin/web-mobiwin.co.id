package com.mobiwin.websites.controllers.back;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.CandidateModel;
import com.mobiwin.websites.models.CareerModel;
import com.mobiwin.websites.services.CandidateService;
import com.mobiwin.websites.services.CareerService;
import com.mobiwin.websites.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CareerController {

    @Autowired
    CareerService careerService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/admin/career", method = RequestMethod.GET)
    public String listCareer(Model publicData, HttpSession sessi, HttpServletResponse httpResponse) {
        if (sessi.getAttribute("id_session") != null) {
            publicData.addAttribute("title", "career");
            List<CareerModel> careerListData = careerService.listAllCareer();
            publicData.addAttribute("list_data", careerListData);

            return "public/cms/admin/pages/career/list";
        }else{
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/career/new", method = RequestMethod.GET)
    public String newCareer(Model publicData) {
        publicData.addAttribute("title", "New career");
        return "public/cms/admin/pages/career/new";
    }

    @RequestMapping(value = "/admin/career/save", method = RequestMethod.POST)
    public String saveCareer(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam(value = "jobTitleTxt", required = false) String jobTitleTxt,
            @RequestParam(value = "potitionTxt", required = false) String potitionTxt,
            @RequestParam(value = "requirementTxt", required = false) String requirementTxt,
            @RequestParam(value = "descriptionTxt", required = false) String descriptionTxt,
            @RequestParam(value = "jobIconFile", required = false) MultipartFile jobIconFile) {

        String msg = "";

        if (jobIconFile.isEmpty()) {
            publicData.addAttribute("errmsg", "Empty file option");
        } else {

            String exten = jobIconFile.getContentType().toString();
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
                    if (!Files.exists(Paths.get("src/main/resources/static/upload/career/"))) {
                        Files.createDirectories(Paths.get("src/main/resources/static/upload/career/"));
                    }

                    // UPLOAD
                    byte[] fileBytes = jobIconFile.getBytes();
                    String cleanJobTitleTxt = jobTitleTxt.replaceAll("[^a-zA-Z0-9]", "_");
                    String uploadPath = "src/main/resources/static/upload/temp/" + cleanJobTitleTxt + "_" + random + "."
                            + ext;

                    // KALAU GAK MAU PAKAI COMRESS, AMBIL VARIABEL uploadPath

                    // WRITE FILE I/O
                    Files.write(Paths.get(uploadPath), fileBytes);

                    // COMRESS IMAGE
                    File imageFile = new File(uploadPath);

                    String uploadCompressPath = "src/main/resources/static/upload/career/" + cleanJobTitleTxt + "_" + random
                            + "." + ext;
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
                    // String fixTempPath = "/temp/" + jobTitleTxt + "_" + random + "." + ext;
                    String fixRealPath = "/career/" + cleanJobTitleTxt + "_" + random + "." + ext;

                    // FINAL, jobTitleTxt
                    // FINAL, positionTxt
                    // FINAL, bioTxt
                    // FINAL, jika tidak mau pakai Compress pakai uploadPath untuk path image
                    // FINAL, jika mau pakai Compress pakai uploadCompressPath untuk path image

                    // Membuat Object Models Career
                    CareerModel careerModel = new CareerModel();
                    careerModel.setIconOf(fixRealPath);
                    careerModel.setJobTitle(jobTitleTxt);
                    careerModel.setPotition(potitionTxt);
                    careerModel.setRequirement(requirementTxt);
                    careerModel.setPotitionDesc(descriptionTxt);

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = format.format(new Date());
                    Date datenow = format.parse(dateString);

                    careerModel.setCreatedAt(datenow);

                    // SAVE TO DATABASE WITH MODELS OBJECT DATA
                    careerService.saveCareer(careerModel);

                    msg = "Add Career success";
                } catch (Exception e) {
                    publicData.addAttribute("errmsg", e.getMessage());
                }
            }
        }

        try {
            msg = URLEncoder.encode(msg, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            msg = "Error";
        }

        return "redirect:/admin/career?msg=" + msg;
    }

    @RequestMapping(value = "/admin/career/edit/{id}", method = RequestMethod.GET)
    public String editCareer(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @PathVariable("id") Long id) {
        publicData.addAttribute("title", "Edit career");
        CareerModel ourCareerListDataWithId = careerService.listCareerById(id);
        publicData.addAttribute("list_data", ourCareerListDataWithId);

        return "public/cms/admin/pages/career/edit";
    }

    @RequestMapping(value = "/admin/career/update", method = RequestMethod.POST)
    public String updateCareer(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam(value = "idTxt", required = false) String idTxt,
            @RequestParam(value = "jobTitleTxt", required = false) String jobTitleTxt,
            @RequestParam(value = "potitionTxt", required = false) String potitionTxt,
            @RequestParam(value = "requirementTxt", required = false) String requirementTxt,
            @RequestParam(value = "descriptionTxt", required = false) String descriptionTxt,
            @RequestParam(value = "jobIconFile") MultipartFile jobIconFile) {

        String msg = "";

        if (jobIconFile.isEmpty()) {
            try {
                careerService.updatePartDataCareer(jobTitleTxt, potitionTxt, requirementTxt, descriptionTxt, idTxt);
                ;
                msg = "Update Career data success";
            } catch (Exception e) {
                publicData.addAttribute("errmsg", e.getMessage());
            }
        } else {

            String exten = jobIconFile.getContentType().toString();
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
                    if (!Files.exists(Paths.get("src/main/resources/static/upload/career/"))) {
                        Files.createDirectories(Paths.get("src/main/resources/static/upload/career/"));
                    }

                    // UPLOAD
                    byte[] fileBytes = jobIconFile.getBytes();
                    String cleanJobTitleTxt = jobTitleTxt.replaceAll("[^a-zA-Z0-9]", "_");
                    String uploadPath = "src/main/resources/static/upload/temp/" + cleanJobTitleTxt + "_" + random + "."
                            + ext;

                    // KALAU GAK MAU PAKAI COMRESS, AMBIL VARIABEL uploadPath

                    // WRITE FILE I/O
                    Files.write(Paths.get(uploadPath), fileBytes);

                    // COMRESS IMAGE
                    File imageFile = new File(uploadPath);

                    String uploadCompressPath = "src/main/resources/static/upload/career/" + cleanJobTitleTxt + "_" + random
                            + "." + ext;
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
                    // String fixTempPath = "/temp/" + jobTitleTxt + "_" + random + "." + ext;
                    String fixRealPath = "/career/" + cleanJobTitleTxt + "_" + random + "." + ext;

                    // FINAL, jobTitleTxt
                    // FINAL, positionTxt
                    // FINAL, bioTxt
                    // FINAL, jika tidak mau pakai Compress pakai uploadPath untuk path image
                    // FINAL, jika mau pakai Compress pakai uploadCompressPath untuk path image

                    // Membuat Object Models Career
                    CareerModel careerModel = new CareerModel();
                    careerModel.setIconOf(fixRealPath);
                    careerModel.setJobTitle(jobTitleTxt);
                    careerModel.setPotition(potitionTxt);
                    careerModel.setRequirement(requirementTxt);
                    careerModel.setPotitionDesc(descriptionTxt);

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = format.format(new Date());
                    Date datenow = format.parse(dateString);

                    careerModel.setCreatedAt(datenow);

                    // SAVE TO DATABASE WITH MODELS OBJECT DATA
                    careerService.saveCareer(careerModel);

                    msg = "Update Career data success";
                } catch (Exception e) {
                    publicData.addAttribute("errmsg", e.getMessage());
                }
            }
        }

        try {
            msg = URLEncoder.encode(msg, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            msg = "Error";
        }

        return "redirect:/admin/career?msg=" + msg; 
    }

    @RequestMapping(value = "/admin/career/candidate/{id}", method = RequestMethod.GET)
    public String candidateCareer(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @PathVariable("id") Long id) {
        publicData.addAttribute("title", "Candidate career");

        List<CandidateModel> candidateNotBeenSeen = candidateService.hasNotBeenSeen(id,"has_not_been_seen");
        publicData.addAttribute("hasNotBeenSeen", candidateNotBeenSeen);

        List<CandidateModel> candidateBeenSeen = candidateService.hasBeenSeen(id,"has_been_seen");
        publicData.addAttribute("hasBeenSeen", candidateBeenSeen);

        return "public/cms/admin/pages/career/candidate";
    }
    @RequestMapping(value = "/admin/career/candidate/update/{id}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void candidateUpdate(HttpServletResponse response,@PathVariable("id") Long id,
    @RequestParam("email") String email,@RequestParam("name") String name)
    throws MessagingException {
        try{
            candidateService.candidateUpdate(id,"has_been_seen");
            emailService.sendMailDoneCandidate(email,name);
            response.sendRedirect("/admin/career");
        } catch(IOException e){
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/admin/career/delete/{id}", method = RequestMethod.GET)
    public String deleteCareer(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @PathVariable("id") Long id) {

        String msg = "";

        CareerModel ourCareerListDataWithId = careerService.listCareerById(id);
        if (ourCareerListDataWithId.getId() > 0) {
            try {
                careerService.deleteCareer(id);
                msg = "Delete Career data success";
            } catch (Exception e) {
                msg = "Delete Career data failed";
            }
        } else {
            msg = "Data Career not found";
        }

        return "redirect:/admin/career?msg=" + msg;
    }
}
