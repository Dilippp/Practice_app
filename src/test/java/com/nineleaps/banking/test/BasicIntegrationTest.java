package com.nineleaps.banking.test;

import com.nineleaps.banking.config.MysqlTestContainerInitializer;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(initializers = MysqlTestContainerInitializer.class)
public class BasicIntegrationTest {

    protected void setUp() throws Exception {}
}
