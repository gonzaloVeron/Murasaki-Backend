package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.UserCredentialsDTO;
import com.backend.murasaki.dtos.UserDTO;
import com.backend.murasaki.dtos.UserLoggedDTO;
import com.backend.murasaki.dtos.UserRegisterDTO;
import com.backend.murasaki.models.User;
import com.backend.murasaki.services.SendMailService;
import com.backend.murasaki.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping(path = "/getByTeacherId/{teacher_id}")
    @ResponseBody
    public UserDTO findByTeacherId(@PathVariable int teacher_id){
        return this.userService.findByTeacherId(teacher_id);
    }

    @PostMapping(path = "/recovery/{email}")
    public void sendRecoveryEmail(@PathVariable String email){
        this.userService.sendRecoveryEmail(email);
    }

    @PostMapping(path = "/jwt/changePass/{password}")
    public void changePassword(HttpServletRequest request, @PathVariable String password){
        int user_id = (int)request.getAttribute("user_id");
        this.userService.changeUserPassword(user_id, password);
    }

    @GetMapping(path = "/jwt/needChangePass")
    @ResponseBody
    public boolean needChangePassword(HttpServletRequest request){
        int user_id = (int)request.getAttribute("user_id");
        return this.userService.needChangePass(user_id);
    }

}
