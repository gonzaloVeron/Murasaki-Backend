package com.backend.murasaki.services;

import com.backend.murasaki.dtos.UserCredentialsDTO;
import com.backend.murasaki.dtos.UserLoggedDTO;
import com.backend.murasaki.dtos.UserRegisterDTO;
import com.backend.murasaki.exceptions.UnauthorizedException;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.models.User;
import com.backend.murasaki.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private Environment env;

    public User register(UserRegisterDTO dto){
        Teacher teacherFound = this.teacherService.findById(dto.getTeacher_id());
        User user = new User(dto.getEmail(), this.hashPassword(dto.getPassword()), teacherFound);
        return this.userRepository.save(user);
    }

    public UserLoggedDTO authenticate(UserCredentialsDTO dto){
        User userFound = this.userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new UnauthorizedException("Credenciales inválidas"));
        if(!this.matchPassword(dto.getPassword(), userFound.getPassword())){
            throw new UnauthorizedException("Credenciales inválidas");
        }
        return new UserLoggedDTO(userFound, userFound.getTeacher().getName(), this.jwtService.create(userFound.getId()));
    }

    public String hashPassword(String password){
        int strength = Integer.parseInt(env.getProperty("passHashSecret"));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        //String encodedPassword = bCryptPasswordEncoder.encode(password);
        return  bCryptPasswordEncoder.encode(password);
    }

    private boolean matchPassword(String rawPassword, String encodedPassword){
        int strength = Integer.parseInt(env.getProperty("passHashSecret"));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

}
