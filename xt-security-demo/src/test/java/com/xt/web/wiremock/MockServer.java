package com.xt.web.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
public class MockServer {

    private void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("wiremock/response/" + file + ".txt");
        String content = StringUtils.join(
                FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");

        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withBody(content)
                        .withStatus(200)));
    }

    @Test
    public void exampleTest() throws IOException {
        configureFor(8080);
        removeAllMappings();

        mock("/order/1", "01");
        mock("/order/3", "02");
    }
}
