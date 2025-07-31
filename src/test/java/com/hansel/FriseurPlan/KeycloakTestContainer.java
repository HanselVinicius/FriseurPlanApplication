package com.hansel.FriseurPlan;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.junit.jupiter.Container;

import java.util.concurrent.atomic.AtomicBoolean;

public class KeycloakTestContainer implements BeforeAllCallback {

    static AtomicBoolean keycloakContainerStarted = new AtomicBoolean(false);


    @Container
    static final KeycloakContainer keycloakContainer = new KeycloakContainer("quay.io/keycloak/keycloak:26.2")
            .withRealmImportFile("test-realm-export.json");


    @Override
    public void beforeAll(ExtensionContext context) {
        if (keycloakContainerStarted.get()) {
            return;
        }
        keycloakContainer.start();
        String host = keycloakContainer.getHost();
        Integer port = keycloakContainer.getMappedPort(8080);
        String issuerUri = "http://" + host + ":" + port + "/realms/FriseurPlanRealm";
        System.setProperty("spring.security.oauth2.resourceserver.jwt.issuer-uri", issuerUri);
        System.setProperty("spring.security.oauth2.client.provider.keycloak.issuer-uri", issuerUri);
        keycloakContainerStarted.set(true);
    }


}
