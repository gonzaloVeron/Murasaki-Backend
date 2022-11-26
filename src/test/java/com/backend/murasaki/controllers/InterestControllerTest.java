package com.backend.murasaki.controllers;

import com.backend.murasaki.interceptors.JwtInterceptor;
import com.backend.murasaki.models.Interest;
import com.backend.murasaki.models.Lesson;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class InterestControllerTest {

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
        this.mvc.perform(get("/api/v1/interest/jwt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(6))
                .andExpect(jsonPath("$[?(@.id == '%s')]", 14).exists())
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Trabajo").exists())
                .andExpect(jsonPath("$[?(@.icon == '%s')]", "pi pi-briefcase").exists());
    }

    @Test
    void getByIdWithExistentIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/interest/jwt/{interest_id}", 11))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 11).exists())
                .andExpect(jsonPath("$.name", "Cultura").exists())
                .andExpect(jsonPath("$.icon", "pi pi-users").exists());
    }

    @Test
    void getByIdWithNonExistentIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/interest/jwt/{interest_id}", 70))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void findTest() throws Exception {
        this.mvc.perform(get("/api/v1/interest/jwt/find/{search_text}", "ul").param("page", "0").param("size", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Cultura").exists())
                .andExpect(jsonPath("$.content[?(@.icon == '%s')]", "pi pi-users").exists());
    }

    @Test
    void findAllTest() throws Exception {
        this.mvc.perform(get("/api/v1/interest/jwt/find").param("page", "0").param("size", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Estudios").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Cultura").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Historia").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Hobby").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Trabajo").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "test").doesNotExist());

    }

    @Test
    void createTest() throws Exception {
        Object obj = new Object() {
            public String name = "test";
            public String icon = "fa-solid fa-bell";
        };
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        MvcResult result = this.mvc.perform(post("/api/v1/interest/jwt").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 1).exists())
                .andExpect(jsonPath("$.name", "test").exists())
                .andExpect(jsonPath("$.icon", "fa-solid fa-bell").exists())
                .andReturn();
    }

    @Test
    void deleteTest() throws Exception {
        this.mvc.perform(delete("/api/v1/interest/jwt/{interest_id}", 23))
                .andDo(print())
                .andExpect(status().isOk());

        this.mvc.perform(get("/api/v1/interest/jwt/{interest_id}", 23))
                .andExpect(status().isNotFound());
    }

}