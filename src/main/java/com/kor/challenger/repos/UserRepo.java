package com.kor.challenger.repos;

import com.kor.challenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    //User findById(Long id);

    User findByUsername(String username);

    User findByActivationCode(String code);
}
