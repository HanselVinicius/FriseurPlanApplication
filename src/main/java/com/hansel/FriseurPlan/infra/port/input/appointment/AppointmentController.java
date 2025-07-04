package com.hansel.FriseurPlan.infra.port.input.appointment;

import com.hansel.FriseurPlan.core.application.usecase.appointment.command.AppointmentCommandUseCase;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.query.AppointmentQueryUseCase;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentCommandUseCase appointmentCommandUseCase;
    private final AppointmentQueryUseCase  appointmentQueryUseCase;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        Appointment appointment = this.appointmentCommandUseCase.createAppointment(appointmentDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(appointment.getId())
                .toUri();
        return ResponseEntity.created(location).body(appointment);
    }

    @GetMapping("{id}")
    public ResponseEntity<AppointmentReturnDto> getAppointmentById(@PathVariable Long id){
        AppointmentReturnDto appointment = this.appointmentQueryUseCase.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }
}
