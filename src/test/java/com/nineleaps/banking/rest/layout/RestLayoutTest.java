package com.nineleaps.banking.rest.layout;

import static org.apache.commons.lang.CharEncoding.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.restassured.path.json.JsonPath;
import com.nineleaps.banking.BankingAppApplication;
import com.nineleaps.banking.test.BasicIntegrationTest;
import com.nineleaps.banking.utils.RestUtils;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.internal.Diff;
import org.assertj.core.util.diff.Delta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(
        classes = BankingAppApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @ActiveProfiles("integration")
public class RestLayoutTest extends BasicIntegrationTest {

    @LocalServerPort protected int port;
    @Getter protected RestUtils restUtils;

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        restUtils = new RestUtils(port);
    }

    @Disabled
    @Test
    public void compareRestLayouts() throws Exception {
        JsonPath allMappings = restUtils.get("/actuator/mappings").extract().body().jsonPath();
        List<String> endpoints =
                allMappings.get(
                        "contexts.application.mappings.dispatcherServlets.dispatcherServlet*.predicate");
        endpoints.sort(String::compareTo);
        String newLayout = StringUtils.join(endpoints, System.getenv("line.separator"));

        FileUtils.writeStringToFile(
                new File("target/test-classes/approved/rest-layout"), newLayout, UTF_8);

        Diff diff = new Diff();
        List<Delta<String>> deltas =
                diff.diff(
                        new File("src/test/resources/approved/rest-layout"),
                        newLayout,
                        StandardCharsets.UTF_8);
        assertThat(deltas.size())
                .withFailMessage(
                        "Deltas: \n"
                                + deltas.stream()
                                        .map(Delta::toString)
                                        .collect(Collectors.joining()))
                .isZero();
    }
}
