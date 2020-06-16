package com.kor.challenger.controller;

import com.kor.challenger.domain.User;
import com.kor.challenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class RegistrationController {
    @Autowired
    private UserService userService;

    /*@PostMapping("/registration")
    public GenericResponse registerUserAccount(
            @Valid UserDto accountDto, HttpServletRequest request) {
        logger.debug("Registering user account with information: {}", accountDto);
        User registered = createUserAccount(accountDto);
        if (registered == null) {
            throw new UserAlreadyExistException();
        }
        String appUrl = "http://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();

        eventPublisher.publishEvent(
                new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

        return new GenericResponse("success");
    }*/

    @GetMapping("registration")
    public User registerUserAccount() {

        System.out.println("hello");

        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        userService.addUser(user);

        return user;
    }
}
