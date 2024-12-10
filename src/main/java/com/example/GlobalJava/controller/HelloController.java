package com.example.GlobalJava.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Prefijo para tus endpoints
public class HelloController {

    @GetMapping("/") // Endpoint para manejar solicitudes GET
    public String hello() {
        return "Hola mundo";
    }

    @PostMapping("/message") // Endpoint para manejar solicitudes POST
    public String receiveMessage(@RequestBody String message) {
        return "Mensaje recibido: " + message;
    }
}