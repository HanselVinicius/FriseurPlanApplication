package com.hansel.FriseurPlan.infra.port.input.appointment;

import com.hansel.FriseurPlan.core.application.usecase.appointment.command.AppointmentCommandUseCase;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentDto;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentCommandUseCase appointmentCommandUseCase;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        Appointment appointment = this.appointmentCommandUseCase.createAppointment(appointmentDto);
        return ResponseEntity.ok(appointment);
    }
}
