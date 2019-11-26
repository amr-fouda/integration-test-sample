package com.example.demo.integration;

import com.example.demo.DemoApplication;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.UserValidationService;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@TestPropertySource(
        locations = "classpath:application.properties")
public class UserServiceWithFakeExternalServiceIT {

    final String SAMPLE_EMAIL = "a@a.com";
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidationService userValidationService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUserTestWithInvalidUserId() {
        wireMockRule.stubFor(get(urlPathEqualTo("/some-test-api-key"))
                .willReturn(aResponse()
                        .withBody("Hello world")
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        RestTemplate rt = new RestTemplate();
        String result = rt.getForEntity("http://localhost:8089/some-test-api-key", String.class).getBody();

        Assert.assertTrue(result.equalsIgnoreCase("Hello world"));
    }

}
