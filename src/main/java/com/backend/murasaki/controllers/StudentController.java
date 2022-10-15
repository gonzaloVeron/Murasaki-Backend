package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.*;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return this.studentService.findAll().stream().map(student -> new StudentDTOout(student.getId(), student.getName(), student.getJlptLevel(), student.getTeacherAssigned().toDTO())).collect(Collectors.toList());
    }

    @GetMapping(path = "{student_id}")
    @ResponseBody
    public StudentDTO getById(@PathVariable int student_id) {
        return this.studentService.findByIdDTO(student_id);
    }

    @PostMapping(path = "")
    @ResponseBody
    public Student create(@RequestBody StudentDTO dto) {
        return this.studentService.save(dto);
    }

    @PutMapping(path = "{student_id}")
    @ResponseBody
    public Student update(@PathVariable int student_id, @RequestBody StudentDTO dto) {
        return this.studentService.update(student_id, dto);
    }

    @PostMapping(path = "/addInterest")
    @ResponseBody
    public Student addInterest(@RequestBody AddInterestDTO dto){
        return this.studentService.addInterest(dto);
    }

    @PostMapping(path = "/addLesson/{student_id}")
    @ResponseBody
    public Student addLesson(@RequestBody LessonDTO dto, @PathVariable int student_id){
        return this.studentService.addLesson(dto, student_id);
    }

    @GetMapping(path = "/find/{search_text}")
    @ResponseBody
    public Page<StudentDTOout> find(@PathVariable String search_text, @RequestParam int page, @RequestParam int size){
        return this.studentService.find(search_text, page, size);
    }

    @GetMapping(path = "/find")
    @ResponseBody
    public Page<StudentDTOout> findAll(@RequestParam int page, @RequestParam int size){
        return this.studentService.find("", page, size);
    }

    @DeleteMapping(path = "{student_id}")
    public void delete(@PathVariable int student_id){
        this.studentService.delete(student_id);
    }

}
