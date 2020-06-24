package com.kor.challenger.service;

import com.kor.challenger.domain.Role;
import com.kor.challenger.domain.User;
import com.kor.challenger.repos.UserRepo;
import com.kor.challenger.security.jwt.JwtUser;
import com.kor.challenger.security.jwt.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        log.info("IN <UserService> loadUserByUsername user with name " + jwtUser.getUsername());

        return jwtUser;
    }

    public boolean addUser(User user) {

        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        return true;
    }
}
