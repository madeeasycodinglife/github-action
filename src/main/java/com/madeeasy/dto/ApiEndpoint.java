package com.madeeasy.dto;


public class ApiEndpoint {
    private String method;
    private String url;
    private String description;

    public ApiEndpoint(String method, String url, String description) {
        this.method = method;
        this.url = url;
        this.description = description;
    }

    // Getters and setters (or use Lombok to reduce boilerplate)
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

