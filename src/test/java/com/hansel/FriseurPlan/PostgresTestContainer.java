package com.hansel.FriseurPlan;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.concurrent.atomic.AtomicBoolean;

public class PostgresTestContainer implements BeforeAllCallback {

    static AtomicBoolean postgresContainerStarted = new AtomicBoolean(false);

    @Container
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:17-alpine")
            .withUsername("root")
            .withPassword("mariadbrootPW")
            .withDatabaseName("friseurplan")
            .withExposedPorts(5432);

    @Override
    public void beforeAll(ExtensionContext context) {
        if (postgresContainerStarted.get()) {
            return;
        }

        postgresContainer.start();
        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
        postgresContainerStarted.set(true);
    }
}
