package com.kor.challenger.controller;

import com.kor.challenger.domain.User;
import com.kor.challenger.domain.dto.response.ProfileResponseDto;
import com.kor.challenger.domain.dto.response.UserResponseDto;
import com.kor.challenger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("profile")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("{id}")
    public ProfileResponseDto get(@PathVariable("id") User user) {
        return userService.getProfile(user);
    }

    @PostMapping("{id}")
    public UserResponseDto update(@PathVariable("id") User userFromDb,
                                  @RequestParam("text") String text,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        return userService.update(userFromDb, text, file);
    }
}
