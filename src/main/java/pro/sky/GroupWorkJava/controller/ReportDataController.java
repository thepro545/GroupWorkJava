package pro.sky.GroupWorkJava.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.service.ReportDataService;

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
public class ReportDataController {

    private final ReportDataService reportDataService;

    private final String fileType = "image/jpeg";

    public ReportDataController(ReportDataService reportDataService) {
        this.reportDataService = reportDataService;
    }

    @GetMapping("/{id}/report")
    public ReportData downloadReport(@PathVariable Long id) {
        return reportDataService.findById(id);
    }

    @PostMapping()
    public void save(ReportData report) {
        reportDataService.save(report);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        reportDataService.remove(id);
    }

    @GetMapping("/{id}/photo-from-db")
    public ResponseEntity<byte[]> downloadPhotoFromDB(@PathVariable Long id) {
        ReportData reportData = reportDataService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType));
        headers.setContentLength(reportData.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportData.getData());
    }

    @GetMapping("/{id}/photo-from-file")
    public void downloadPhotoFromFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
        ReportData reportData = reportDataService.findById(id);
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
