package com.backend.murasaki.services;

import com.backend.murasaki.dtos.SearchStudentByDTO;
import com.backend.murasaki.dtos.StudentDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentRepository studentRepository;


    @Transactional
    public Student save(StudentDTO dto) {
        Teacher teacher = this.teacherService.findById(dto.getTeacherAsignedId());
        Student student = new Student(dto.getName(), dto.getJlptLevel(), teacher, dto.getPriorKnowledge(), dto.getAge());
        this.studentRepository.save(student);
        return student;
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student findById(int student_id) {
        return this.studentRepository.findById(student_id).orElseThrow(() -> new NotFoundException("The requested Student was not found."));
    }

    @Transactional(readOnly = true)
    public List<Student> searchByLevel(SearchStudentByDTO dto){
        if(dto.getLevel() > 0){
            return this.studentRepository.findByJlptLevel(dto.getLevel());
        }else{
            return this.findAll();
        }
    }

    @Transactional(readOnly = true)
    public List<Student> searchByTeacherName(SearchStudentByDTO dto) {
        if(dto.getTeacherName() != ""){
            Teacher teacher = this.teacherService.findByName(dto.getTeacherName());
            return this.studentRepository.findByTeacherAssigned(teacher);
        }else{
            return this.findAll();
        }
    }

    @Transactional(readOnly = true)
    public List<Student> searchByTeacher(int teacher_id) {
        Teacher teacher = this.teacherService.findById(teacher_id);
        return this.studentRepository.findByTeacherAssigned(teacher);
    }

}