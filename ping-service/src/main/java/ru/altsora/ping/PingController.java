package ru.altsora.ping;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RestController
public class PingController {
    @Value("${pong-service.url}")
    private String pongServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String whoAmI() {
        return "I am ping service!";
    }

    @GetMapping("/ping")
    public String ping() {
        log.info("call /ping");
        try {
            final String response = restTemplate.getForObject(pongServiceUrl, String.class);
            return "Ping ... " + response;
        } catch (RestClientException ex) {
            log.error("failed to call pong-service by URL [{}]", pongServiceUrl, ex);
            return "Ping ...";
        }
    }
}
