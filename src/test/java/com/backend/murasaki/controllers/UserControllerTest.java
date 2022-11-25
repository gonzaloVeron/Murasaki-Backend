package com.backend.murasaki.controllers;

import com.backend.murasaki.interceptors.JwtInterceptor;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class UserControllerTest {

    @MockBean
    JwtInterceptor interceptor;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() throws Exception {
        when(interceptor.preHandle(any(), any(), any())).thenReturn(true);
    }

    @Test
    void loginTest() throws Exception {
        Object obj = new Object() {
            public String email = "gonveron96@gmail.com";
            public String password = "aaaa";
        };

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        this.mvc.perform(post("/api/v1/user/login").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email").value("gonveron96@gmail.com"))
                .andExpect(jsonPath("$.user.id").value(7))
                .andExpect(jsonPath("$.teacherName", "Gonzalo G. Verón").exists())
                .andExpect(jsonPath("$.token").exists())
        ;
    }

    @Test
    void registerTest() throws Exception {

        Object obj = new Object() {

            public String email = "test@gmail.com";

            public String name = "TestTeacher";

        };

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        this.mvc.perform(post("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.password").doesNotExist())
        ;

    }

    @Test
    void findByTeacherIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/user/getByTeacherId/{teacher_id}", 6))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("gonveron96@gmail.com"));
                //.andExpect(jsonPath("$.name").value("Gonzalo G. Verón"));
    }

    @Test
    void sendRecoveryEmailTest() throws Exception {
    }

//    @Test
//    void changePasswordTest() throws Exception {
//        this.mvc.perform(post("/api/v1/user/jwt/changePass/{password}", "ssss").requestAttr("user_id", "7"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

}