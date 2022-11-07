package alonzo.carlos.spring.controller;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class GreetingController {

    @RequestMapping
    public String greetClient(){
        return "Well, hello there. Welcome to your world.";
    }


}
