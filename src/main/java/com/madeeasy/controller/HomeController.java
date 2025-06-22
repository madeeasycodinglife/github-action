package com.madeeasy.controller;


import com.madeeasy.dto.ApiEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<ApiEndpoint> endpoints = List.of(
                new ApiEndpoint("GET", "/api/github/", "Home endpoint"),
                new ApiEndpoint("GET", "/api/github/hello", "Greeting endpoint"),
                new ApiEndpoint("GET", "/api/github/status", "Service status"),
                new ApiEndpoint("POST", "/api/github/submit", "Submit data"),
                new ApiEndpoint("PUT", "/api/github/update/{id}", "Update item"),
                new ApiEndpoint("DELETE", "/api/github/delete/{id}", "Delete item")
        );

        model.addAttribute("endpoints", endpoints);
        model.addAttribute("title", "API Documentation");
        model.addAttribute("swaggerUrl", "/swagger-ui.html");
        return "index";
    }
}

