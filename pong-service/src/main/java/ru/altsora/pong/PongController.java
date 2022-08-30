package ru.altsora.pong;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class PongController {

    @GetMapping("/")
    public String whoAmI() {
        return "I am pong service";
    }

    @GetMapping("/pong")
    public String pong() {
        log.info("call /pong");
        return "Pong";
    }
}
