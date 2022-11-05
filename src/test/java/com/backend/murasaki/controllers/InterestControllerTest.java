package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.InterestDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Interest;
import com.backend.murasaki.services.InterestService;
import com.backend.murasaki.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(JwtService.class)
@WebMvcTest(InterestController.class)
class InterestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InterestService interestService;

    private List<Interest> testInterests;

    private Interest testInterest;

    private InterestDTO testInterestDTO;

    @BeforeEach
    void setUp() {
        testInterest = new Interest(35, "Cultura", "fa-solid fa-bell");
        testInterests = new ArrayList<>();
        testInterests.add(testInterest);
        testInterestDTO = new InterestDTO("Cultura", "fa-solid fa-bell");

        when(interestService.findAll()).thenReturn(testInterests);
        when(interestService.findById(35)).thenReturn(testInterest);
        when(interestService.findById(70)).thenThrow(NotFoundException.class);
        when(interestService.save(ArgumentMatchers.any(InterestDTO.class))).thenReturn(testInterest);
    }

    @Test
    void getAllTest() throws Exception {
        this.mvc.perform(get("/api/v1/interest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[?(@.id == '%s')]", 35).exists())
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Cultura").exists())
                .andExpect(jsonPath("$[?(@.icon == '%s')]", "fa-solid fa-bell").exists());
    }

    @Test
    void getByIdWithExistentIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/interest/{interest_id}", 35))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 35).exists())
                .andExpect(jsonPath("$.name", "Cultura").exists())
                .andExpect(jsonPath("$.icon", "fa-solid fa-bell").exists());
    }

    @Test
    void getByIdWithNonExistentIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/interest/{interest_id}", 70))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void createTest() throws Exception {
        Object obj = new Object() {
            public String name = "Cultura";
            public String icon = "fa-solid fa-bell";
        };
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        this.mvc.perform(post("/api/v1/interest").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 35).exists())
                .andExpect(jsonPath("$.name", "Cultura").exists())
                .andExpect(jsonPath("$.icon", "fa-solid fa-bell").exists());
    }

}