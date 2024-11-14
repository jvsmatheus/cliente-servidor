package edu.projeto.cliente_servidor;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class teste {
    public static void main(String[] args) {
        String plainPassword = "Teste123";

        // Geração de um salt e encriptação da senha
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        // Exibição da senha encriptada
        System.out.println("Senha original: " + plainPassword);
        System.out.println("Senha encriptada (hash): " + hashedPassword);

        // Verificação de senha (exemplo)
        boolean isMatch = BCrypt.checkpw(plainPassword, hashedPassword);
        System.out.println("A senha é válida: " + isMatch);
    }
}
