package com.backend.murasaki.controllers;

import com.backend.murasaki.dtos.InterestDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Interest;
import com.backend.murasaki.services.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interest/jwt")
public class InterestController {

    @Autowired
    private InterestService interestService;

    @GetMapping(path = "")
    @ResponseBody
    public List<Interest> getAll() {
        return this.interestService.findAll();
    }

    @GetMapping(path = "/{interest_id}")
    @ResponseBody
    public Interest getById(@PathVariable int interest_id) throws NotFoundException {
        return this.interestService.findById(interest_id);
    }

    @PostMapping(path = "")
    @ResponseBody
    public Interest create(@RequestBody InterestDTO dto) {
        return this.interestService.save(dto);
    }

    @GetMapping(path = "/find/{search_text}")
    @ResponseBody
    public Page<Interest> find(@PathVariable String search_text, @RequestParam int page, @RequestParam int size){
        return this.interestService.find(search_text, page, size);
    }

    @GetMapping(path = "/find")
    @ResponseBody
    public Page<Interest> findAll(@RequestParam int page, @RequestParam int size){
        return this.interestService.find("", page, size);
    }

    @DeleteMapping(path = "/{interest_id}")
    public void delete(@PathVariable int interest_id){
        this.interestService.delete(interest_id);
    }

}
