package com.kor.challenger.controller;

import com.kor.challenger.domain.User;
import com.kor.challenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

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

    @PostMapping("registration")
    public User registerUserAccount(@Valid User user, @RequestParam("file") MultipartFile file){

        System.out.println(file.getOriginalFilename());

        userService.addUser(user);

        return user;
    }

}
