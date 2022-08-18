package pro.sky.GroupWorkJava.service;

import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.model.File;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.repository.ReportDataRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.util.Date;

/**
 * @author Maxon4ik
 * @date 10.08.2022 15:00
 */

@Service
@Transactional
public class ReportDataService {
    private final ReportDataRepository reportRepository;

    public ReportDataService(ReportDataRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void uploadReportData(Long personId, byte[] pictureFile, File file, String ration, String health,
                                 String habits, String filePath, Date dateSendMessage) throws IOException {

        ReportData photo = findById(personId);
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

    public void uploadReportData(Long personId, byte[] pictureFile, File file,
                                 String caption, String filePath, Date dateSendMessage) throws IOException {

        ReportData photo = findById(personId);
        photo.setLastMessage(dateSendMessage);
        photo.setFilePath(filePath);
        photo.setFileSize(file.fileSize());
        photo.setData(pictureFile);
        photo.setChatId(personId);
        photo.setCaption(caption);
        reportRepository.save(photo);
    }

    public ReportData findById(Long id) {
        return reportRepository.findById(id).orElse(new ReportData());
    }

    public ReportData save(ReportData report){
        return reportRepository.save(report);
    }

    public void remove(Long id){
        reportRepository.deleteById(id);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
