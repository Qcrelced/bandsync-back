package com.selecao.bandsyncback.webapi.auth;

import com.selecao.bandsyncback.webapi.dto.AuthConnexionDto;
import com.selecao.bandsyncback.webapi.dto.AuthRegisterDto;
import com.selecao.bandsyncback.webapi.dto.AuthConnexionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthConnexionDto authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRegisterDto authRequest) {
        return authService.register(authRequest);
    }
}
