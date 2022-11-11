package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.LinkDTO;
import com.backend.murasaki.interceptors.JwtInterceptor;
import com.backend.murasaki.models.Interest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class StudentControllerTest {

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
        this.mvc.perform(get("/api/v1/student/jwt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(11))
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Nahuel Fazio").exists())
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Victor Seoane").exists())
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Angeles Barroso").exists())
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Guillermo Gaspar").exists())
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Valeria Olmos").exists())
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Emilio Sanmartin").exists())
                .andExpect(jsonPath("$[?(@.name == '%s')]", "Sílvia Belda").exists());
    }

    @Test
    void getByExistentIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/student/jwt/{student_id}", 27))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age", 15).exists())
                .andExpect(jsonPath("$.email", "silvia@gmail.com").exists())
                .andExpect(jsonPath("$.emailTutor", "tutor7@gmail.com").exists())
                .andExpect(jsonPath("$.jlptLevel", 5).exists())
                .andExpect(jsonPath("$.name", "Sílvia Belda").exists())
                .andExpect(jsonPath("$.priorKnowledge", "No tiene.").exists())
                .andExpect(jsonPath("$.tel", 1162641228).exists())
                .andExpect(jsonPath("$.teacherAsignedId", 6).exists());
    }

    @Test
    void getByInexistentIdTest() throws Exception {
        this.mvc.perform(get("/api/v1/student/jwt/{student_id}", 66))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void findTest() throws Exception {
        this.mvc.perform(get("/api/v1/student/jwt/find/{search_text}", "lio").param("page", "0").param("size", "5").requestAttr("user_id", 7))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Emilio Sanmartin").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Julio Palomares").exists());
    }

    @Test
    void findAllTest() throws Exception {
        this.mvc.perform(get("/api/v1/student/jwt/find").param("page", "0").param("size", "5").requestAttr("user_id", 7))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Victor Seoane").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Nahuel Fazio").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Emilio Sanmartin").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Sílvia Belda").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Elias Hurtado").exists())
                .andExpect(jsonPath("$.content[?(@.name == '%s')]", "Julio Palomares").doesNotExist());
    }

    @Test
    void deleteTest() throws Exception {
        this.mvc.perform(delete("/api/v1/student/jwt/{student_id}", 38))
                .andDo(print())
                .andExpect(status().isOk());

        this.mvc.perform(get("/api/v1/student/jwt/{student_id}", 38))
                .andExpect(status().isNotFound());
    }

    @Test
    void createTest() throws Exception {
        Object obj = new Object() {
            public String name = "Create Test";

            public int jlptLevel = 5;

            public int teacherAsignedId = 1;

            public String priorKnowledge = "Cosas interesantes sobre japon";

            public int tel = 1162641228;

            public String email = "create@gmail.com";

            public String emailTutor = "tutor27@gmail.com";

            public int age = 27;

            public Set<Interest> interests = new HashSet<Interest>();
        };

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        MvcResult result = this.mvc.perform(post("/api/v1/student/jwt").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson).requestAttr("user_id", 7))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 1).exists())
                .andExpect(jsonPath("$.name", "Create Test").exists())
                .andExpect(jsonPath("$.jlptLevel", 5).exists())
                .andExpect(jsonPath("$.priorKnowledge", "Cosas interesantes sobre japon").exists())
                .andExpect(jsonPath("$.tel", 1162641228).exists())
                .andExpect(jsonPath("$.email", "create@gmail.com").exists())
                .andExpect(jsonPath("$.emailTutor", "tutor27@gmail.com").exists())
                .andExpect(jsonPath("$.age", 27).exists())
                .andExpect(jsonPath("$.interests").isArray())
                .andReturn();
    }

    @Test
    void updateTest() throws Exception {

        Object obj = new Object() {
            public String name = "Create";

            public int jlptLevel = 4;

            public int teacherAsignedId = 6;

            public String priorKnowledge = "a";

            public int tel = 99999999;

            public String email = "create@gmail.com";

            public String emailTutor = "tutor27@gmail.com";

            public int age = 25;

            public Set<Interest> interests = new HashSet<Interest>();
        };

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        MvcResult result = this.mvc.perform(put("/api/v1/student/jwt/{student_id}", 38).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson).requestAttr("user_id", 7))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 1).exists())
                .andExpect(jsonPath("$.name", "Create").exists())
                .andExpect(jsonPath("$.jlptLevel", 4).exists())
                .andExpect(jsonPath("$.priorKnowledge", "a").exists())
                .andExpect(jsonPath("$.tel", 99999999).exists())
                .andExpect(jsonPath("$.email", "create@gmail.com").exists())
                .andExpect(jsonPath("$.emailTutor", "tutor27@gmail.com").exists())
                .andExpect(jsonPath("$.age", 25).exists())
                .andExpect(jsonPath("$.interests").isArray())
                .andReturn();
    }

    @Test
    void addLessonTest() throws Exception {

        Date newDate = new Date();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String stringDate = simpleDateFormat.format(newDate);

        Object obj = new Object() {
            public Date date = newDate;

            public int lessonNumber = 1;

            public String content = "The content";

            public String homework = "The homework";

            public List<LinkDTO> linkDTOS = new ArrayList<LinkDTO>();
        };

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(obj);

        MvcResult result = this.mvc.perform(post("/api/v1/student/jwt/addLesson/{student_id}", 38).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson).requestAttr("user_id", 7))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 38).exists())
                .andExpect(jsonPath("$.name", "asdasd").exists())
                .andExpect(jsonPath("$.lessons[?(@.lessonNumber == '%s')]", 1).exists())
                .andExpect(jsonPath("$.lessons[?(@.content == '%s')]", "The content").exists())
                .andExpect(jsonPath("$.lessons[?(@.homework == '%s')]", "The homework").exists())
                .andExpect(jsonPath("$.lessons[?(@.links)]").isArray())
                .andExpect(jsonPath("$.lessons[?(@.date == '%s')]", stringDate).exists())
                .andReturn();
    }

}