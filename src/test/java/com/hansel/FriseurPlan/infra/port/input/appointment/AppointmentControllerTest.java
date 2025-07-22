package com.hansel.FriseurPlan.infra.port.input.appointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansel.FriseurPlan.BaseIntegrationTest;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentDto;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class AppointmentControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    private AppointmentDto appointmentDto;


    @BeforeEach
    void setUp() {

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusHours(1);
        TimeRange timeRange = TimeRange.create(start, end);
        appointmentDto = new AppointmentDto(timeRange,1L,1L);
    }


    @Test
    public void shouldCreateAppointmentWithToken() throws Exception {

        this.mvc.perform(
                        post("/v1/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(appointmentDto))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

}