package pro.sky.GroupWorkJava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.model.File;
import pro.sky.GroupWorkJava.exceptions.ReportDataNotFoundException;
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

    private final Logger logger = LoggerFactory.getLogger(ReportDataService.class);

    public ReportDataService(ReportDataRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void uploadReportData(Long personId, byte[] pictureFile, File file, String ration, String health,
                                 String habits, String filePath, Date dateSendMessage, Long timeDate, int daysOfReports) throws IOException {
        logger.info("Was invoked method to uploadReportData");
        ReportData report = findById(personId);
        report.setLastMessage(dateSendMessage);
        report.setDays(daysOfReports);
        report.setFilePath(filePath);
        report.setFileSize(file.fileSize());
        report.setLastMessageMS(timeDate);
        report.setChatId(personId);
        report.setData(pictureFile);
        report.setRation(ration);
        report.setHealth(health);
        report.setHabits(habits);
        reportRepository.save(report);
    }

    public void uploadReportData(Long personId, byte[] pictureFile, File file,
                                 String caption, String filePath, Date dateSendMessage, Long timeDate, int daysOfReports) throws IOException {
        logger.info("Was invoked method to uploadReportData");
        ReportData report = findById(personId);
        report.setLastMessage(dateSendMessage);
        report.setDays(daysOfReports);
        report.setFilePath(filePath);
        report.setChatId(personId);
        report.setFileSize(file.fileSize());
        report.setData(pictureFile);
        report.setCaption(caption);
        report.setLastMessageMS(timeDate);
        reportRepository.save(report);
    }

    public ReportData findById(Long id) {
        logger.info("Was invoked method to get a report by id={}", id);
        return reportRepository.findById(id).orElseThrow(ReportDataNotFoundException::new);
    }

    public ReportData findByChatId(Long chatId) {
        logger.info("Was invoked method to get a report by chatId={}", chatId);
        return reportRepository.findByChatId(chatId);
    }

    public Collection<ReportData> findListByChatId(Long chatId) {
        logger.info("Was invoked method to findListByChatId a report by id={}", chatId);
        return reportRepository.findListByChatId(chatId);
    }

    public ReportData save(ReportData report) {
        logger.info("Was invoked method to save a report");
        return reportRepository.save(report);
    }

    public void remove(Long id) {
        logger.info("Was invoked method to remove a report by id={}", id);
        reportRepository.deleteById(id);
    }

    public List<ReportData> getAll() {
        logger.info("Was invoked method to get all reports");
        return reportRepository.findAll();
    }

    public List<ReportData> getAllReports(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method to get all reports");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return reportRepository.findAll(pageRequest).getContent();
    }

    private String getExtensions(String fileName) {
        logger.info("Was invoked method to getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
