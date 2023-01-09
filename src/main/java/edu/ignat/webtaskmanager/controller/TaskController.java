package edu.ignat.webtaskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
