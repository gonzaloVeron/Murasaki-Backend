package com.backend.murasaki.services;

import com.backend.murasaki.dtos.TeacherDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional
    public Teacher save(TeacherDTO dto) {
        Teacher teacher = new Teacher(dto.getName(), new ArrayList<Student>());
        return this.teacherRepository.save(teacher);
    }

    @Transactional
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Transactional
    public Teacher findById(int teacher_id) {
        return this.teacherRepository.findById(teacher_id).orElseThrow(() -> new NotFoundException("The requested teacher was not found."));
    }

}
