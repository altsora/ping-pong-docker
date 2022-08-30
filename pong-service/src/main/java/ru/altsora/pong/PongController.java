package ru.altsora.pong;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Log4j2
@RestController
public class PongController {
    @Value("${service.ping.url}")
    private String pingServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    private void init() {
        log.info("pingServiceUrl = [{}]", pingServiceUrl);
    }

    @GetMapping("/")
    public String whoAmI() {
        return "I am pong service!";
    }

    @GetMapping("/pong")
    public String pong() {
        log.info("call /pong");
        return "Pong";
    }

    @GetMapping("/ping")
    public String callPing() {
        log.info("call /ping");
        try {
            final String response = restTemplate.getForObject(pingServiceUrl, String.class);
            return "Pong <=== " + response;
        } catch (RestClientException ex) {
            log.error("failed to call ping-service by URL [{}]", pingServiceUrl, ex);
            return "Pong <===";
        }
    }
}
