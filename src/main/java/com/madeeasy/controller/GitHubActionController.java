package com.madeeasy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
@Tag(name = "GitHub CI/CD", description = "Endpoints for testing CI/CD integration via GitHub Actions.")
public class GitHubActionController {

    @Operation(summary = "Home endpoint", description = "Checks if the CI/CD pipeline is working correctly.")
    @ApiResponse(responseCode = "200", description = "Pipeline is functioning")
    @GetMapping("/")
    public String home() {
        return "🚀 CI/CD Works! Updated via GitHub Actions.";
    }

    @Operation(summary = "Say Hello", description = "Returns a greeting message from the deployed app.")
    @ApiResponse(responseCode = "200", description = "Greeting response")
    @GetMapping("/hello")
    public String sayHello() {
        return "👋 Hello from GitHub Actions Deployed App!";
    }

    @Operation(summary = "Service Status", description = "Returns the status of the user service.")
    @ApiResponse(responseCode = "200", description = "Service status returned successfully")
    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("service", "user-service");
        status.put("status", "UP");
        status.put("timestamp", new Date());
        return status;
    }

    @Operation(summary = "Submit Data", description = "Receives and echoes back submitted data.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Data received and returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/submit")
    public Map<String, Object> submitData(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Data received successfully!");
        response.put("received", request);
        return response;
    }

    @Operation(summary = "Update Item", description = "Updates an item by its ID with new data.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item updated successfully"),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/update/{id}")
    public Map<String, Object> updateItem(@PathVariable String id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Updated successfully for ID: " + id);
        response.put("updated", request);
        return response;
    }

    @Operation(summary = "Delete Item", description = "Deletes an item by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteItem(@PathVariable String id) {
        return Collections.singletonMap("message", "Deleted item with ID: " + id);
    }
}
