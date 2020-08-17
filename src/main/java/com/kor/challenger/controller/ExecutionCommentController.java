package com.kor.challenger.controller;

import com.kor.challenger.domain.Execution;
import com.kor.challenger.domain.dto.response.ExecutionCommentResponseDto;
import com.kor.challenger.security.jwt.JwtUser;
import com.kor.challenger.service.ExecutionCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("executioncomment")
public class ExecutionCommentController {
    @Autowired
    private ExecutionCommentService executionCommentService;

    @PostMapping
    private ExecutionCommentResponseDto createChallengeComment(@RequestParam("id") Execution executionFromDb,
                                                               @RequestParam("text") String text,
                                                               @AuthenticationPrincipal JwtUser jwtUser) {
        return executionCommentService.createExecutionComment(executionFromDb, text, jwtUser);
    }
}
