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
        Optional<Usuario> user = this.repository.findByEmail(request.email());

        if (!user.isEmpty()) {
            return ResponseEntity.ok(new ErrorResponseDTO("Email já cadastrado"));
        }

        if (user.isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setNome(request.nome());
            newUser.setEmail(request.email());
            newUser.setSenha(passwordEncoder.encode(request.senha()));

            this.repository.save(newUser);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Dados invalidos"));
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

    public ResponseEntity delete(String email) {
        Optional<Usuario> user = this.repository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("Usuário não encontrado"));
        }

        if (user.isPresent()) {
            this.repository.delete(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Dados invalidos"));
    }
}
