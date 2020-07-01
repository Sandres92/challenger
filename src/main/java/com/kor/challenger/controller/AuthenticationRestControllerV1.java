package com.kor.challenger.controller;

import com.kor.challenger.domain.RegistrationResponseStatus;
import com.kor.challenger.domain.User;
import com.kor.challenger.domain.dto.AuthenticationRequestDto;
import com.kor.challenger.domain.dto.LoginRequestDto;
import com.kor.challenger.domain.dto.RegistrationRequestDto;
import com.kor.challenger.domain.dto.RegistrationResponseDto;
import com.kor.challenger.repos.UserRepo;
import com.kor.challenger.security.jwt.JwtTokenProvider;
import com.kor.challenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthenticationRestControllerV1 {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("login")
    private ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            String username = loginRequestDto.getUsername();
            UsernamePasswordAuthenticationToken mm = new UsernamePasswordAuthenticationToken(username, loginRequestDto.getPassword());
            authenticationManager.authenticate(mm);

            User user = userRepo.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("user with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<String, String> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("invalid username or password");
        }
    }

    @PostMapping("registration")
    public RegistrationResponseDto registerUserAccount(@Valid User user) {
    //public RegistrationResponseDto registerUserAccount(@Valid User user, @RequestParam("file") MultipartFile file) {

        System.out.println("1111 22222 333333");
        String oldPassword = user.getPassword();
        String passwordEncoder = "{bcrypt}"+bCryptPasswordEncoder.encode(oldPassword);
        user.setPassword(passwordEncoder);

        if (!userService.addUser(user)) {
            return new RegistrationResponseDto(user.getUsername(), oldPassword, RegistrationResponseStatus.NO_CREATED_USER_ALREADY_EXIST);
        }

        return new RegistrationResponseDto(user.getUsername(), oldPassword, RegistrationResponseStatus.NEW_USER_CREATED);
    }
}
