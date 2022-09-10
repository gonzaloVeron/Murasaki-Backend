package com.backend.murasaki.services;

import com.backend.murasaki.dtos.StudentDTO;
import com.backend.murasaki.exceptions.StudentNotFoundException;
import com.backend.murasaki.exceptions.StudentNotFoundExceptionSupplier;
import com.backend.murasaki.exceptions.TeacherNotFoundException;
import com.backend.murasaki.exceptions.TeacherNotFoundExceptionSupplier;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.repositories.StudentRepository;
import com.backend.murasaki.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student save(StudentDTO dto) throws TeacherNotFoundException {
        Teacher teacher = this.teacherRepository.findById(dto.getTeacherAsignedId()).orElseThrow(new TeacherNotFoundExceptionSupplier());
        Student student = new Student(dto.getName(), dto.getJlptLevel(), teacher);
        this.studentRepository.save(student);
        return student;
    }

    @Transactional
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Transactional
    public Student findById(int student_id) throws StudentNotFoundException {
        return this.studentRepository.findById(student_id).orElseThrow(new StudentNotFoundExceptionSupplier());
    }



}
