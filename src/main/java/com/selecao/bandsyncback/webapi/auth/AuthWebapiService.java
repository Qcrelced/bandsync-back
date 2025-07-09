package com.selecao.bandsyncback.webapi.auth;


import com.selecao.bandsyncback.core.security.JwtService;
import com.selecao.bandsyncback.core.user.User;
import com.selecao.bandsyncback.core.user.UserRepository;
import com.selecao.bandsyncback.webapi.dto.AuthConnexionDto;
import com.selecao.bandsyncback.webapi.dto.AuthRegisterDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthWebapiService {

    private static final Logger log = LoggerFactory.getLogger(AuthWebapiService.class);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthWebapiService(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public ResponseEntity<?> login(AuthConnexionDto authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(), authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            log.warn("Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(Collections.singletonMap("token", jwt));
    }

    public ResponseEntity<?> register(AuthRegisterDto authRequest) {
        if (userRepository.findByEmail(authRequest.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        }

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole("USER");

        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(Collections.singletonMap("token", jwt));
    }
}
