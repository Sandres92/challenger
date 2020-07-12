package com.kor.challenger.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ChallengeResponseDto {
    private Long id;
    private String text;
    private LocalDateTime creationDate;
    private UserResponseDto author;
    private List<ChallengeContentResponseDto> challengeContents;
    private List<ChallengeCommentResponseDto> challengeComments;
}
