package com.kor.challenger.controller;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.dto.response.ChallengeResponseDto;
import com.kor.challenger.domain.dto.response.ChallengesResponseDto;
import com.kor.challenger.security.jwt.JwtUser;
import com.kor.challenger.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("challenge")
public class ChallengeController {
    @Autowired
    ChallengeService challengeService;

    @GetMapping
    public ChallengesResponseDto getListChallenges() {
        System.out.println("Get all success");

        List<Challenge> challenges = challengeService.getAllChallenges();
        return new ChallengesResponseDto(challenges);
    }

    @GetMapping("{id}")
    public ChallengeResponseDto getOneChallenge(@PathVariable("id") Long id) {
        Challenge challenge = challengeService.getChallengeById(id);

        if (challenge == null) {
            return null;
        }
        return challenge.toChallengeResponseDto();
    }

    @PostMapping
    public ChallengeResponseDto createChallenge(@RequestParam("text") String text,
                                                @RequestParam("file") MultipartFile file,
                                                @AuthenticationPrincipal JwtUser jwtUser) throws IOException {
        return challengeService.createChallenge(text, file, jwtUser);
    }
}
