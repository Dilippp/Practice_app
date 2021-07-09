package com.nineleaps.banking.config.common.module;

import com.nineleaps.banking.config.AbstractFlywayConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
public class BankServiceFlywayConfiguration extends AbstractFlywayConfiguration {

    public BankServiceFlywayConfiguration(DataSource dataSource) {
        super("bank"); //module name is basically database name of different different modules.
        migrate(dataSource);
    }

    @Bean
    public Boolean bankServiceFlywayConfigurationCompleted() {
        return true;
    }
}
