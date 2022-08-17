package pro.sky.GroupWorkJava.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.GroupWorkJava.model.ReportData;
import pro.sky.GroupWorkJava.service.ReportDataService;

import java.io.*;
import java.util.Objects;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportDataController.class)
class ReportDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("reportDataService")
    private ReportDataService reportDataService;

    @Test
    void downloadReport() throws Exception {
        String ration = "good ration";
        String health = "health";
        ReportData reportData = new ReportData();
        reportData.setHealth(health);
        reportData.setRation(ration);
        when(reportDataService.findById(anyLong())).thenReturn(reportData);

        mockMvc.perform(
                        get("/photoReports/{id}/check", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.health").value(health))
                .andExpect(jsonPath("$.ration").value(ration));
        verify(reportDataService).findById(1L);
    }

    @Test
    void downloadPhotoFromDataBase() throws Exception {
        String fileType = "image/jpeg";
        ReportData reportData = new ReportData();
        InputStream is = getClass().getClassLoader().getResourceAsStream("pet.jpeg");
        byte[] data = Objects.requireNonNull(is).readAllBytes();
        reportData.setData(data);

        when(reportDataService.findById(anyLong())).thenReturn(reportData);
        mockMvc.perform(
                        get("/photoReports/{id}/photo-from-db", 1L))
                .andExpect(status().isOk())
                .andExpect(content().bytes(data))
                .andExpect(content().contentType(fileType));
        verify(reportDataService).findById(1L);
    }

    @Test
    void testDownloadAvatarFromFile() throws Exception {
        String fileType = "image/jpeg";
        ReportData reportData = new ReportData();
        reportData.setFilePath("src/test/java/pro/sky/GroupWorkJava/controller/pet.jpeg");

        InputStream is = getClass().getClassLoader().getResourceAsStream("pet.jpeg");
        byte[] data = Objects.requireNonNull(is).readAllBytes();

        when(reportDataService.findById(anyLong())).thenReturn(reportData);
        mockMvc.perform(
                        get("/photoReports/{id}/photo-from-file", 1L))
                .andExpect(status().isOk())
                .andExpect(content().bytes(data))
                .andExpect(content().contentType(fileType));
        verify(reportDataService).findById(1L);
    }
}