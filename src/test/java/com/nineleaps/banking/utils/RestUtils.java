package com.nineleaps.banking.utils;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class RestUtils {

    private static final String USER_GUID_HEADER = "X-User-GUID";
    @Getter private final ObjectMapper objectMapper;
    private int localServerPort;
    @Getter @Setter protected String userGuidHeaderValue = "service";

    public RestUtils(int localServerPort) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setDefaultPropertyInclusion(NON_NULL);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        this.objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(false));
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Date.class, new DateDeserializers.SqlDateDeserializer());
        this.objectMapper.registerModules(new JavaTimeModule(), simpleModule);
        this.localServerPort = localServerPort;
    }

    public ValidatableResponse post(String endpoint, int responseCode) {
        return RestAssured.given()
                .with()
                .contentType(APPLICATION_JSON_VALUE)
                .header(USER_GUID_HEADER, userGuidHeaderValue)
                .post("http://localhost:" + localServerPort + endpoint)
                .then()
                .statusCode(responseCode);
    }

    public <T> ValidatableResponse post(String endpoint, T data) {
        return postPayload(endpoint, data, 200);
    }

    @SneakyThrows
    public <T, R> R post(String endpoint, T data, Class<R> returnType) {
        String response = post(endpoint, data).extract().asString();
        return objectMapper.readerFor(returnType).readValue(response);
    }

    @SneakyThrows
    public <T> ValidatableResponse postPayload(String endpoint, T payload, int responseCode) {
        return RestAssured.given()
                .with()
                .contentType(APPLICATION_JSON_VALUE)
                .header(USER_GUID_HEADER, userGuidHeaderValue)
                .body(objectMapper.writeValueAsString(payload))
                .post("http://localhost:" + localServerPort + endpoint)
                .then()
                .statusCode(responseCode);
    }

    @SneakyThrows
    public <T> ValidatableResponse putPayload(String endpoint, T payload, int responseCode) {
        return RestAssured.given()
                .with()
                .contentType(APPLICATION_JSON_VALUE)
                .header(USER_GUID_HEADER, userGuidHeaderValue)
                .body(objectMapper.writeValueAsString(payload))
                .put("http://localhost:" + localServerPort + endpoint)
                .then()
                .statusCode(responseCode);
    }

    public ValidatableResponse get(String endpoint) {
        return RestAssured.given()
                .with()
                .contentType(APPLICATION_JSON_VALUE)
                .header(USER_GUID_HEADER, userGuidHeaderValue)
                .get("http://localhost:" + localServerPort + endpoint)
                .then()
                .statusCode(200);
    }
}
