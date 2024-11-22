package edu.projeto.cliente_servidor.controllers;

import edu.projeto.cliente_servidor.dto.usuario.CreateRequestDTO;
import edu.projeto.cliente_servidor.dto.usuario.UpdateRequestDTO;
import edu.projeto.cliente_servidor.services.AuthService;
import edu.projeto.cliente_servidor.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity create(@RequestBody CreateRequestDTO request) {
        return service.create(request);
    }

    @GetMapping
    public ResponseEntity findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PutMapping(value = "/{email}")
    public ResponseEntity update(@PathVariable String email, @RequestBody UpdateRequestDTO request) {
        return service.update(email, request);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity delete(@PathVariable String email) {
        return service.delete(email);
    }
}
