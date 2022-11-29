package com.backend.murasaki.services;

import com.backend.murasaki.dtos.*;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.exceptions.UnauthorizedException;
import com.backend.murasaki.models.Role;
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

    @Autowired
    private RoleService roleService;

    @Transactional
    public User register(UserRegisterDTO dto){
        TeacherDTO teacherDTO = new TeacherDTO(dto.getName());
        Teacher teacherFound = this.teacherService.save(teacherDTO);
        String randomPass = this.generateSecureRandomPassword();
        Role teacherRole = this.roleService.getRole("teacher");
        User user = this.userRepository.save(new User(dto.getEmail(), this.hashPassword(randomPass), teacherFound, teacherRole, true));
        this.sendMailService.sendMail(
          "gonveron96@gmail.com", dto.getEmail(), "Murasaki", "Su password es: " + randomPass
        );
        return user;
    }

    @Transactional(readOnly = true)
    public User findById(int user_id){
        return this.userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("The user requested was now found"));
    }

    @Transactional(readOnly = true)
    public UserLoggedDTO authenticate(UserCredentialsDTO dto){
        User userFound = this.userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new UnauthorizedException("Credenciales inválidas"));
        if(!this.matchPassword(dto.getPassword(), userFound.getPassword())){
            throw new UnauthorizedException("Credenciales inválidas");
        }
        return new UserLoggedDTO(userFound, userFound.getTeacher().getName(), this.jwtService.create(userFound.getId(), userFound.getRole().getName()));
    }

    @Transactional(readOnly = true)
    public UserDTO findByTeacherId(int id){
        User user = this.userRepository.findByTeacher_id(id).orElseThrow(() -> new NotFoundException("The requested user was not found."));
        return new UserDTO(user.getEmail(), user.getTeacher().getName());
    }

    @Transactional(readOnly = true)
    public void sendRecoveryEmail(String email){
        User userFound = this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("The requested user was not found."));
        String token = this.jwtService.create(userFound.getId(), userFound.getRole().getName());
        this.sendMailService.sendMail(
                "gonveron96@gmail.com",
                email,
                "Murasaki - Recuperar contraseña",
                "Por favor, entre en el siguiente enlace para cambiar su contraseña " + "http://localhost:4200/auth/changePassword/" + token
        );
    }

    @Transactional
    public void changeUserPassword(int id, String password){
        User userFound = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("The requested user was not found."));
        userFound.setPassword(this.hashPassword(password));
        userFound.setFirstTime(false);
        this.userRepository.save(userFound);
    }

    @Transactional(readOnly = true)
    public boolean needChangePass(int user_id){
        User userFound = this.userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("The requested user was not found."));
        return userFound.isFirstTime();
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
