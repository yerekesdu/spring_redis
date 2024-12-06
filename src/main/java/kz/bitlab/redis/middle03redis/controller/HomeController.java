package kz.bitlab.redis.middle03redis.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/home")
    public String home()  {
        return "Hello World";
    }

    @GetMapping(value = "/current-session")
    public String currentSession(HttpSession session)  {
        return "Current Session" + session;
    }

}
