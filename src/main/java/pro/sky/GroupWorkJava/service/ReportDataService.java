package pro.sky.GroupWorkJava.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.model.File;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.repository.ReportDataRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

        ReportData report = reportRepository.findByChatId(personId);
        report.setLastMessage(dateSendMessage);
        report.setFilePath(filePath);
        report.setFileSize(file.fileSize());
        report.setData(pictureFile);
        report.setRation(ration);
        report.setHealth(health);
        report.setHabits(habits);
        reportRepository.save(report);
    }

    public void uploadReportData(Long personId, byte[] pictureFile, File file,
                                 String caption, String filePath, Date dateSendMessage) throws IOException {

        ReportData report = reportRepository.findByChatId(personId);
        report.setLastMessage(dateSendMessage);
        report.setFilePath(filePath);
        report.setFileSize(file.fileSize());
        report.setData(pictureFile);
        report.setCaption(caption);
        reportRepository.save(report);
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

    public List<ReportData> getAll() {
        return reportRepository.findAll();
    }

    public List<ReportData> getAllReports(Integer pageNumber, Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return reportRepository.findAll(pageRequest).getContent();
    }
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
