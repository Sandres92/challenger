package com.kor.challenger.service;

import com.kor.challenger.domain.*;
import com.kor.challenger.domain.dto.requests.ExecutionEditRequestDto;
import com.kor.challenger.domain.dto.response.ChallengeResponseDto;
import com.kor.challenger.domain.dto.response.ExecutionResponseDto;
import com.kor.challenger.repos.ChallengeRepo;
import com.kor.challenger.repos.ExecutionRepo;
import com.kor.challenger.repos.UserRepo;
import com.kor.challenger.security.jwt.JwtUser;
import com.kor.challenger.util.FileWriterUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExecutionService {
    private final ExecutionRepo executionRepo;
    private final ChallengeRepo challengeRepo;
    private final FileWriterUtility fileWriterUtility;
    private final UserRepo userRepo;

    public ExecutionService(ExecutionRepo executionRepo,
                            ChallengeRepo challengeRepo,
                            FileWriterUtility fileWriterUtility,
                            UserRepo userRepo) {
        this.executionRepo = executionRepo;
        this.challengeRepo = challengeRepo;
        this.fileWriterUtility = fileWriterUtility;
        this.userRepo = userRepo;
    }

    public List<Execution> getAllExecutions() {
        return executionRepo.findAll();
    }

    public Execution getExecutionById(Long id) {
        return executionRepo.findById(id).orElse(null);
    }

    public ExecutionResponseDto createExecution(Challenge challengeFromDb,
                                                String text,
                                                MultipartFile file,
                                                JwtUser jwtUser) throws IOException {
        Execution execution = new Execution();
        execution.setDescription(text);
        execution.setCreationDate(LocalDateTime.now());

        User user = userRepo.findById(jwtUser.getId()).orElse(null);
        execution.setAuthor(user);
        execution.setExecutionContents(new ArrayList<>(10));
        execution.setExecutionComments(new ArrayList<>());
        execution.setTempWinPlace((short) -1);
        execution.setWinPlace((short) -1);

        List<ExecutionContent> executionContents = execution.getExecutionContents();
        String resultFilename = fileWriterUtility.saveFile(file);

        ExecutionContent executionContent = new ExecutionContent();
        executionContent.setFilename(resultFilename);

        executionContents.add(0, executionContent);

        execution.setExecutionContents(executionContents);
        execution.setChallenge(challengeFromDb);
        Execution executionFromDb = executionRepo.save(execution);

        System.out.println(challengeFromDb.getId());

        challengeFromDb.addExecution(executionFromDb);
        Challenge updateChallenge = challengeRepo.save(challengeFromDb);

        return executionFromDb.toExecutionResponseDto();
    }

    public ExecutionResponseDto update(Execution executionFromDb, ExecutionEditRequestDto execution) {
        executionFromDb.setWinPlace(execution.getWinPlace());
        executionFromDb.setTempWinPlace(execution.getTempWinPlace());

        Execution updateExecution = executionRepo.save(executionFromDb);

        return updateExecution.toExecutionResponseDto();
    }
}
