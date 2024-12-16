package edu.projeto.cliente_servidor.controllers;

import edu.projeto.cliente_servidor.dto.categoria.CreateRequestDTO;
import edu.projeto.cliente_servidor.dto.categoria.UpdateRequestDTO;
import edu.projeto.cliente_servidor.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateRequestDTO request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return this.service.create(token, request.nome());
    }

    @GetMapping
    public ResponseEntity<?> read() {
        return this.service.read();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateRequestDTO request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return this.service.update(token, id, request.nome());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return this.service.delete(token, id);
    }
}
