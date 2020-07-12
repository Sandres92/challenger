package com.kor.challenger.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kor.challenger.domain.dto.response.ChallengeCommentResponseDto;
import com.kor.challenger.domain.dto.response.ChallengeContentResponseDto;
import com.kor.challenger.domain.dto.response.ChallengeResponseDto;
import com.kor.challenger.domain.dto.response.UserResponseDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ElementCollection
    private List<ChallengeContent> challengeContents = new ArrayList<>(10);

    @OneToMany(mappedBy = "challenge", orphanRemoval = true)
    private List<ChallengeComment> challengeComments = new ArrayList<>();

    public ChallengeResponseDto toChallengeResponseDto() {
        ChallengeResponseDto challengeResponseDto = new ChallengeResponseDto();
        challengeResponseDto.setId(this.id);
        challengeResponseDto.setText(this.text);
        challengeResponseDto.setCreationDate(this.creationDate);

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

        return challengeResponseDto;
    }
}
