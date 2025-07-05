package com.hansel.FriseurPlan.infra.port.output.client.appointment.query;

import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.GetAppointmentDto;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.port.output.client.appointment.specifications.GetAppointmentByRelationSpecs;
import com.hansel.FriseurPlan.infra.port.output.entities.AddressVo;
import com.hansel.FriseurPlan.infra.port.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntityRepository;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.vo.TimeRangeVo;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentQueryClientImplTest {

    @Mock
    private AppointmentEntityRepository appointmentEntityRepository;
    @Mock
    private GetAppointmentByRelationSpecs  getAppointmentByRelationSpecs;
    @InjectMocks
    private AppointmentQueryClientImpl appointmentQueryClient;

    private Long appointmentId;
    private Long hairdresserId;
    private Long costumerId;
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
    private GetAppointmentDto  getAppointmentDto;


    @BeforeEach
    void setUp() {
        this.appointmentId = 1L;
        this.hairdresserId = 1L;
        this.costumerId = 1L;
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
        this.getAppointmentDto = new GetAppointmentDto(
                costumerId,
                hairdresserId,
                Pageable.ofSize(10)
        );

    }

    @Test
    void shouldGetAppointmentById() {
        when(this.appointmentEntityRepository.findById(appointmentId)).thenReturn(Optional.of(appointmentEntity));
        this.appointmentQueryClient.getAppointmentById(appointmentId);
        verify(this.appointmentEntityRepository).findById(appointmentId);
    }

    @Test
    public void getAppointments(){
        PageImpl<AppointmentEntity> appointmentsPage = new PageImpl<>(List.of(this.appointmentEntity));
        when(appointmentEntityRepository.findAll(any(GetAppointmentByRelationSpecs.class),any(Pageable.class))).thenReturn(appointmentsPage);
        Page<AppointmentReturnDto> result = this.appointmentQueryClient.getAppointments(this.getAppointmentDto);
        verify(this.appointmentEntityRepository).findAll(any(GetAppointmentByRelationSpecs.class),any(Pageable.class));
        assertEquals(result.getTotalElements(), appointmentsPage.getTotalElements());
        assertEquals(result.getTotalPages(), appointmentsPage.getTotalPages());
        assertEquals(result.getContent().getFirst().id(), appointmentsPage.getContent().getFirst().getId());
    }

}