package pro.sky.GroupWorkJava.service;

import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.model.File;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.repository.ReportRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.util.Date;

/**
 * @author Maxon4ik
 * @date 10.08.2022 15:00
 */

@Service
@Transactional
public class PhotoReportService {
    private final ReportRepository reportRepository;

    public PhotoReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void uploadPhoto(Long personId, byte[] pictureFile, File file, String ration, String health, String habits, String filePath, Date dateSendMessage) throws IOException {

        ReportData photo = findPhotoReport(personId);
        photo.setLastMessage(dateSendMessage);
        photo.setFilePath(filePath);
        photo.setFileSize(file.fileSize());
        photo.setData(pictureFile);
        photo.setChatId(personId);
        photo.setRation(ration);
        photo.setHealth(health);
        photo.setHabits(habits);
        reportRepository.save(photo);

    }

    public void uploadPhoto(Long personId, byte[] pictureFile, File file,
                            String caption, String filePath, Date dateSendMessage) throws IOException {

        ReportData photo = findPhotoReport(personId);
        photo.setLastMessage(dateSendMessage);
        photo.setFilePath(filePath);
        photo.setFileSize(file.fileSize());
        photo.setData(pictureFile);
        photo.setChatId(personId);
        photo.setCaption(caption);
        reportRepository.save(photo);

    }


    public ReportData findPhotoReport(Long personId) {
        return reportRepository.findById(personId).orElse(new ReportData());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
