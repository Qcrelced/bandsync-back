package com.selecao.bandsyncback.webapi.user;

import com.selecao.bandsyncback.core.user.UserService;
import com.selecao.bandsyncback.webapi.dto.UserDto;
import com.selecao.bandsyncback.webapi.dto.AuthRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserWebapiService userWebapiService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody AuthRegisterDto user){
        boolean success = userService.registerUser(user);
        if (!success) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exist");
        }
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(){
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        UserDto userDto = userWebapiService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

}
