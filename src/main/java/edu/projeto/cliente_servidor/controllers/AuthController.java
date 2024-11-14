package edu.projeto.cliente_servidor.controllers;

import edu.projeto.cliente_servidor.dto.LoginRequestDTO;
import edu.projeto.cliente_servidor.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO request) {
        var response = service.login(request);
        return response;
    }
}
