package com.ayoubtrd.links.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("")
    private String home() {
        return "<h1>Hello world</h1>";
    }
}
