package com.kor.challenger.controller;

import com.kor.challenger.domain.Role;
import com.kor.challenger.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RequestMapping("/rest/api")
@RestController
public class LoginController {

    @RequestMapping
    public User getInfo() {
        System.out.println("hello");

        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setActivationCode("test");
        Set<Role> roleSets = new HashSet<>();
        roleSets.add(Role.USER);
        user.setRoles(roleSets);

        return user;//some response MyClass;
    }
}
