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

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobiwin.websites.models.OurTeamModel;
import com.mobiwin.websites.services.OurTeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TeamController {

    @Autowired
    OurTeamService ourTeamService;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/admin/team", method = RequestMethod.GET)
    public String listTeam() {

        return "public/cms/admin/pages/ourteam/list";
    }

    @RequestMapping(value = "/admin/team/new", method = RequestMethod.GET)
    public String newTeam() {

        return "public/cms/admin/pages/ourteam/new";
    }

    @RequestMapping(value = "/admin/team/save", method = RequestMethod.POST)
    public String saveTeam(Model publicData, HttpSession sessi, HttpServletResponse httpResponse,
            @RequestParam(value = "namaKaryawanTxt", required = false) String namaKaryawanTxt,
            @RequestParam(value = "positionTxt", required = false) String positionTxt,
            @RequestParam(value = "bioTxt", required = false) String bioTxt,
            @RequestParam(value = "pilihAvatarInp") MultipartFile avatarFiles) {

        if (avatarFiles.isEmpty()) {
            System.out.println("File kosong");
        } else {

            String exten = avatarFiles.getContentType().toString();
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

                    // MKDIR
                    if (!Files.exists(Paths.get("src/main/resources/static/upload/temp/"))) {
                        Files.createDirectories(Paths.get("src/main/resources/static/upload/temp/"));
                    }

                    // UPLOAD
                    byte[] fileBytes = avatarFiles.getBytes();
                    namaKaryawanTxt = namaKaryawanTxt.replaceAll("[^a-zA-Z0-9]", "_");
                    String uploadPath = "src/main/resources/static/upload/temp/" + namaKaryawanTxt + "_" + random + "."
                            + ext;

                    // KALAU GAK MAU PAKAI COMRESS, AMBIL VARIABEL uploadPath

                    // WRITE FILE I/O
                    Files.write(Paths.get(uploadPath), fileBytes);

                    // COMRESS IMAGE
                    File imageFile = new File(uploadPath);

                    String uploadCompressPath = "src/main/resources/static/upload/" + namaKaryawanTxt + "_" + random + "."
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

                    
                    // FINAL, namaKaryawanTxt
                    // FINAL, positionTxt
                    // FINAL, bioTxt
                    // FINAL, jika tidak mau pakai Compress pakai uploadPath untuk path image
                    // FINAL, jika mau pakai Compress pakai uploadCompressPath untuk path image

                    // Membuat Object Models Team
                    OurTeamModel ourTeamModel = new OurTeamModel();
                    ourTeamModel.setAvatarPath(uploadCompressPath);
                    ourTeamModel.setEmployeeName(namaKaryawanTxt);
                    ourTeamModel.setPotition(positionTxt);
                    ourTeamModel.setBio(bioTxt);

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = format.format(new Date());
                    Date datenow  = format.parse (dateString); 
                
                    ourTeamModel.setCreatedAt(datenow);

                    // SAVE TO DATABASE WITH MODELS OBJECT DATA
                    ourTeamService.saveOurTeam(ourTeamModel);

                    publicData.addAttribute("sucmsg", "Upload Berhasil");
                } catch (Exception e) {
                    publicData.addAttribute("errmsg", e.getMessage());
                }
            }
        }

        return "public/cms/admin/pages/ourteam/new";
    }
}
