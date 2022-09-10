package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.StudentDTO;
import com.backend.murasaki.exceptions.TeacherNotFoundException;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "")
    @ResponseBody
    public List<Student> getAll() {
        return this.studentService.findAll();
    }

    @GetMapping(path = "{student_id}")
    @ResponseBody
    public Student getById(@PathVariable int student_id) {
        return this.studentService.findById(student_id);
    }

    @PostMapping(path = "")
    @ResponseBody
    public Student create(@RequestBody StudentDTO dto) throws TeacherNotFoundException {
        return this.studentService.save(dto);
    }

}
