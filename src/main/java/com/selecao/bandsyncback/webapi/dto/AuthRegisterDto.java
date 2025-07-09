package com.selecao.bandsyncback.webapi.dto;

import lombok.Data;

@Data
public class AuthRegisterDto {

    private String email;
    private String username;
    private String password;

}
