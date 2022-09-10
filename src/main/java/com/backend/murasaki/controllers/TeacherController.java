package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.TeacherDTO;
import com.backend.murasaki.exceptions.TeacherNotFoundException;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping(path = "")
    @ResponseBody
    public Teacher create(@RequestBody TeacherDTO dto) {
        return this.teacherService.save(dto);
    }

    @GetMapping(path = "")
    @ResponseBody
    public List<Teacher> getAll(){
        return this.teacherService.findAll();
    }

    @GetMapping(path = "{teacher_id}")
    @ResponseBody
    public Teacher getById(@PathVariable int teacher_id) throws TeacherNotFoundException {
        return this.teacherService.findById(teacher_id);
    }

}
