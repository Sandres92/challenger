package com.kor.challenger.controller;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.ChallengesAndExecutions;
import com.kor.challenger.domain.dto.response.ChallengesAndExecutionsResponseDto;
import com.kor.challenger.domain.dto.response.ChallengesResponseDto;
import com.kor.challenger.service.ChallengeAndExecutionService;
import com.kor.challenger.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("challengeandexecution")
public class ChallengeAndExecutionController {
    @Autowired
    ChallengeAndExecutionService challengeAndExecutionService;

    @GetMapping
    public ChallengesAndExecutionsResponseDto getListChallengesAndExecutions() {
        System.out.println("Get all challenges and executions success");

        ChallengesAndExecutions challengesAndExecutions = challengeAndExecutionService.getChallengesAndExecutions();

        return new ChallengesAndExecutionsResponseDto(challengesAndExecutions.getChallenges(), challengesAndExecutions.getExecutions());
    }
}
