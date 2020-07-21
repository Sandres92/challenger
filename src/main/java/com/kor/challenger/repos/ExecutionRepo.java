package com.kor.challenger.repos;

import com.kor.challenger.domain.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepo extends JpaRepository<Execution, Long> {
}
