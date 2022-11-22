package com.backend.murasaki.controllers;

import com.backend.murasaki.interceptors.JwtInterceptor;
import com.backend.murasaki.models.Interest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

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
class TeacherControllerTest {

    @MockBean
    JwtInterceptor interceptor;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() throws Exception {
        when(interceptor.preHandle(any(), any(), any())).thenReturn(true);
    }

    @Test
    void getAllTest() throws Exception {
        this.mvc.perform(get("/api/v1/teacher"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Julian Borja").exists())
                //.andExpect(jsonPath("$[?(@.name == '%s')]", "Gonzalo G. Verón").exists())
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Test").exists());
    }

    @Test
    void getByExistentIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/teacher/{teacher_id}", 4))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value("Julian Borja"));
    }

    @Test
    void getByInexistentIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/teacher/{teacher_id}", 15))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void findTest() throws Exception {
        this.mvc.perform(get("/api/v1/teacher/find/{search_text}", "or").param("page", "0").param("size", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Julian Borja").exists());
    }

    @Test
    void findAllTest() throws Exception {
        this.mvc.perform(get("/api/v1/teacher/find").param("page", "0").param("size", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Julian Borja").exists())
                //.andExpect(jsonPath("$.content[?(@.name == '%s')]", "Gonzalo G. Verón").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Test").exists());
    }

    @Test
    void createTest() throws Exception {

        Object obj = new Object() {
            public String name = "TestTeacher";
        };

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        MvcResult result = this.mvc.perform(post("/api/v1/teacher").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 1).exists())
                .andExpect(jsonPath("$.name", "TestTeacher").exists())
                .andReturn();
    }

    @Test
    void deleteTest() throws Exception {
        this.mvc.perform(delete("/api/v1/teacher/{teacher_id}", 8))
                .andDo(print())
                .andExpect(status().isOk());

        this.mvc.perform(get("/api/v1/teacher/{teacher_id}", 8))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateTest() throws Exception {

        Object obj = new Object() {
            public String name = "Mariana Borja";
        };

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        MvcResult result = this.mvc.perform(put("/api/v1/teacher/{teacher_id}", 6).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 6).exists())
                .andExpect(jsonPath("$.name", "Mariana Borja").exists())
                .andReturn();
    }

}