package com.selecao.bandsyncback.webapi.auth;

import com.selecao.bandsyncback.webapi.dto.AuthRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthWebapiService authWebapiService;

    public AuthController(AuthWebapiService authWebapiService) {
        this.authWebapiService = authWebapiService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRegisterDto authRequest) {
        return authWebapiService.login(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRegisterDto authRequest) {
        return authWebapiService.register(authRequest);
    }
}
