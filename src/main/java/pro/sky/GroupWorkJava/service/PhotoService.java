package pro.sky.GroupWorkJava.service;

import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.model.File;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.repository.ReportRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Maxon4ik
 * @date 10.08.2022 15:00
 */

@Service
@Transactional
public class PhotoService {
    private final ReportRepository reportRepository;

    @Value("/photo")
    private String photoDir;


    public PhotoService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void uploadPhoto(Long personId, byte[] pictureFile, File file,
                            String caption, String ration, String health, String habits, String filePath, Date dateSendMessage) throws IOException {

        ReportData photo = findPhoto(personId);
        photo.setLastMessage(dateSendMessage);
        photo.setFilePath(filePath);
        photo.setFileSize(file.fileSize());
        photo.setData(pictureFile);
        photo.setChatId(personId);
        photo.setCaption(caption);
        photo.setRation(ration);
        photo.setHealth(health);
        photo.setHabits(habits);
        reportRepository.save(photo);

    }

    public ReportData findPhoto(Long personId) {
        return reportRepository.findById(personId).orElse(new ReportData());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
