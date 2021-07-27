package com.nineleaps.banking.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;

// https://www.baeldung.com/docker-test-containers
// https://www.baeldung.com/spring-boot-testcontainers-integration-test

public class MysqlTestContainerInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static MySQLContainer mySQLContainer;

    static {
        mySQLContainer = new MySQLContainer("mysql:latest");
        mySQLContainer.withDatabaseName("test");
        mySQLContainer.withUsername("root");
        mySQLContainer.withPassword("root");
        mySQLContainer.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                        "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
                        "spring.datasource.username=" + mySQLContainer.getUsername(),
                        "spring.datasource.password=" + mySQLContainer.getPassword())
                .applyTo(applicationContext.getEnvironment());
    }
}
