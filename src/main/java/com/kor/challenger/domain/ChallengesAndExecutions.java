package com.kor.challenger.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class ChallengesAndExecutions {
    private List<Challenge> challenges;
    private List<Execution> executions;
}
