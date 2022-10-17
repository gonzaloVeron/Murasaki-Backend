package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.UserCredentialsDTO;
import com.backend.murasaki.dtos.UserLoggedDTO;
import com.backend.murasaki.dtos.UserRegisterDTO;
import com.backend.murasaki.models.User;
import com.backend.murasaki.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    @ResponseBody
    public UserLoggedDTO login(@RequestBody UserCredentialsDTO dto){
        return this.userService.authenticate(dto);
    }

    @PostMapping(path = "/register")
    @ResponseBody
    public User register(@RequestBody UserRegisterDTO dto){
        return this.userService.register(dto);
    }

}
