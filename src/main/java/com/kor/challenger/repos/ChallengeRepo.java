package com.kor.challenger.repos;

import com.kor.challenger.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepo extends JpaRepository<Challenge, Long> {
    //Challenge findById(Long id);
}
