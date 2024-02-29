package com.api.mock.component.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MockApiController {
    
    @PostMapping("/")
    public ResponseEntity<String> mockImageClassifier(@RequestBody String entity) {
        String json = "{\"success\": true, \"message\": \"success\", \"estimated_data\": {\"class\": 3, \"confidence\": 0.8683}}";
        // String json = "{\"success\": false, \"message\": \"Error:E50012\", \"estimated_data\": {}}";
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    
}
