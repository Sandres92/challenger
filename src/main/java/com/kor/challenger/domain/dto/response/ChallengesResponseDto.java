package com.kor.challenger.domain.dto.response;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@AllArgsConstructor
@Setter
@Getter
public class ChallengesResponseDto {
    private List<ChallengeResponseDto> challenges;

    /*public ChallengesResponseDto(List<ChallengeResponseDto> challenges) {
        this.challenges = new ArrayList<>();

        for (ChallengeResponseDto item : challenges) {
            this.challenges.add(item);
        }
    }*/

    public ChallengesResponseDto(List<Challenge> challenges) {
        this.challenges = new ArrayList<>();

        for (Challenge item : challenges) {
            this.challenges.add(item.toChallengeResponseDto());
        }
    }
}
