package br.com.cabral.basic_api.controller;

import br.com.cabral.basic_api.configuration.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Operações relacionada a autenticação")
public class AuthController {

    @Autowired
    private  final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping()
    @Operation(summary = "autenticar os usuários")
    public ResponseEntity<String> Login(@RequestParam String userName){
        String token = jwtService.generateToken(userName);
        return   ResponseEntity.ok(token);
    }
}
