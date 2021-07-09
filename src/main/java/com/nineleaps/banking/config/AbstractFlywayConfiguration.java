package com.nineleaps.banking.config;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;

import javax.sql.DataSource;

@RequiredArgsConstructor
public abstract class AbstractFlywayConfiguration {

    private final String moduleName;

    protected void migrate(DataSource dataSource) {
        Flyway.configure()
                .dataSource(dataSource)
                .defaultSchema(moduleName)
                .schemas(moduleName)
                .baselineOnMigrate(true)
                .baselineVersion(MigrationVersion.LATEST)
                .locations("db/" + moduleName + "/migration")
                .load()
                .migrate();
    }
}
