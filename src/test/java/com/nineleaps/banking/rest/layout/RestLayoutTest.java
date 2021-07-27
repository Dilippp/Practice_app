package com.nineleaps.banking.rest.layout;

import static org.apache.commons.lang.CharEncoding.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

import com.nineleaps.banking.BankingAppApplication;
import com.nineleaps.banking.config.EndpointsListener;
import com.nineleaps.banking.test.BasicIntegrationTest;
import com.nineleaps.banking.utils.RestUtils;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.assertj.core.internal.Diff;
import org.assertj.core.util.diff.Delta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(
        classes = BankingAppApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @ActiveProfiles("integration")
public class RestLayoutTest extends BasicIntegrationTest {

    @Autowired private EndpointsListener.CustomEndpoints customEndpoints;

    @LocalServerPort protected int port;
    @Getter protected RestUtils restUtils;

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        restUtils = new RestUtils(port);
    }

    @Test
    public void compareRestLayouts() throws Exception {
        List<String> endpoints = customEndpoints.getEndpoints();
        endpoints.sort(String::compareTo);
        String newLayout = endpoints.stream().map(s -> s.trim()).collect(Collectors.joining("\n"));

        FileUtils.writeStringToFile(
                new File("src/test/resources/approved/rest-layout"), newLayout, UTF_8);

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
