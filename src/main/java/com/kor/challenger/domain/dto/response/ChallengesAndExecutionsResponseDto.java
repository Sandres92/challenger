package com.kor.challenger.domain.dto.response;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.Execution;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ChallengesAndExecutionsResponseDto {
    private List<ChallengeResponseDto> challenges;
    private List<ExecutionResponseDto> executions;

    public ChallengesAndExecutionsResponseDto(List<Challenge> challenges, List<Execution> executions) {
        this.challenges = new ArrayList<>();
        for (Challenge item : challenges) {
            this.challenges.add(item.toChallengeResponseDto());
        }

        this.executions = new ArrayList<>();
        for (Execution item : executions) {
            this.executions.add(item.toExecutionResponseDto());
        }
    }
}
