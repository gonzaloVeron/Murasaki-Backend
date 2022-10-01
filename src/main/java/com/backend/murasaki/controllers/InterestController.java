package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.InterestDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Interest;
import com.backend.murasaki.services.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interest")
public class InterestController {

    @Autowired
    private InterestService interestService;

    @GetMapping("")
    @ResponseBody
    public List<Interest> getAll() {
        return this.interestService.findAll();
    }

    @GetMapping("{interest_id}")
    @ResponseBody
    public Interest getById(@PathVariable int interest_id) throws NotFoundException {
        return this.interestService.findById(interest_id);
    }

    @PostMapping("")
    @ResponseBody
    public Interest create(@RequestBody InterestDTO dto) {
        return this.interestService.save(dto);
    }

}
