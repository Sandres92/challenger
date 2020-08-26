package com.kor.challenger.controller;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.Execution;
import com.kor.challenger.domain.dto.requests.ExecutionEditRequestDto;
import com.kor.challenger.domain.dto.response.ChallengesResponseDto;
import com.kor.challenger.domain.dto.response.ExecutionResponseDto;
import com.kor.challenger.domain.dto.response.ExecutionsResponseDto;
import com.kor.challenger.security.jwt.JwtUser;
import com.kor.challenger.service.ExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("execution")
@Slf4j
public class ExecutionController {
    @Autowired
    ExecutionService executionService;

    @GetMapping
    public ExecutionsResponseDto getListExecutions() {
        System.out.println("Get all executions success");

        List<Execution> executions = executionService.getAllExecutions();
        return new ExecutionsResponseDto(executions);
    }

    @GetMapping("{id}")
    public ExecutionResponseDto getOneExecution(@PathVariable("id") Long id) {
        Execution execution = executionService.getExecutionById(id);

        if (execution == null) {
            return null;
        }

        return execution.toExecutionResponseDto();
    }

    @PostMapping
    public ExecutionResponseDto createExecution(@RequestParam("id") Challenge challengeFromDb,
                                                @RequestParam("text") String text,
                                                @RequestParam("file") MultipartFile file,
                                                @AuthenticationPrincipal JwtUser jwtUser) throws IOException {
        return executionService.createExecution(challengeFromDb, text, file, jwtUser);
    }

    @PutMapping("{id}")
    public ExecutionResponseDto update(@PathVariable("id") Execution executionFromDb, @RequestBody ExecutionEditRequestDto execution) throws IOException {
        return executionService.update(executionFromDb, execution);
    }
}
