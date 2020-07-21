package com.kor.challenger.service;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.ChallengesAndExecutions;
import com.kor.challenger.domain.Execution;
import com.kor.challenger.domain.dto.response.ChallengesAndExecutionsResponseDto;
import com.kor.challenger.repos.ChallengeRepo;
import com.kor.challenger.repos.ExecutionRepo;
import com.kor.challenger.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeAndExecutionService {
    private final ChallengeRepo challengeRepo;
    private final ExecutionRepo executionRepo;
    private final UserRepo userRepo;

    public ChallengeAndExecutionService(ChallengeRepo challengeRepo,
                                        ExecutionRepo executionRepo,
                                        UserRepo userRepo) {
        this.executionRepo = executionRepo;
        this.challengeRepo = challengeRepo;
        this.userRepo = userRepo;
    }

    public ChallengesAndExecutions getChallengesAndExecutions() {
        List<Challenge> challenges = challengeRepo.findAll();
        List<Execution> executions = executionRepo.findAll();

        return new ChallengesAndExecutions(challenges, executions);
    }
}
