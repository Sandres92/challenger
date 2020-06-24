package com.kor.challenger.controller;

import com.kor.challenger.domain.User;
import com.kor.challenger.domain.dto.AuthenticationRequestDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        String p = bCryptPasswordEncoder.encode(requestDto.getPassword());

        System.out.println(p);

        try {
            String username = requestDto.getUsername();
            String username_t = username.trim();
            User user = userRepo.findByUsername(username);

            UsernamePasswordAuthenticationToken mm = new UsernamePasswordAuthenticationToken(username_t, requestDto.getUsername());

            System.out.println( "aa " + mm.getCredentials());
            System.out.println( "aa " + mm.getPrincipal());
            System.out.println( "aa " + mm.getDetails());

            authenticationManager.authenticate(mm);

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
}
