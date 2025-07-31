package com.hansel.FriseurPlan;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:17-alpine")
            .withUsername("root")
            .withPassword("mariadbrootPW")
            .withDatabaseName("friseurplan")
            .withExposedPorts(5432);

    @Container
    static final KeycloakContainer keycloakContainer = new KeycloakContainer("quay.io/keycloak/keycloak:26.2")
            .withRealmImportFile("test-realm-export.json");


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
//        String host = keycloakContainer.getHost();
//        Integer port = keycloakContainer.getMappedPort(8080);
//        String issuerUri = "http://" + host + ":" + port + "/realms/FriseurPlanRealm";
//        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri", () -> issuerUri);
//        registry.add("spring.security.oauth2.client.provider.keycloak.issuer-uri", () -> issuerUri);
    }
}
