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
    private static final String REGEX_MESSAGE = "(Рацион:)(\\s)(\\W+)(;)\n" +
            "(Самочувствие:)(\\s)(\\W+)(;)\n" +
            "(Поведение:)(\\s)(\\W+)(;)";


    @Value("/photo")
    private String photoDir;


    public PhotoService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void uploadPhoto(Long personId, byte[] pictureFile, File file, String caption) throws IOException { //, String caption
        Path filePath = Path.of(photoDir, personId + "_" +"photo" + "." + getExtensions(Objects.requireNonNull(file.filePath())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        ReportData photo = findPhoto(personId);
        photo.setFilePath(filePath.toString());
        photo.setFileSize(file.fileSize());
//        photo.setCaption(caption);
        photo.setData(pictureFile);

        photo.setChatId(personId);
        reportRepository.save(photo);

    }

    public void textCaption(Long personId, String caption) {
        Pattern pattern = Pattern.compile(REGEX_MESSAGE);
        Matcher matcher = pattern.matcher(caption);
        ReportData photo = findPhoto(personId);
        System.out.println("111");
        if (matcher.matches()) {
            System.out.println("555");
            String ration = matcher.group(3);
            String health = matcher.group(7);
            String habits = matcher.group(11);
            photo.setRation(ration);
            photo.setHabits(habits);
            photo.setHealth(health);
            System.out.println("123");
        }
        else {
            System.out.println("321");
        }
    }

    public ReportData findPhoto(Long personId) {
        return reportRepository.findById(personId).orElse(new ReportData());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
