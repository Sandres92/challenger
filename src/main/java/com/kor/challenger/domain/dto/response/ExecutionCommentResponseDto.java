package com.kor.challenger.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class ExecutionCommentResponseDto {
    private Long id;
    private String text;
    private Long executionId;
    private LocalDateTime creationDate;
    private UserResponseDto author;
}
