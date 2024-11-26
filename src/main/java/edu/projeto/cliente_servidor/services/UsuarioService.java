package edu.projeto.cliente_servidor.services;

import edu.projeto.cliente_servidor.configs.security.TokenService;
import edu.projeto.cliente_servidor.dto.ErrorResponseDTO;
import edu.projeto.cliente_servidor.dto.usuario.CreateRequestDTO;
import edu.projeto.cliente_servidor.dto.usuario.UpdateRequestDTO;
import edu.projeto.cliente_servidor.models.Usuario;
import edu.projeto.cliente_servidor.repositories.UsuarioRepository;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseEntity create(CreateRequestDTO request) {

        // Verificar tamanho do nome
        if (request.nome().length() < 3 || request.nome().length() > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO("Dados inválidos"));
        }

        // Verificar tamanho e formato da senha
        if (request.senha().length() < 3 || request.senha().length() > 6 ||
                !request.senha().matches("\\d+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO("Dados inválidos"));
        }

        // Verificar se o email já está cadastrado
        Optional<Usuario> user = this.repository.findByEmail(request.email());
        if (user.isPresent()) {
            return ResponseEntity.ok(new ErrorResponseDTO("Email já cadastrado"));
        }

        // Criar novo usuário
        Usuario newUser = new Usuario();
        newUser.setNome(request.nome());
        newUser.setEmail(request.email());
        newUser.setSenha(passwordEncoder.encode(request.senha()));
        newUser.setIsAdmin(false);

        this.repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    public ResponseEntity findAll() {
        List<Usuario> users = this.repository.findAll();

       return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public ResponseEntity findByEmail(String email) {
        Optional<Usuario> user = this.repository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("Usuário não encontrado"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    public ResponseEntity update(String email, UpdateRequestDTO request) {

        // Verificar tamanho do nome
        if (request.nome().length() < 3 || request.nome().length() > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO("Dados inválidos"));
        }

        // Verificar tamanho e formato da senha
        if (request.senha().length() < 3 || request.senha().length() > 6 ||
                !request.senha().matches("\\d+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO("Dados inválidos"));
        }

        // Verificar se o email já está cadastrado
        Optional<Usuario> user = this.repository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("Usuário não encontrado"));
        }


        user.get().setNome(request.nome());
        user.get().setSenha(passwordEncoder.encode(request.senha()));

        var _user = this.repository.save(new Usuario(
                user.get().getEmail(),
                request.nome(),
                passwordEncoder.encode(request.senha()),
                false
        ));

        return ResponseEntity.status(HttpStatus.OK).body(_user);
    }

    @Transactional
    public ResponseEntity delete(String email) {
        Optional<Usuario> user = this.repository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("Usuário não encontrado"));
        }

        if (user.isPresent()) {
            this.repository.deleteByEmail(user.get().getEmail());
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Dados invalidos"));
    }
}
