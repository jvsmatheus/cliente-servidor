package edu.projeto.cliente_servidor.services;

import edu.projeto.cliente_servidor.configs.security.TokenService;
import edu.projeto.cliente_servidor.dto.ErrorResponseDTO;
import edu.projeto.cliente_servidor.dto.login.LoginRequestDTO;
import edu.projeto.cliente_servidor.dto.usuario.CreateRequestDTO;
import edu.projeto.cliente_servidor.dto.login.LoginResponseDTO;
import edu.projeto.cliente_servidor.models.Usuario;
import edu.projeto.cliente_servidor.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseEntity login(LoginRequestDTO request) {

        Optional<Usuario> user = this.repository.findByEmail(request.email());

        if (user.isPresent()) {
            if(passwordEncoder.matches(request.senha(), user.get().getSenha())) {
                String token = this.tokenService.generateToken(user);
                return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Email e/ou senha invalidos"));
    }


}
