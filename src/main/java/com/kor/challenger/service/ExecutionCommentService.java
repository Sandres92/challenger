package com.kor.challenger.service;

import com.kor.challenger.domain.*;
import com.kor.challenger.domain.dto.response.ExecutionCommentResponseDto;
import com.kor.challenger.repos.*;
import com.kor.challenger.security.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExecutionCommentService {
    private final ExecutionCommentRepo executionCommentRepo;
    private final ExecutionRepo executionRepo;
    private final UserRepo userRepo;

    @Autowired
    public ExecutionCommentService(ExecutionCommentRepo executionCommentRepo,
                                   ExecutionRepo executionRepo,
                                   UserRepo userRepo) {
        this.executionCommentRepo = executionCommentRepo;
        this.executionRepo = executionRepo;
        this.userRepo = userRepo;
    }

    public ExecutionCommentResponseDto createExecutionComment(Execution executionFromDb, String text, JwtUser jwtUser) {
        ExecutionComment executionComment = new ExecutionComment();
        executionComment.setText(text);
        executionComment.setCreationDate(LocalDateTime.now());

        User user = userRepo.findById(jwtUser.getId()).orElse(null);
        executionComment.setAuthor(user);

        executionComment.setExecution(executionFromDb);

        ExecutionComment executionCommentFromDb = executionCommentRepo.save(executionComment);

        List<ExecutionComment> executionComments = executionFromDb.getExecutionComments();
        executionComments.add(executionCommentFromDb);
        executionFromDb.setExecutionComments(executionComments);
        Execution updateChallenge = executionRepo.save(executionFromDb);

        return executionCommentFromDb.toExecutionCommentResponseDto();
    }
}
