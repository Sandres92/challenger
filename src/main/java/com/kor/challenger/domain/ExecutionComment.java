package com.kor.challenger.domain;

import com.kor.challenger.domain.dto.response.ExecutionCommentResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(of = "id")
public class ExecutionComment {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "execution_id")
    //@JsonBackReference //УБЕРАЕТ ЦИКЛИЧЕСКИЕ ССЫЛКИ
    private Execution execution;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User author;

    public ExecutionCommentResponseDto toExecutionCommentResponseDto() {
        return new ExecutionCommentResponseDto(this.id, this.text, this.author.toUserResponseDto());
    }
}
