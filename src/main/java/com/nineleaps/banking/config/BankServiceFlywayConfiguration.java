package com.nineleaps.banking.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class BankServiceFlywayConfiguration extends AbstractFlywayConfiguration {

    public BankServiceFlywayConfiguration(DataSource dataSource) {
        super("bank"); // module name is basically database name of different different modules.
        migrate(dataSource);
    }

    @Bean
    public Boolean bankServiceFlywayConfigurationCompleted() {
        return true;
    }
}
