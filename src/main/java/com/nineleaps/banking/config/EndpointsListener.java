package com.nineleaps.banking.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@Slf4j
public class EndpointsListener {

    List<String> allEndpoints = new ArrayList<>();

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        RequestMappingHandlerMapping requestMappingHandlerMapping =
                applicationContext.getBean(
                        "requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map =
                requestMappingHandlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> requestMappingInfos = map.keySet();
        allEndpoints.addAll(
                requestMappingInfos.stream()
                        .map(RequestMappingInfo::toString)
                        .collect(Collectors.toList()));
    }

    @Getter
    public static class CustomEndpoints {
        List<String> endpoints;

        CustomEndpoints(List<String> endpoints) {
            this.endpoints = endpoints;
        }
    }

    @Bean
    public CustomEndpoints customEndpoints() {
        return new CustomEndpoints(allEndpoints);
    }
}
