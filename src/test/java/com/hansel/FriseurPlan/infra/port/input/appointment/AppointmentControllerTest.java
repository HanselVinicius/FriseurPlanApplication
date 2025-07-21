package com.hansel.FriseurPlan.infra.port.input.appointment;

import com.hansel.FriseurPlan.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class AppointmentControllerTest extends BaseIntegrationTest {



    @Test
    public void shouldCreateAppointment() {
        assert true;
    }

}