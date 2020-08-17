package com.kor.challenger.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class ChallengeCommentResponseDto {
    private Long id;
    private String text;
    private Long challengeId;
    private LocalDateTime creationDate;
    private UserResponseDto author;
}
