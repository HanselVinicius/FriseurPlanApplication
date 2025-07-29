package com.hansel.FriseurPlan.core.application.usecase.appointment.query;

import com.hansel.FriseurPlan.core.application.port.appointment.query.AppointmentQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.GetAppointmentDto;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentQueryUseCaseTest {

    @Mock
    private AppointmentQueryClient appointmentQueryClient;
    @InjectMocks
    private AppointmentQueryUseCase appointmentQueryUseCase;

    private Long appointmentId;
    private Long hairdresserId;
    private Long costumerId;
    private TimeRange timeRange;
    private GetAppointmentDto  getAppointmentDto;

    private AppointmentReturnDto appointmentReturnDto;
    @BeforeEach
    void setUp() {
        appointmentId = 1L;
        hairdresserId = 1L;
        costumerId = 1L;
        timeRange = TimeRange.create(LocalDateTime.MIN, LocalDateTime.MAX);
        appointmentReturnDto = new AppointmentReturnDto(
                appointmentId,
                timeRange,
                null,
                null
        );
        getAppointmentDto = new GetAppointmentDto(
                costumerId,
                hairdresserId,
                Pageable.ofSize(10)
        );

    }

    @Test
    void shouldGetAppointmentById() {
        when(this.appointmentQueryClient.getAppointmentById(appointmentId)).thenReturn(appointmentReturnDto);
        AppointmentReturnDto appointmentById = this.appointmentQueryUseCase.getAppointmentById(appointmentId);

        verify(appointmentQueryClient).getAppointmentById(appointmentId);
        assertNotNull(appointmentById);
        assertEquals(appointmentId,appointmentById.id());
        assertEquals(timeRange.getStartTime(),appointmentById.timeRange().getStartTime());
        assertEquals(timeRange.getEndTime(),appointmentById.timeRange().getEndTime());
    }

    @Test
    void shouldGetAppointments() {
        PageImpl<AppointmentReturnDto> appointments = new PageImpl<>(List.of(appointmentReturnDto));
        when(this.appointmentQueryClient.getAppointments(getAppointmentDto)).thenReturn(appointments);
        Page<AppointmentReturnDto> appointmentsPage = this.appointmentQueryUseCase.getAppointments(getAppointmentDto);

        verify(this.appointmentQueryClient).getAppointments(getAppointmentDto);
        assertNotNull(appointmentsPage);
        assertEquals(appointmentsPage.getTotalElements(), appointmentsPage.getTotalElements());
        assertEquals(appointmentsPage.getTotalPages(), appointmentsPage.getTotalPages());
        assertEquals(appointmentsPage.getNumber(), appointmentsPage.getNumber());
        assertEquals(appointmentsPage.getNumberOfElements(), appointmentsPage.getNumberOfElements());
        assertEquals(appointmentsPage.getContent(), appointmentsPage.getContent());
    }


}