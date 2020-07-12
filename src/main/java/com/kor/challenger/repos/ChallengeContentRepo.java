package com.kor.challenger.repos;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.ChallengeContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeContentRepo extends JpaRepository<Challenge, Long> {

}
