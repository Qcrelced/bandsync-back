package com.selecao.bandsyncback.webapi.user;


import com.selecao.bandsyncback.core.user.User;
import com.selecao.bandsyncback.core.user.UserRepository;
import com.selecao.bandsyncback.webapi.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserWebapiService {

    private final UserRepository userRepository;
    private final UserConverter userConvertor;

    public UserWebapiService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConvertor = userConverter;
    }

    public UserDto getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userConvertor::userToDto).orElse(null);
    }
}
