package com.selecao.bandsyncback.core.user;

import com.selecao.bandsyncback.webapi.dto.AuthRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean registerUser(AuthRegisterDto userRegister){
        if (userRepository.findByEmail(userRegister.getEmail()) != null){
            log.warn("User with email {} already exists", userRegister.getEmail());
            return false;
        }
        User user = new User();
        user.setEmail(userRegister.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return true;
    }

}
