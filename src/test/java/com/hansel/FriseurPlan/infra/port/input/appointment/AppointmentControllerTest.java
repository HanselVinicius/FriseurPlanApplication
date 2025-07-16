package com.hansel.FriseurPlan.infra.port.input.appointment;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class AppointmentControllerTest {

    @Container
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:17-alpine")
            .withUsername("root")
            .withPassword("mariadbrootPW")
            .withDatabaseName("friseurplan")
            .withExposedPorts(5432);

    @Container
    static final GenericContainer<?> keycloakContainer = new GenericContainer<>("quay.io/keycloak/keycloak:26.2")
            .withExposedPorts(8080)
            .withEnv("KEYCLOAK_ADMIN", "admin")
            .withEnv("KEYCLOAK_ADMIN_PASSWORD", "admin")
            .withEnv("KC_DB", "dev-file")
            .withCommand("start-dev")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("test-realm-export.json"),
                    "/opt/keycloak/data/import/test-realm-export.json"
            )
            .withEnv("KEYCLOAK_IMPORT", "/opt/keycloak/data/import/test-realm-export.json");


    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
        keycloakContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgresContainer.stop();
        keycloakContainer.stop();
    }


    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        String host = keycloakContainer.getHost();
        Integer port = keycloakContainer.getMappedPort(8080);
        String issuerUri = "http://" + host + ":" + port + "/realms/FriseurPlanRealm";
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri", () -> issuerUri);
        registry.add("spring.security.oauth2.client.provider.keycloak.issuer-uri", () -> issuerUri);
    }

    @Test
    public void shouldCreateAppointment() {
        assert true;
    }

}