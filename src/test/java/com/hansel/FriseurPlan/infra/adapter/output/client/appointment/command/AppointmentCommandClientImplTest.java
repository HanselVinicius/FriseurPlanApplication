package com.hansel.FriseurPlan.infra.adapter.output.client.appointment.command;

import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.adapter.output.entities.AddressVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.appointment.AppointmentEntity;
import com.hansel.FriseurPlan.infra.adapter.output.entities.appointment.AppointmentEntityRepository;
import com.hansel.FriseurPlan.infra.adapter.output.entities.appointment.vo.TimeRangeVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.adapter.output.entities.hairdresser.HairdresserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AppointmentCommandClientImplTest {


    @Mock
    private AppointmentEntityRepository  appointmentEntityRepository;
    @InjectMocks
    private AppointmentCommandClientImpl appointmentCommandClientImpl;

    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private TimeRange timeRange;
    private Costumer  costumer;
    private HairdresserEntity hairdresserEntity;
    private Hairdresser hairdresser;
    private Appointment  appointment;
    private AppointmentEntity appointmentEntity;
    private CostumerEntity  costumerEntity;


    @BeforeEach
    void setUp() {
        this.email = Email.create("hanelvinicius@gmail.com", true);
        this.phoneNumber = PhoneNumber.create("16992977903");
        this.address = Address.create("123 Main St", 100, "Springfield", "SP", 12345678L);
        this.hairdresserEntity = new HairdresserEntity(null, "John Doe", EmailVo.fromEmailDomain(email),phoneNumber, AddressVo.fromAddressDomain(address), LocalDateTime.MAX, null,null);
        this.hairdresser = Hairdresser.create(null, "John Doe", null, phoneNumber, email, address);
        this.timeRange = TimeRange.create(LocalDateTime.MAX,LocalDateTime.MAX);
        this.costumer = Costumer.create(null, "teste", "16992977903",email);
        this.appointment = Appointment.create(null,timeRange,this.costumer,this.hairdresser);
        this.costumerEntity  = new CostumerEntity(null,"name",phoneNumber,EmailVo.fromEmailDomain(email),LocalDateTime.MAX,null,null);
        this.appointmentEntity = new AppointmentEntity(null, TimeRangeVo.fromTimeRangeDomain(timeRange),hairdresserEntity,costumerEntity,LocalDateTime.MAX,null,null);
    }

    @Test
    public void shouldCreateAppointment() {
        when(this.appointmentEntityRepository.save(any())).thenReturn(appointmentEntity);
        this.appointmentCommandClientImpl.createAppointment(appointment);
        verify(appointmentEntityRepository).save(any(AppointmentEntity.class));
    }

    @Test
    public void shouldDeleteAppointment() {
        this.appointmentCommandClientImpl.deleteAppointment(1L);
        verify(appointmentEntityRepository).deleteAppointment(eq(1L), any());
    }

}