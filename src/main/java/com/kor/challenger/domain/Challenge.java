package com.kor.challenger.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kor.challenger.domain.dto.response.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "challenge")
@Data
@Setter
@Getter
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endChallengeDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ElementCollection
    private List<ChallengeContent> challengeContents = new ArrayList<>(10);

    @OneToMany(mappedBy = "challenge", orphanRemoval = true)
    private List<ChallengeComment> challengeComments = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", orphanRemoval = true)
    private List<Execution> executions = new ArrayList<>();

    /*@OneToMany(mappedBy = "challenge", orphanRemoval = true)
    private List<Execution> executions1Place = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", orphanRemoval = true)
    private List<Execution> executions2Place = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", orphanRemoval = true)
    private List<Execution> executions3Place = new ArrayList<>();*/

    public ChallengeResponseDto toChallengeResponseDto() {
        ChallengeResponseDto challengeResponseDto = new ChallengeResponseDto();
        challengeResponseDto.setId(this.id);
        challengeResponseDto.setDescription(this.description);

        challengeResponseDto.setCreationDate(this.creationDate);
        challengeResponseDto.setEndChallengeDate(this.endChallengeDate);
        challengeResponseDto.setNowServerTime(LocalDateTime.now());

        UserResponseDto userResponseDto = author.toUserResponseDto();
        challengeResponseDto.setAuthor(userResponseDto);

        List<ChallengeContentResponseDto> contentDtos = new ArrayList<>();
        for (ChallengeContent item : challengeContents) {
            contentDtos.add(item.toChallengeContentResponseDto());
        }
        challengeResponseDto.setChallengeContents(contentDtos);

        List<ChallengeCommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (ChallengeComment item : challengeComments) {
            commentResponseDtos.add(item.toChallengeCommentResponseDto());
        }
        challengeResponseDto.setChallengeComments(commentResponseDtos);

        List<ExecutionResponseDto> executionResponseDtos = new ArrayList<>();
        for (Execution item : executions) {
            executionResponseDtos.add(item.toExecutionResponseDto());
        }
        challengeResponseDto.setExecutions(executionResponseDtos);

        return challengeResponseDto;
    }

    public void addExecution(Execution execution) {
        executions.add(execution);
    }
}
