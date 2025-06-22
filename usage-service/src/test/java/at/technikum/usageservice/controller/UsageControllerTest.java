package at.technikum.usageservice.controller;

import at.technikum.usageservice.model.UsageData;
import at.technikum.usageservice.service.UsageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsageController.class)
class UsageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsageService usageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllUsageData() throws Exception {
        UsageData usageData = new UsageData();
        usageData.setHour(LocalDateTime.parse("2025-01-10T14:34:00"));
        usageData.setCommunityProduced(0.123);
        usageData.setCommunityUsed(0.456);
        usageData.setGridUsed(0.789);

        List<UsageData> usageDataList = Collections.singletonList(usageData);

        given(usageService.getAllUsageData()).willReturn(usageDataList);

        mockMvc.perform(get("/usage"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usageDataList)));
    }


    @Test
    void testDeleteAllUsageData() throws Exception {
        doNothing().when(usageService).deleteAllUsageData();

        mockMvc.perform(delete("/usage"))
                .andExpect(status().isOk());
    }
}
