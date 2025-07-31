package com.hansel.FriseurPlan;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith({
        PostgresTestContainer.class,
        KeycloakTestContainer.class,
        SpringExtension.class
})
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@ImportAutoConfiguration
@SpringBootTest(classes = {FriseurPlanApplication.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@ActiveProfiles("test")
public @interface IntegrationTest {
}
