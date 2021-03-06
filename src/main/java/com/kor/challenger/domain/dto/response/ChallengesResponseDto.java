package com.kor.challenger.domain.dto.response;

import com.kor.challenger.domain.Challenge;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ChallengesResponseDto {
    private List<ChallengeResponseDto> challenges;

    public ChallengesResponseDto(List<Challenge> challenges) {
        this.challenges = new ArrayList<>();

        for (Challenge item : challenges) {
            this.challenges.add(item.toChallengeResponseDto());
        }
    }
}
