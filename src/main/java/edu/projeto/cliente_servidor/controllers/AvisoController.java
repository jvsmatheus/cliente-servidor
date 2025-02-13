package edu.projeto.cliente_servidor.controllers;

import edu.projeto.cliente_servidor.dto.aviso.CreateRequestDTO;
import edu.projeto.cliente_servidor.dto.categoria.UpdateRequestDTO;
import edu.projeto.cliente_servidor.services.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avisos")
public class AvisoController {

    @Autowired
    private AvisoService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateRequestDTO request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return this.service.create(token, request);
    }

//    @GetMapping
//    public ResponseEntity<?> read() {
//        return this.service.read();
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateRequestDTO request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
//        return this.service.update(token, id, request.nome());
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<?> delete(@PathVariable Integer id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
//        return this.service.delete(token, id);
//    }
}
