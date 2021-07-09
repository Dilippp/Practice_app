package com.nineleaps.banking.config;

import com.nineleaps.banking.security.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @RequiredArgsConstructor
    @Profile("production")
    public static class SimpleSecurityConfig extends WebSecurityConfigurerAdapter {

        private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

        @Value("${service.auth.token:}")
        private String authToken;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf()
                    .disable()
                    .cors()
                    .and()
                    .exceptionHandling()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/actuator/health", "/webhook/**")
                    .permitAll()
                    .anyRequest()
                    .hasAnyRole("USER")
                    .and()
                    .httpBasic()
                    .authenticationEntryPoint(restAuthenticationEntryPoint);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("service")
                    .password(passwordEncoder().encode(authToken))
                    .roles("USER");
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @Profile("local")
    @Configuration
    public static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity webSecurity) throws Exception {
            webSecurity.ignoring().antMatchers("/**");
        }
    }
}
