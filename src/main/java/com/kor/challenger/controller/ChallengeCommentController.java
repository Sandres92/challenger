package com.kor.challenger.controller;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.dto.response.ChallengeCommentResponseDto;
import com.kor.challenger.security.jwt.JwtUser;
import com.kor.challenger.service.ChallengeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("challengecomment")
public class ChallengeCommentController {
    @Autowired
    private ChallengeCommentService challengeCommentService;

    @PostMapping
    private ChallengeCommentResponseDto createChallengeComment(@RequestParam("id") Challenge challengeFromDb,
                                                               @RequestParam("text") String text,
                                                               @AuthenticationPrincipal JwtUser jwtUser) {
        return challengeCommentService.createChallengeComment(challengeFromDb, text, jwtUser);
    }
}
