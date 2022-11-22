package com.backend.murasaki.models.controllers;

import com.backend.murasaki.dtos.InterestDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Interest;
import com.backend.murasaki.services.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interest")
public class InterestController {

    @Autowired
    private InterestService interestService;

    @GetMapping(path = "/jwt")
    @ResponseBody
    public List<Interest> getAll() {
        return this.interestService.findAll();
    }

    @GetMapping(path = "/jwt/{interest_id}")
    @ResponseBody
    public Interest getById(@PathVariable int interest_id) throws NotFoundException {
        return this.interestService.findById(interest_id);
    }

    @PostMapping(path = "/jwt")
    @ResponseBody
    public Interest create(@RequestBody InterestDTO dto) {
        return this.interestService.save(dto);
    }

    @GetMapping(path = "/jwt/find/{search_text}")
    @ResponseBody
    public Page<Interest> find(@PathVariable String search_text, @RequestParam int page, @RequestParam int size){
        return this.interestService.find(search_text, page, size);
    }

    @GetMapping(path = "/jwt/find")
    @ResponseBody
    public Page<Interest> findAll(@RequestParam int page, @RequestParam int size){
        return this.interestService.find("", page, size);
    }

    @DeleteMapping(path = "/jwt/{interest_id}")
    public void delete(@PathVariable int interest_id){
        this.interestService.delete(interest_id);
    }

}
