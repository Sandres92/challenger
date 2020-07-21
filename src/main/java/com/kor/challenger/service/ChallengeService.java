package com.kor.challenger.service;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.ChallengeContent;
import com.kor.challenger.domain.User;
import com.kor.challenger.domain.dto.response.ChallengeResponseDto;
import com.kor.challenger.repos.ChallengeRepo;
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
public class ChallengeService {
    private final ChallengeRepo challengeRepo;
    private final FileWriterUtility fileWriterUtility;
    private final UserRepo userRepo;

    public ChallengeService(ChallengeRepo challengeRepo,
                            FileWriterUtility fileWriterUtility,
                            UserRepo userRepo) {
        this.challengeRepo = challengeRepo;
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
        challenge.setText(text);
        challenge.setCreationDate(LocalDateTime.now());
        challenge.setEndChallengeDate(LocalDateTime.now().plusDays(3));

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
}
