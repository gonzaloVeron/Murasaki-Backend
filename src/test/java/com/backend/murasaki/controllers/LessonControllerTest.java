package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.LessonDTO;
import com.backend.murasaki.models.Lesson;
import com.backend.murasaki.models.Link;
import com.backend.murasaki.services.JwtService;
import com.backend.murasaki.services.LessonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(JwtService.class)
@WebMvcTest(LessonController.class)
class LessonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LessonService lessonService;

    private List<Lesson> testLessons;

    private Lesson testLesson;

    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date();
        testLessons = new ArrayList<Lesson>();
        testLesson = new Lesson(5, new Date(), 2, "content", "homework", new ArrayList<Link>());
        testLessons.add(testLesson);

        when(lessonService.findAll()).thenReturn(testLessons);
        when(lessonService.findById(5)).thenReturn(testLesson);
        when(lessonService.save(ArgumentMatchers.any(LessonDTO.class))).thenReturn(testLesson);
    }

    @Test
    void getAllTest() throws Exception {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String stringDate = simpleDateFormat.format(date);
        this.mvc.perform(get("/api/v1/lesson"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[*].id").value(5))
                .andExpect(jsonPath("$[*].date").value(stringDate))
                .andExpect(jsonPath("$[*].lessonNumber").value(2))
                .andExpect(jsonPath("$[*].content").value("content"))
                .andExpect(jsonPath("$[*].homework").value("homework"))
                .andExpect(jsonPath("$[?(@.links)]").isArray());
    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

}