package com.kor.challenger.domain.dto;

import com.kor.challenger.domain.RegistrationResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegistrationResponseDto {
    private String username;
    private String password;

    private RegistrationResponseStatus status;
}
