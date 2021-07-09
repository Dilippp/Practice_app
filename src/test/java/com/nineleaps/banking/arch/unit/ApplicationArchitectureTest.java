package com.nineleaps.banking.arch.unit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationArchitectureTest {

    @Test
    public void services_should_only_be_accessed_by_controllers() {

        JavaClasses importedClasses =
                new ClassFileImporter().importPackages("com.nineleaps.banking.service");

        ArchRule archRule =
                classes()
                        .that()
                        .resideInAPackage("..service..")
                        .should()
                        .onlyBeAccessed()
                        .byAnyPackage("..controller..", "..service..");

        archRule.check(importedClasses);

        ArchRule archRule2 =
                classes()
                        .that()
                        .resideInAPackage("..service..")
                        .should()
                        .haveSimpleNameEndingWith("Service");

        archRule2.check(importedClasses);
    }
}
