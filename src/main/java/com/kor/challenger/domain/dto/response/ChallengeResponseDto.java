package com.kor.challenger.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ChallengeResponseDto {
    private Long id;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime endChallengeDate;
    private UserResponseDto author;
    private List<ChallengeContentResponseDto> challengeContents;
    private List<ChallengeCommentResponseDto> challengeComments;
    private List<ExecutionResponseDto> executions;
}
