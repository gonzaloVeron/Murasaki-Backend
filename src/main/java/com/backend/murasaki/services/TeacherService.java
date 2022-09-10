package com.backend.murasaki.services;

import com.backend.murasaki.dtos.TeacherDTO;
import com.backend.murasaki.exceptions.TeacherNotFoundException;
import com.backend.murasaki.exceptions.TeacherNotFoundExceptionSupplier;
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
        List<Student> initialStudents = new ArrayList<Student>();
        Teacher teacher = new Teacher(dto.getName(), initialStudents);
        return this.teacherRepository.save(teacher);
    }

    @Transactional
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Transactional
    public Teacher findById(int teacher_id) throws TeacherNotFoundException {
        return this.teacherRepository.findById(teacher_id).orElseThrow(new TeacherNotFoundExceptionSupplier());
    }

}
