package com.kor.challenger.domain.dto.response;

import com.kor.challenger.domain.Challenge;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ChallengeMiniResponseDto {
    private Long id;
    private String text;
    private LocalDateTime creationDate;
    private LocalDateTime endChallengeDate;
    private UserResponseDto author;
    private ChallengeContentResponseDto challengeContents;

    public ChallengeMiniResponseDto(Challenge challenge) {
        this.id = challenge.getId();
        this.text = challenge.getText();
        this.creationDate = challenge.getCreationDate();
        this.endChallengeDate = challenge.getEndChallengeDate();
        this.author = challenge.getAuthor().toUserResponseDto();
        this.challengeContents = challenge.getChallengeContents().get(0).toChallengeContentResponseDto();
    }
}
