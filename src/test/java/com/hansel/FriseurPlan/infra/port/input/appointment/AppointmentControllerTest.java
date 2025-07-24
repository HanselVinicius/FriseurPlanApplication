package com.hansel.FriseurPlan.infra.port.input.appointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansel.FriseurPlan.BaseIntegrationTest;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentDto;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.infra.port.output.entities.AddressVo;
import com.hansel.FriseurPlan.infra.port.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntityRepository;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.vo.TimeRangeVo;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntityRepository;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class AppointmentControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CostumerEntityRepository costumerEntityRepository;
    @Autowired
    private HairdresserEntityRepository hairdresserEntityRepository;
    @Autowired
    private AppointmentEntityRepository appointmentEntityRepository;

    private Email email;
    private AppointmentDto appointmentDto;
    private PhoneNumber phoneNumber;
    private CostumerEntity costumerEntity;
    private HairdresserEntity hairdresserEntity;
    private Address address;

    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.costumerEntity = new CostumerEntity(null, "name", phoneNumber, EmailVo.fromEmailDomain(email), LocalDateTime.MAX, null, null);
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
        this.hairdresserEntity = new HairdresserEntity(null, "John Doe", EmailVo.fromEmailDomain(email), phoneNumber, AddressVo.fromAddressDomain(address), LocalDateTime.MAX, null, null);

        this.costumerEntityRepository.save(costumerEntity);
        this.hairdresserEntityRepository.save(hairdresserEntity);

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusHours(1);
        TimeRange timeRange = TimeRange.create(start, end);
        appointmentDto = new AppointmentDto(timeRange, 1L, 1L);
    }



    @Test
    public void shouldCreateAppointment() throws Exception {

        MvcResult mvcResult = this.mvc.perform(
                        post("/v1/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(appointmentDto))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Appointment appointment = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Appointment.class);
        String location = mvcResult.getResponse().getHeader("Location");

        assertNotNull(appointment.getId());

        assertNotNull(location);
        assertTrue(location.contains("http://localhost/v1/appointments"));

    }

    @Test
    public void shouldFailToCreateAppointmentWithInvalidRequestBody() throws Exception {
        AppointmentDto appointmentDto = new AppointmentDto(TimeRange.create(LocalDateTime.MIN, LocalDateTime.MAX), null, null);
        this.mvc.perform(
                        post("/v1/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(appointmentDto))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldFailToCreateAppointmentWithNotExistentCostumer() throws Exception {
        AppointmentDto appointmentDto = new AppointmentDto(TimeRange.create(LocalDateTime.MIN, LocalDateTime.MAX), 999L, 1L);
        this.mvc.perform(
                        post("/v1/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(appointmentDto))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldFailToCreateAppointmentWithNotExistentHairdresser() throws Exception {
        AppointmentDto appointmentDto = new AppointmentDto(TimeRange.create(LocalDateTime.MIN, LocalDateTime.MAX), 1L, 999L);
        this.mvc.perform(
                        post("/v1/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(appointmentDto))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldFailToCreateAppointmentWithOverlapTimeRange() throws Exception {
        AppointmentEntity appointmentEntity = new AppointmentEntity(
                null,
                new TimeRangeVo(LocalDateTime.now().minusHours(1L),LocalDateTime.now().plusHours(2L)),
                hairdresserEntity,
                costumerEntity,
                LocalDateTime.MAX,
                LocalDateTime.MAX,
                null
                );
        appointmentEntityRepository.save(appointmentEntity);
        AppointmentDto appointmentDto = new AppointmentDto(TimeRange.create(LocalDateTime.now(), LocalDateTime.now().plusHours(1L)), 1L, 1L);
        this.mvc.perform(
                        post("/v1/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(appointmentDto))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    public void shouldDeleteAppointmentSuccessfully() throws Exception {
        AppointmentEntity appointmentEntity = new AppointmentEntity(
                null,
                new TimeRangeVo(LocalDateTime.now().minusHours(1L),LocalDateTime.now().plusHours(2L)),
                hairdresserEntity,
                costumerEntity,
                LocalDateTime.MAX,
                LocalDateTime.MAX,
                null
        );

        AppointmentEntity save = appointmentEntityRepository.save(appointmentEntity);
        this.mvc.perform(
                        delete("/v1/appointments/"+save.getId())
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldFailToDeleteAppointment() throws Exception {
        this.mvc.perform(
                        delete("/v1/appointments/9999")
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetAppointments() throws Exception {
        AppointmentEntity appointmentEntity = new AppointmentEntity(
                null,
                new TimeRangeVo(LocalDateTime.now().minusHours(1L),LocalDateTime.now().plusHours(2L)),
                hairdresserEntity,
                costumerEntity,
                LocalDateTime.MAX,
                LocalDateTime.MAX,
                null
        );

        appointmentEntityRepository.save(appointmentEntity);
        MvcResult mvcResult = this.mvc.perform(
                        get("/v1/appointments")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("id"));
    }


    @Test
    public void shouldGetAppointmentsById() throws Exception {
        AppointmentEntity appointmentEntity = new AppointmentEntity(
                null,
                new TimeRangeVo(LocalDateTime.now().minusHours(1L),LocalDateTime.now().plusHours(2L)),
                hairdresserEntity,
                costumerEntity,
                LocalDateTime.MAX,
                LocalDateTime.MAX,
                null
        );

        AppointmentEntity save = appointmentEntityRepository.save(appointmentEntity);
        MvcResult mvcResult = this.mvc.perform(
                        get("/v1/appointments/"+save.getId())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("id"));
    }
}