package edu.projeto.cliente_servidor.services;

import edu.projeto.cliente_servidor.configs.security.TokenService;
import edu.projeto.cliente_servidor.dto.aviso.CreateRequestDTO;
import edu.projeto.cliente_servidor.models.Aviso;
import edu.projeto.cliente_servidor.models.Categoria;
import edu.projeto.cliente_servidor.repositories.AvisoRepository;
import edu.projeto.cliente_servidor.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository repository;
    @Autowired
    private TokenService tokenService;

    public ResponseEntity<?> create(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            CreateRequestDTO request
    ) {
        try {
            // Verifica se o cabeçalho Authorization está presente
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Token ausente ou inválido");
            }

            String token = authorizationHeader.substring(7); // Remove o prefixo "Bearer "

            // Usa o serviço para validar se o usuário é admin
            if (!tokenService.isAdmin(token)) {
                return ResponseEntity.status(403).body("Você não tem permissão suficiente para performar esta ação");
            }

            // Criação da categoria
            Aviso aviso = new Aviso(request.descricao(), request.idCategoria());
            this.repository.save(aviso);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Dados inválidos");
        }
    }

    public ResponseEntity<?> read() {
        try {
            // Criação da categoria

            var categories = this.repository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(categories);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional
    public ResponseEntity<?> update(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            Integer id,
            String nome
    ) {
        System.out.println(authorizationHeader);
        System.out.println(id);
        System.out.println(nome);
        try {
            // Verifica se o cabeçalho Authorization está presente
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Token ausente ou inválido");
            }

            String token = authorizationHeader.substring(7); // Remove o prefixo "Bearer "

            // Usa o serviço para validar se o usuário é admin
            if (!tokenService.isAdmin(token)) {
                return ResponseEntity.status(403).body("Você não tem permissão suficiente para performar esta ação");
            }

            var categoria = this.repository.findById(id);

            if(categoria.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
            }

//            categoria.get().setNome(nome);
            this.repository.updateNomeById(id, nome);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            System.out.println("Aqui: " + e.getMessage());
            return ResponseEntity.badRequest().body("Dados inválidos");
        }
    }

    @Transactional
    public ResponseEntity<?> delete(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            Integer id
    ) {
        try {
            // Verifica se o cabeçalho Authorization está presente
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Token ausente ou inválido");
            }

            String token = authorizationHeader.substring(7); // Remove o prefixo "Bearer "

            // Usa o serviço para validar se o usuário é admin
            if (!tokenService.isAdmin(token)) {
                return ResponseEntity.status(403).body("Você não tem permissão suficiente para performar esta ação");
            }

            var categoria = this.repository.findById(id);

            if(categoria.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
            }

            this.repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Dados inválidos");
        }
    }
}
