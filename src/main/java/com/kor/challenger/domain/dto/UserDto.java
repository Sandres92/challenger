package com.kor.challenger.domain.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UserDto {

    Long id;

    //@NotBlank(message = "username can't be empty")
    private String username;
    //@NotBlank(message = "password can't be empty")
    private String password;
}
