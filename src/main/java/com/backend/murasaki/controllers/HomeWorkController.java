package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.HomeWorkDTO;
import com.backend.murasaki.models.HomeWork;
import com.backend.murasaki.services.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/homework")
public class HomeWorkController {

    @Autowired
    private HomeWorkService homeWorkService;

    @GetMapping("")
    @ResponseBody
    public List<HomeWork> getAll() {
        return this.homeWorkService.findAll();
    }

    @GetMapping("{homework_id}")
    @ResponseBody
    public HomeWork getById(@PathVariable int homework_id) {
        return this.homeWorkService.findById(homework_id);
    }

    @PostMapping("{student_id}")
    @ResponseBody
    public HomeWork create(@RequestBody HomeWorkDTO dto, @PathVariable int student_id) {
        return this.homeWorkService.save(dto, student_id);
    }

}
