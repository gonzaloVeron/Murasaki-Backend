package com.backend.murasaki.models.controllers;

import com.backend.murasaki.dtos.TeacherDTO;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Teacher getById(@PathVariable int teacher_id) {
        return this.teacherService.findById(teacher_id);
    }

    @GetMapping(path = "/find/{search_text}")
    @ResponseBody
    public Page<Teacher> find(@PathVariable String search_text, @RequestParam int page, @RequestParam int size){
        return this.teacherService.find(search_text, page, size);
    }

    @GetMapping(path = "/find")
    @ResponseBody
    public Page<Teacher> findAll(@RequestParam int page, @RequestParam int size){
        return this.teacherService.find("", page, size);
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



}
