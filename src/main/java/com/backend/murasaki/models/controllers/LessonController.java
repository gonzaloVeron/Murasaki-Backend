package com.backend.murasaki.models.controllers;

import com.backend.murasaki.dtos.LessonDTO;
import com.backend.murasaki.models.Lesson;
import com.backend.murasaki.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping(path = "")
    @ResponseBody
    public List<Lesson> getAll() {
        return this.lessonService.findAll();
    }

    @GetMapping(path = "{lesson_id}")
    @ResponseBody
    public Lesson getById(@PathVariable int lesson_id) {
        return this.lessonService.findById(lesson_id);
    }

    @PutMapping(path = "/jwt/{lesson_id}")
    @ResponseBody
    public Lesson updateLesson(@PathVariable int lesson_id, @RequestBody LessonDTO dto){
        return this.lessonService.update(lesson_id, dto);
    }

}
