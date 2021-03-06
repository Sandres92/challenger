package com.kor.challenger.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ExecutionResponseDto {
    private Long id;
    private String description;
    private LocalDateTime creationDate;
    private Short tempWinPlace;
    private Short winPlace;
    private UserResponseDto author;
    private ChallengeMiniResponseDto challengeMini;
    private List<ExecutionContentResponseDto> executionContents;
    private List<ExecutionCommentResponseDto> executionComments;
}
