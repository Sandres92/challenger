package com.kor.challenger.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.kor.challenger.domain.dto.response.ChallengeCommentResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(of = "id")
public class ChallengeComment {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    //@JsonBackReference //УБЕРАЕТ ЦИКЛИЧЕСКИЕ ССЫЛКИ
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User author;

    public ChallengeCommentResponseDto toChallengeCommentResponseDto() {
        return new ChallengeCommentResponseDto(this.id, this.text, this.author.toUserResponseDto());
    }
}
