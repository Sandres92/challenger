package com.kor.challenger.repos;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepo extends JpaRepository<Challenge, Long> {
    //Challenge findById(Long id);

    List<Challenge> findByAuthor(User user);
}
