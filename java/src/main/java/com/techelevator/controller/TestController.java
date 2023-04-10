package com.techelevator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestController {

    public TestController(){

    }

    @GetMapping("/test")
    public String[] testMethod(){
        return new String[] {"Hello!", "Greetings from the back end!", "Testing 1 2 3.", "Is this thing on?"};
    }


}
