package com.srikandi.winterauth.resourceserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
public class PublicController {

    @GetMapping("/welcome")
    public @ResponseBody
    String getGreeting() {
        return "Hey Good Day!";
    }

}