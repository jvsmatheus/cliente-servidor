package edu.projeto.cliente_servidor.controllers;

import edu.projeto.cliente_servidor.dto.aviso.CreateRequestDTO;
import edu.projeto.cliente_servidor.dto.aviso.UpdateRequestDTO;
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

    @GetMapping("{idCategoria}")
    public ResponseEntity<?> read(@PathVariable Integer idCategoria) {
        return this.service.read(idCategoria);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateRequestDTO request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        System.out.println(id);
        System.out.println(request);
        return this.service.update(token, id, request.descricao());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return this.service.delete(token, id);
    }
}
