package com.kor.challenger.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ChallengeCommentResponseDto {
    private Long id;
    private String text;
    private UserResponseDto author;
}
