package edu.projeto.cliente_servidor.services;

import edu.projeto.cliente_servidor.configs.security.TokenService;
import edu.projeto.cliente_servidor.dto.ErrorResponseDTO;
import edu.projeto.cliente_servidor.dto.usuario.CreateRequestDTO;
import edu.projeto.cliente_servidor.models.Usuario;
import edu.projeto.cliente_servidor.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Usuário não encontrado"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
