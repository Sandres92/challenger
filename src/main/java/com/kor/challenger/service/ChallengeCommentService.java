package com.kor.challenger.service;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.ChallengeComment;
import com.kor.challenger.domain.User;
import com.kor.challenger.domain.dto.response.ChallengeCommentResponseDto;
import com.kor.challenger.repos.ChallengeCommentRepo;
import com.kor.challenger.repos.ChallengeRepo;
import com.kor.challenger.repos.UserRepo;
import com.kor.challenger.security.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChallengeCommentService {
    private final ChallengeCommentRepo challengeCommentRepo;
    private final ChallengeRepo challengeRepo;
    private final UserRepo userRepo;

    @Autowired
    public ChallengeCommentService(ChallengeCommentRepo challengeCommentRepo,
                                   ChallengeRepo challengeRepo,
                                   UserRepo userRepo) {
        this.challengeCommentRepo = challengeCommentRepo;
        this.challengeRepo = challengeRepo;
        this.userRepo = userRepo;
    }

    public ChallengeCommentResponseDto createChallengeComment(Challenge challengeFromDb, String text, JwtUser jwtUser) {
        ChallengeComment challengeComment = new ChallengeComment();
        challengeComment.setText(text);
        challengeComment.setCreationDate(LocalDateTime.now());

        User user = userRepo.findById(jwtUser.getId()).orElse(null);
        challengeComment.setAuthor(user);

        challengeComment.setChallenge(challengeFromDb);

        ChallengeComment challengeCommentFromDb = challengeCommentRepo.save(challengeComment);

        List<ChallengeComment> challengeComments = challengeFromDb.getChallengeComments();
        challengeComments.add(challengeCommentFromDb);
        challengeFromDb.setChallengeComments(challengeComments);
        Challenge updateChallenge = challengeRepo.save(challengeFromDb);

        return challengeCommentFromDb.toChallengeCommentResponseDto();
    }
}
