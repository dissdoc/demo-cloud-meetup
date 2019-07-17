package com.example.speecherdemo;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "speecher")
@Component
public class CustomEndpoint {

    @ReadOperation
    public String customPoint() {
        return "Simple Endpoint";
    }
}