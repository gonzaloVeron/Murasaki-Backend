package com.backend.murasaki.services;

import com.backend.murasaki.dtos.*;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Interest;
import com.backend.murasaki.models.Lesson;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.repositories.LessonRepository;
import com.backend.murasaki.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;

@Service
public class StudentService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InterestService interestService;

    @Autowired
    private LessonRepository lessonRepository; // plantear mejor solucion para usar el service

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

    @Transactional
    public Student addInterest(AddInterestDTO dto){
        Student student = this.findById(dto.getStudent_id());
        Interest interest = this.interestService.findById(dto.getInterest_id());
        student.addInterest(interest);
        return this.studentRepository.save(student);
    }

    @Transactional
    public Student addLesson(LessonDTO dto, int student_id){
        Student student = this.findById(student_id);
        Lesson lesson = new Lesson(dto.getDate(), dto.getLessonNumber(), dto.getContent(), dto.getHomework());
        this.lessonRepository.save(lesson);
        student.addLesson(lesson);
        return this.studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public Page<StudentDTOout> find(String search_text, int page, int size){
        //Pageable page = PageRequest.of(1, 2, Sort.by("name"));
        Pageable p = PageRequest.of(page, size);
        Page<StudentDTOout> students;
        if(this.isInteger(search_text)){
            students = this.studentRepository.findByJlptLevel(p, Integer.parseInt(search_text)).map(student -> new StudentDTOout(student.getId(), student.getName(), student.getJlptLevel(), student.getTeacherAssigned().toDTO()));
        }else{
            students = this.studentRepository.findByTeacherAssignedNameLike(p,"%"+search_text+"%").map(student -> new StudentDTOout(student.getId(), student.getName(), student.getJlptLevel(), student.getTeacherAssigned().toDTO()));
        }
        return students;
    }

    private boolean isInteger(String str){
        return Pattern.matches("-?[0-9]+", str);
    }

}