package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.StudentDTO;
import com.backend.murasaki.dtos.StudentDTOout;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "")
    @ResponseBody
    public List<StudentDTOout> getAll() {
        return this.studentService.findAll().stream().map(student -> new StudentDTOout(student.getId(), student.getName(), student.getJlptLevel(), student.getTeacherAssigned().toDTO(), student.getSchedule())).collect(Collectors.toList());
    }

    @GetMapping(path = "{student_id}")
    @ResponseBody
    public Student getById(@PathVariable int student_id) {
        return this.studentService.findById(student_id);
    }

    @PostMapping(path = "")
    @ResponseBody
    public Student create(@RequestBody StudentDTO dto) {
        return this.studentService.save(dto);
    }

    @GetMapping(path = "/search/{jlptLevel}")
    @ResponseBody
    public List<Student> searchByLevel(@PathVariable int jlptLevel){
        return this.studentService.searchByLevel(jlptLevel);
    }

    @GetMapping(path = "/searchByTeacher/{teacher_id}")
    @ResponseBody
    public List<Student> searchByTeacher(@PathVariable int teacher_id) {
        return this.studentService.searchByTeacher(teacher_id);
    }

    @PostMapping(path = "/template")
    public void setTemplate(){
        this.studentService.setTemplate();
    }

}
