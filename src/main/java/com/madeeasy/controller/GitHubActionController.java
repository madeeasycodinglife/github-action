package com.madeeasy.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/github")
public class GitHubActionController {

    @GetMapping(path = "/")
    public String home() {
        return "🚀 CI/CD Works! Updated via GitHub Actions.";
    }

    @GetMapping(path = "/hello")
    public String sayHello() {
        return "👋 Hello from GitHub Actions Deployed App!";
    }

    @GetMapping(path = "/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("service", "user-service");
        status.put("status", "UP");
        status.put("timestamp", new Date());
        return status;
    }

    @PostMapping(path = "/submit")
    public Map<String, Object> submitData(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Data received successfully!");
        response.put("received", request);
        return response;
    }

    @PutMapping(path = "/update/{id}")
    public Map<String, Object> updateItem(@PathVariable String id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Updated successfully for ID: " + id);
        response.put("updated", request);
        return response;
    }

    @DeleteMapping(path = "/delete/{id}")
    public Map<String, String> deleteItem(@PathVariable String id) {
        return Collections.singletonMap("message", "Deleted item with ID: " + id);
    }
}

