package com.backend.murasaki.services;

import com.backend.murasaki.dtos.HomeWorkDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.HomeWork;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.repositories.HomeWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomeWorkService {

    @Autowired
    private HomeWorkRepository homeWorkRepository;

    @Autowired
    private StudentService studentService;

    @Transactional
    public HomeWork save(HomeWorkDTO dto, int student_id) {
        Student student = this.studentService.findById(student_id);
        HomeWork homeWork = new HomeWork(dto.getTitle(), dto.getDescription(), dto.getMultipleChoise(), student);
        return this.homeWorkRepository.save(homeWork);
    }

    @Transactional
    public List<HomeWork> findAll(){
        return this.homeWorkRepository.findAll();
    }

    @Transactional
    public HomeWork findById(int homework_id) {
        return this.homeWorkRepository.findById(homework_id).orElseThrow(() -> new NotFoundException("The requested homework was not found."));
    }

}
