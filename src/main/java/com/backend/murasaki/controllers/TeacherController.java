package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.ScheduleDTO;
import com.backend.murasaki.dtos.TeacherDTO;
import com.backend.murasaki.dtos.TranslateStudentDTO;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/teacher/jwt")
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
    public Teacher getById(@PathVariable int teacher_id) {
        return this.teacherService.findById(teacher_id);
    }

    @GetMapping(path = "/find/{search_text}")
    @ResponseBody
    public Page<Teacher> find(HttpServletRequest request, @PathVariable String search_text, @RequestParam int page, @RequestParam int size){
        int user_id = (int)request.getAttribute("user_id");
        return this.teacherService.find(user_id, search_text, page, size);
    }

    @GetMapping(path = "/find")
    @ResponseBody
    public Page<Teacher> findAll(HttpServletRequest request, @RequestParam int page, @RequestParam int size){
        int user_id = (int)request.getAttribute("user_id");
        return this.teacherService.find(user_id,"", page, size);
    }

    @DeleteMapping(path = "{teacher_id}")
    public void delete(@PathVariable int teacher_id){
        this.teacherService.delete(teacher_id);
    }

    @PutMapping(path = "{teacher_id}")
    @ResponseBody
    public Teacher update(@PathVariable int teacher_id, @RequestBody TeacherDTO dto) {
        return this.teacherService.update(teacher_id, dto);
    }

    @GetMapping(path = "/actual")
    @ResponseBody
    public Teacher actualTeacher(HttpServletRequest request){
        int user_id = (int)request.getAttribute("user_id");
        return this.teacherService.getActualTeacher(user_id);
    }

    @GetMapping(path = "/schedules")
    @ResponseBody
    public ScheduleDTO getTeacherSchedules(HttpServletRequest request){
        int user_id = (int)request.getAttribute("user_id");
        return this.teacherService.getTeacherSchedules(user_id);
    }

}
