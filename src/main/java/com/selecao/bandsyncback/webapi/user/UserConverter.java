package com.selecao.bandsyncback.webapi.user;

import com.selecao.bandsyncback.core.user.User;
import com.selecao.bandsyncback.webapi.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
