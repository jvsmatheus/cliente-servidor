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
            Aviso aviso = new Aviso();
            aviso.setCategoria(request.categoria());
            aviso.setDescricao(request.descricao());
            this.repository.save(aviso);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Dados inválidos");
        }
    }

    public ResponseEntity<?> read(Integer idCategoria) {
        try {
            var warnings = this.repository.findByCategoriaId(idCategoria);

            return ResponseEntity.status(HttpStatus.OK).body(warnings);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> update(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            Integer id,
            String descricao
    ) {
        System.out.println(authorizationHeader);
        System.out.println(id);
//        System.out.println(nome);
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

            var warning = this.repository.findById(id);

            if(warning.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aviso não encontrada");
            }

//            categoria.get().setNome(nome);
            this.repository.updateDescricaoById(id, descricao);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            System.out.println("Aqui: " + e.getMessage());
            return ResponseEntity.badRequest().body("Dados inválidos");
        }
    }

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

            var warning = this.repository.findById(id);

            if(warning.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aviso não encontrado");
            }

            this.repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Dados inválidos");
        }
    }
}
