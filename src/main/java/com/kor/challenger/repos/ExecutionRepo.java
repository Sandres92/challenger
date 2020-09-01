package com.kor.challenger.repos;

import com.kor.challenger.domain.Execution;
import com.kor.challenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExecutionRepo extends JpaRepository<Execution, Long> {
    List<Execution> findByAuthor(User user);
}
