package com.backend.murasaki.controllers;

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

    @GetMapping("")
    @ResponseBody
    public List<Lesson> getAll() {
        return this.lessonService.findAll();
    }

    @GetMapping("{lesson_id}")
    @ResponseBody
    public Lesson getById(@PathVariable int homework_id) {
        return this.lessonService.findById(homework_id);
    }

    @PostMapping("")
    @ResponseBody
    public Lesson create(@RequestBody LessonDTO dto) {
        return this.lessonService.save(dto);
    }

}
