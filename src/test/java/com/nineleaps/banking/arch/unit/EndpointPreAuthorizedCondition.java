package com.nineleaps.banking.arch.unit;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class EndpointPreAuthorizedCondition extends ArchCondition<JavaClass> {

    private static final Set<Class<? extends Annotation>> ENDPOINT_ANNOTATIONS =
            Stream.of(
                            GetMapping.class,
                            PutMapping.class,
                            DeleteMapping.class,
                            PostMapping.class,
                            PatchMapping.class)
                    .collect(Collectors.toSet());

    public EndpointPreAuthorizedCondition() {
        super("Check if endpoint methods are PreAuthorized on method or class level.");
    }

    @Override
    public void check(JavaClass clazz, ConditionEvents events) {
        if (!clazz.isAnnotatedWith(PreAuthorize.class)
                && !clazz.getAllMethods().stream()
                        .filter(
                                javaMethod ->
                                        ENDPOINT_ANNOTATIONS.stream()
                                                .anyMatch(javaMethod::isAnnotatedWith))
                        .anyMatch(javaMethod -> javaMethod.isAnnotatedWith(PreAuthorize.class))) {
            events.add(
                    new SimpleConditionEvent(
                            clazz,
                            false,
                            clazz.getSimpleName()
                                    + " should have all endpoints PreAuthorized on class or method level."));
        }
    }
}
