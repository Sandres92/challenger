package com.kor.challenger.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileResponseDto {
    private UserResponseDto userResponseDto;
    private ChallengesAndExecutionsResponseDto challengesAndExecutionsResponseDto;
}
