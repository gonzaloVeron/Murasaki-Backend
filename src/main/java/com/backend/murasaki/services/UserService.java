package com.backend.murasaki.services;

import com.backend.murasaki.dtos.*;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.exceptions.UnauthorizedException;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.models.User;
import com.backend.murasaki.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private Environment env;

    @Transactional
    public User register(UserRegisterDTO dto){
        TeacherDTO teacherDTO = new TeacherDTO(dto.getName());
        Teacher teacherFound = this.teacherService.save(teacherDTO);
        String randomPass = this.generateSecureRandomPassword();
        User user = this.userRepository.save(new User(dto.getEmail(), this.hashPassword(randomPass), teacherFound));
        this.sendMailService.sendMail(
          "gonveron96@gmail.com", dto.getEmail(), "Murasaki", "Su password es: " + randomPass
        );
        return user;
    }

    @Transactional
    public UserLoggedDTO authenticate(UserCredentialsDTO dto){
        User userFound = this.userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new UnauthorizedException("Credenciales inválidas"));
        if(!this.matchPassword(dto.getPassword(), userFound.getPassword())){
            throw new UnauthorizedException("Credenciales inválidas");
        }
        return new UserLoggedDTO(userFound, userFound.getTeacher().getName(), this.jwtService.create(userFound.getId()));
    }

    @Transactional(readOnly = true)
    public UserDTO findByTeacherId(int id){
        User user = this.userRepository.findByTeacher_id(id).orElseThrow(() -> new NotFoundException("The requested teacher was not found."));
        return new UserDTO(user.getEmail(), user.getTeacher().getName());
    }

    private String hashPassword(String password){
        int strength = Integer.parseInt(env.getProperty("passHashSecret"));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return  bCryptPasswordEncoder.encode(password);
    }

    private boolean matchPassword(String rawPassword, String encodedPassword){
        int strength = Integer.parseInt(env.getProperty("passHashSecret"));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

    /* ----------- */

    private Stream<Character> getRandomSpecialChars(int count) {
        Random random = new SecureRandom();
        IntStream specialChars = random.ints(count, 33, 45);
        return specialChars.mapToObj(data -> (char) data);
    }

    private String generateSecureRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(getRandomSpecialChars(2),
                Stream.concat(getRandomSpecialChars(2),
                        Stream.concat(getRandomSpecialChars(2), getRandomSpecialChars(4))));
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);
        String password = charList.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }

}
