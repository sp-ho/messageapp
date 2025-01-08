package com.example.employee1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/_ah/health")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("healthy");
    }
}
