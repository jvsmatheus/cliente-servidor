package edu.projeto.cliente_servidor.services;

import edu.projeto.cliente_servidor.configs.security.TokenService;
import edu.projeto.cliente_servidor.dto.LoginRequestDTO;
import edu.projeto.cliente_servidor.dto.ResponseDTO;
import edu.projeto.cliente_servidor.models.Usuario;
import edu.projeto.cliente_servidor.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder senhaEncoder;
    private final TokenService tokenService;

    public ResponseEntity login(LoginRequestDTO request) {
        Usuario user = this.repository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("Usuario not found"));
        if(senhaEncoder.matches(request.senha(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
