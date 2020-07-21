package com.kor.challenger.domain.dto.response;

import com.kor.challenger.domain.Execution;
import com.kor.challenger.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Setter
@Getter
public class ExecutionCommentResponseDto {
    private Long id;
    private String text;
    private UserResponseDto author;
}
