package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class HomeController {

    // http://localhost:8080/
    @GetMapping("/") // 슬래쉬가 8080을 말함
    public String getHome() {
        log.info("home 요청");
        return "home"; // return -> template 파일 이름이 되어야함.
    }

    // http://localhost:8080/basic
    @GetMapping("/basic")
    public String getMethodName() {
        return "info";
    }

}
