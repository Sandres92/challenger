package com.kor.challenger.service;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.ChallengeContent;
import com.kor.challenger.domain.Execution;
import com.kor.challenger.domain.User;
import com.kor.challenger.domain.dto.requests.EndChallengeRequestDto;
import com.kor.challenger.domain.dto.response.EndChallengeResponseDto;
import com.kor.challenger.domain.dto.response.ChallengeResponseDto;
import com.kor.challenger.repos.ChallengeRepo;
import com.kor.challenger.repos.ExecutionRepo;
import com.kor.challenger.repos.UserRepo;
import com.kor.challenger.security.jwt.JwtUser;
import com.kor.challenger.util.FileWriterUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ChallengeService {
    private final ChallengeRepo challengeRepo;
    private final ExecutionRepo executionRepo;

    private final FileWriterUtility fileWriterUtility;
    private final UserRepo userRepo;

    @Value("${challenger.time-challenge}")
    private short timeChallenge;

    public ChallengeService(ChallengeRepo challengeRepo,
                            ExecutionRepo executionRepo,
                            FileWriterUtility fileWriterUtility,
                            UserRepo userRepo) {
        this.challengeRepo = challengeRepo;
        this.executionRepo = executionRepo;
        this.fileWriterUtility = fileWriterUtility;
        this.userRepo = userRepo;
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepo.findAll();
    }

    public Challenge getChallengeById(Long id) {
        return challengeRepo.findById(id).orElse(null);
    }

    public Challenge createChallenge(Challenge challenge, User user) throws IOException {
        challenge.setCreationDate(LocalDateTime.now());
        //fillMeta(message);
        challenge.setAuthor(user);

        Challenge updateChallenge = challengeRepo.save(challenge);

        //wsSender.accept(EventType.CREATE, updateMessage);

        return updateChallenge;
    }

    public ChallengeResponseDto createChallenge(String text,
                                                MultipartFile file,
                                                JwtUser jwtUser) throws IOException {
        Challenge challenge = new Challenge();
        challenge.setDescription(text);
        challenge.setCreationDate(LocalDateTime.now());
        challenge.setEndChallengeDate(LocalDateTime.now().plusHours(timeChallenge));

        User user = userRepo.findById(jwtUser.getId()).orElse(null);
        challenge.setAuthor(user);
        challenge.setChallengeContents(new ArrayList<>(10));
        challenge.setChallengeComments(new ArrayList<>());

        List<ChallengeContent> challengeContents = challenge.getChallengeContents();
        String resultFilename = fileWriterUtility.saveFile(file);

        ChallengeContent challengeContent = new ChallengeContent();
        challengeContent.setFilename(resultFilename);

        challengeContents.add(0, challengeContent);

        challenge.setChallengeContents(challengeContents);
        Challenge challengeFromDb = challengeRepo.save(challenge);

        return challengeFromDb.toChallengeResponseDto();
    }

    public EndChallengeResponseDto chooseWinners(Challenge challengeFromDb,
                                                 EndChallengeRequestDto endChallengeRequestDto,
                                                 JwtUser jwtUser) {
        Execution executionFirst = executionRepo.getOne(endChallengeRequestDto.getFirst());
        executionFirst.setWinPlace((short) 0);
        executionRepo.save(executionFirst);

        Execution executionSecond = executionRepo.getOne(endChallengeRequestDto.getSecond());
        executionSecond.setWinPlace((short) 1);
        executionRepo.save(executionSecond);

        Execution executionThird = executionRepo.getOne(endChallengeRequestDto.getThird());
        executionThird.setWinPlace((short) 2);
        executionRepo.save(executionThird);

        EndChallengeResponseDto endChallengeResponseDto = new EndChallengeResponseDto(
                challengeFromDb.getId(),
                executionFirst.getId(),
                executionSecond.getId(),
                executionThird.getId());

        return endChallengeResponseDto;
    }
}
