package com.hansel.FriseurPlan.core.application.usecase.appointment.query;

import com.hansel.FriseurPlan.core.application.adapter.appointment.query.AppointmentQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

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
    private TimeRange timeRange;


    private AppointmentReturnDto appointmentReturnDto;
    @BeforeEach
    void setUp() {
        appointmentId = 1L;
        timeRange = TimeRange.create(LocalDateTime.MIN, LocalDateTime.MAX);
        appointmentReturnDto = new AppointmentReturnDto(
                appointmentId,
                timeRange,
                null,
                null
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
}