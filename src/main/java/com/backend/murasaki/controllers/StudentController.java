package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.*;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/student/jwt")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "")
    @ResponseBody
    public List<StudentDTOout> getAll() {
        return this.studentService.findAll().stream().map(student -> new StudentDTOout(student.getId(), student.getName(), student.getJlptLevel(), student.getEmail(), student.getTel(), student.getTeacherAssigned().toDTO())).collect(Collectors.toList());
    }

    @GetMapping(path = "/{student_id}")
    @ResponseBody
    public StudentDTO getById(@PathVariable int student_id) {
        return this.studentService.findByIdDTO(student_id);
    }

    @PostMapping(path = "")
    @ResponseBody
    public Student create(HttpServletRequest request, @RequestBody StudentDTO dto) {
//        String user_role = (String)request.getAttribute("user_role");
        int user_id = (int)request.getAttribute("user_id");
        return this.studentService.save(user_id, dto);
    }

    @PutMapping(path = "/{student_id}")
    @ResponseBody
    public Student update(HttpServletRequest request, @PathVariable int student_id, @RequestBody StudentDTO dto) {
        int user_id = (int)request.getAttribute("user_id");
        return this.studentService.update(user_id, student_id, dto);
    }

//    @PostMapping(path = "/addInterest")
//    @ResponseBody
//    public Student addInterest(@RequestBody AddInterestDTO dto){
//        return this.studentService.addInterest(dto);
//    }

    @PostMapping(path = "/addLesson/{student_id}")
    @ResponseBody
    public Student addLesson(@RequestBody LessonDTO dto, @PathVariable int student_id){
        return this.studentService.addLesson(dto, student_id);
    }

    @PutMapping(path = "/{student_id}/{lesson_id}")
    public void removeLesson(@PathVariable int student_id, @PathVariable int lesson_id){
        this.studentService.removeLesson(student_id, lesson_id);
    }

    @GetMapping(path = "/find/{search_text}")
    @ResponseBody
    public Page<StudentDTOout> find(HttpServletRequest request, @PathVariable String search_text, @RequestParam int page, @RequestParam int size){
        String user_role = (String)request.getAttribute("user_role");
        int user_id = (int)request.getAttribute("user_id");
        return this.studentService.find(user_id, user_role, search_text, page, size);
    }

    @GetMapping(path = "/find")
    @ResponseBody
    public Page<StudentDTOout> findAll(HttpServletRequest request, @RequestParam int page, @RequestParam int size){
        String user_role = (String)request.getAttribute("user_role");
        int user_id = (int)request.getAttribute("user_id");
        return this.studentService.find(user_id, user_role, "", page, size);
    }

    @DeleteMapping(path = "/{student_id}")
    public void delete(@PathVariable int student_id){
        this.studentService.delete(student_id);
    }

    @PostMapping(path = "/{source_teacher_id}/{target_teacher_id}")
    public void translateStudents(@PathVariable int source_teacher_id, @PathVariable int target_teacher_id, @RequestBody TranslateStudentDTO dto){
        this.studentService.translateStudents(source_teacher_id, target_teacher_id, dto);
    }

}
