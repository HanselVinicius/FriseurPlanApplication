package com.hansel.FriseurPlan.core.application.usecase.appointment.command;

import com.hansel.FriseurPlan.core.application.adapter.appointment.command.AppointmentCommandClient;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentDto;
import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.costumer.query.CostumerQueryUseCase;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.query.HairdresserQueryUseCase;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.port.output.entities.AddressVo;
import com.hansel.FriseurPlan.infra.port.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.vo.TimeRangeVo;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentCommandUseCaseTest {

    @Mock
    private CostumerQueryUseCase costumerQueryUseCase;
    @Mock
    private HairdresserQueryUseCase hairdresserQueryUseCase;
    @Mock
    private AppointmentCommandClient appointmentCommandClient;
    @InjectMocks
    private AppointmentCommandUseCase appointmentCommandUseCase;

    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private TimeRange timeRange;
    private Costumer costumer;
    private HairdresserEntity hairdresserEntity;
    private Hairdresser hairdresser;
    private Appointment appointment;
    private AppointmentEntity appointmentEntity;
    private CostumerEntity costumerEntity;
    private AppointmentDto  appointmentDto;
    private CostumerReturnDto  costumerReturnDto;
    private HairdresserReturnDto hairdresserReturnDto;
    private AppointmentReturnDto appointmentReturnDto;

    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
        this.hairdresserEntity = new HairdresserEntity(null, "John Doe", EmailVo.fromEmailDomain(email),phoneNumber, AddressVo.fromAddressDomain(address), LocalDateTime.MAX, null,null);
        this.hairdresser = Hairdresser.create(1L, "John Doe", null, phoneNumber, email, address);
        this.timeRange = TimeRange.create(LocalDateTime.MIN,LocalDateTime.MAX);
        this.costumer = Costumer.create(1L, "teste", "16992977903",email);
        this.appointment = Appointment.create(null,timeRange,this.costumer,this.hairdresser);
        this.costumerEntity  = new CostumerEntity(null,"name",phoneNumber,EmailVo.fromEmailDomain(email),LocalDateTime.MAX,null,null);
        this.appointmentEntity = new AppointmentEntity(null, TimeRangeVo.fromTimeRangeDomain(timeRange),hairdresserEntity,costumerEntity,LocalDateTime.MAX,null,null);
        this.appointmentDto = new AppointmentDto(this.timeRange,costumer.getId(),hairdresser.getId());
        this.costumerReturnDto = CostumerReturnDto.fromDomain(costumer);
        this.hairdresserReturnDto = HairdresserReturnDto.fromDomain(hairdresser);
        this.appointmentReturnDto = new AppointmentReturnDto(1L,this.timeRange,costumerReturnDto,hairdresserReturnDto);
    }




    @Test
    public void shouldCreateAppointment() {
        when(this.costumerQueryUseCase.getCostumerById(any())).thenReturn(costumerReturnDto);
        when(this.hairdresserQueryUseCase.getHairdresserById(any())).thenReturn(hairdresserReturnDto);

        this.appointmentCommandUseCase.createAppointment(this.appointmentDto);

        verify(appointmentCommandClient).createAppointment(any(Appointment.class));
    }

    @Test
    public void shouldFailCreateAppointment() {
        this.hairdresserReturnDto = new HairdresserReturnDto(
                hairdresser.getId(),
                hairdresser.getName(),
                hairdresser.getPhoneNumber().getNumber(),
                hairdresser.getEmail(),
                hairdresser.getAddress(),
                List.of(appointmentReturnDto));

        when(this.costumerQueryUseCase.getCostumerById(any())).thenReturn(costumerReturnDto);
        when(this.hairdresserQueryUseCase.getHairdresserById(any())).thenReturn(hairdresserReturnDto);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                this.appointmentCommandUseCase.createAppointment(appointmentDto));

        assertEquals("Appointment time overlaps with an existing appointment", exception.getMessage());

    }


}