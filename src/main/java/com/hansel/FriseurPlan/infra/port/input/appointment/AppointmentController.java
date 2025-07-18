package com.hansel.FriseurPlan.infra.port.input.appointment;

import com.hansel.FriseurPlan.core.application.usecase.appointment.command.AppointmentCommandUseCase;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.GetAppointmentDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.query.AppointmentQueryUseCase;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<AppointmentReturnDto>> getAllAppointments(
            @RequestParam(name = "costumerId",required = false) Long costumerId,
            @RequestParam(name = "hairdresserId",required = false) Long hairdresserId,
            Pageable pageable
    ){
        GetAppointmentDto getAppointmentDto = new GetAppointmentDto(
                costumerId,
                hairdresserId,
                pageable
        );
        Page<AppointmentReturnDto> appointmentReturnDtoList = this.appointmentQueryUseCase.getAppointments(getAppointmentDto);
        return ResponseEntity.ok(appointmentReturnDtoList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppointmentById(@PathVariable Long id){
        this.appointmentCommandUseCase.deleteAppointmentById(id);
        return ResponseEntity.noContent().build();
    }




}
