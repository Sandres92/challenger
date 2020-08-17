package com.kor.challenger.repos;

import com.kor.challenger.domain.ExecutionComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionCommentRepo extends JpaRepository<ExecutionComment, Long> {
}
