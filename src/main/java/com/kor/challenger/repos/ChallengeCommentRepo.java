package com.kor.challenger.repos;

import com.kor.challenger.domain.ChallengeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeCommentRepo extends JpaRepository<ChallengeComment, Long> {
}
