package pro.sky.GroupWorkJava.controller;

import com.pengrad.telegrambot.model.File;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.service.PhotoService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Maxon4ik
 * @date 11.08.2022 14:26
 */
@RestController
@RequestMapping("photoReports")
public class PhotoController {

    private final PhotoService photoService;

    private final String fileType = "image/jpeg";

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(value = "/{id}/check")
    public ReportData downloadReport(@PathVariable Long id) {
        return photoReportService.findPhotoReport(id);
    }

    @GetMapping(value = "/{id}/photo-from-db")
    public ResponseEntity<byte[]> downloadPhotoFromDB(@PathVariable Long id) {
        ReportData reportData = photoReportService.findPhotoReport(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType));
        headers.setContentLength(reportData.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportData.getData());
    }

    @GetMapping(value = "/{id}/avatar-from-file")
    public void downloadPhotoFromFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
        ReportData reportData = photoReportService.findPhotoReport(id);
        Path path = Path.of(reportData.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(fileType);
            response.setContentLength((int) reportData.getFileSize());
            is.transferTo(os);
        }
    }
}
