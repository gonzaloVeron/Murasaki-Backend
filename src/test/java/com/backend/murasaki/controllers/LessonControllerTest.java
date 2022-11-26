package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.LessonDTO;
import com.backend.murasaki.dtos.LinkDTO;
import com.backend.murasaki.interceptors.JwtInterceptor;
import com.backend.murasaki.models.Lesson;
import com.backend.murasaki.models.Link;
import com.backend.murasaki.services.JwtService;
import com.backend.murasaki.services.LessonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class LessonControllerTest {

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
        this.mvc.perform(get("/api/v1/lesson/jwt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[?(@.id == '%s')]", 31).exists())
                .andExpect(jsonPath("$[?(@.date == '%s')]", "2022-11-04").exists())
                .andExpect(jsonPath("$[?(@.lessonNumber == '%s')]", 0).exists())
                //.andExpect(jsonPath("$[?(@.content == '%s')]", "Hablamos sobre sus intereses y aspiraciones con respecto al estudio de japonés y empezamos a ver sobre los Kanji.").exists())
                //.andExpect(jsonPath("$[?(@.homework == '%s')]", "Dejé tres Kanji para que traduzca.").exists())
                .andExpect(jsonPath("$[?(@.links)]").isArray());
    }

    @Test
    void getByExistentId() throws Exception {
        this.mvc.perform(get("/api/v1/lesson/jwt/{lesson_id}", 19))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(19))
                .andExpect(jsonPath("$.date").value("2022-11-04"))
                .andExpect(jsonPath("$.lessonNumber").value(0))
                .andExpect(jsonPath("$.content").value("test"))
                .andExpect(jsonPath("$.homework").value("test"))
                .andExpect(jsonPath("$.links").isArray());
    }

    @Test
    void getByInexistentIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/lesson/jwt/{lesson_id}", 66))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateLessonTest() throws Exception {

        LinkDTO ldto1 = new LinkDTO("Youtube","https://www.youtube.com", 18);
        LinkDTO ldto2 = new LinkDTO("PrimeNG","https://www.primefaces.org/primeng/icons");
        Date d = new Date();
        List<LinkDTO> links = new ArrayList<LinkDTO>();
        links.add(ldto1);
        links.add(ldto2);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String stringDate = simpleDateFormat.format(d);

        Object obj = new Object() {
            public Date date = d;
            public int lessonNumber = 2;
            public String content = "aaa";
            public String homework = "ccc";
            public List<LinkDTO> linkDTOS = links;
        };
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        MvcResult result = this.mvc.perform(put("/api/v1/lesson/jwt/{lesson_id}", 17).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 17).exists())
                .andExpect(jsonPath("$.lessonNumber", 2).exists())
                .andExpect(jsonPath("$.content", "aaa").exists())
                .andExpect(jsonPath("$.homework", "ccc").exists())
                .andExpect(jsonPath("$.date", stringDate).exists())
                .andExpect(jsonPath("$.links").isArray())
                .andExpect(jsonPath("$.links.length()").value(2))
                .andReturn();
    }


}