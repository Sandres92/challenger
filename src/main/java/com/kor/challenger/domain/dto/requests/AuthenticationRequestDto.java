package com.kor.challenger.domain.dto.requests;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
