package com.kor.challenger.service;

import com.kor.challenger.domain.Challenge;
import com.kor.challenger.domain.Execution;
import com.kor.challenger.domain.Role;
import com.kor.challenger.domain.User;
import com.kor.challenger.domain.dto.requests.UserEditRequestDto;
import com.kor.challenger.domain.dto.response.ChallengesAndExecutionsResponseDto;
import com.kor.challenger.domain.dto.response.ProfileResponseDto;
import com.kor.challenger.domain.dto.response.UserResponseDto;
import com.kor.challenger.repos.ChallengeRepo;
import com.kor.challenger.repos.ExecutionRepo;
import com.kor.challenger.repos.UserRepo;
import com.kor.challenger.security.jwt.JwtUser;
import com.kor.challenger.security.jwt.JwtUserFactory;
import com.kor.challenger.util.FileWriterUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ChallengeRepo challengeRepo;
    @Autowired
    private ExecutionRepo executionRepo;
    @Autowired
    private FileWriterUtility fileWriterUtility;

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

    public ProfileResponseDto getProfile(User userFromDb) {
        ProfileResponseDto profileResponseDto = new ProfileResponseDto();
        profileResponseDto.setUserResponseDto(userFromDb.toUserResponseDto());

        List<Challenge> challenges = challengeRepo.findByAuthor(userFromDb);
        List<Execution> executions = executionRepo.findByAuthor(userFromDb);

        ChallengesAndExecutionsResponseDto challengesAndExecutionsResponseDto =
                new ChallengesAndExecutionsResponseDto(challenges, executions);
        profileResponseDto.setChallengesAndExecutionsResponseDto(challengesAndExecutionsResponseDto);

        return profileResponseDto;
    }

    public UserResponseDto update(User userFromDb, UserEditRequestDto user) {
        userFromDb.setUsername(user.getUsername());
        userFromDb.setFilename(user.getFilename());

        User updateUser = userRepo.save(userFromDb);

        return updateUser.toUserResponseDto();
    }

    public UserResponseDto update(User userFromDb, String text, MultipartFile file) throws IOException {
        String resultFilename = fileWriterUtility.saveFile(file);
        userFromDb.setFilename(resultFilename);

        User updateUser = userRepo.save(userFromDb);

        return updateUser.toUserResponseDto();
    }
}
