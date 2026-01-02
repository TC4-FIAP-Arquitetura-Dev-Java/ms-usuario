package com.br.fiap.entrypoinsts.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class HealthController {

    @GetMapping("/ping")
    public ResponseEntity<Map<String, String>> ping() {
        log.info("HealthController.ping() called");
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        response.put("message", "pong");
        return ResponseEntity.ok(response);
    }
}


