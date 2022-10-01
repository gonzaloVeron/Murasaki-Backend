package com.backend.murasaki.services;

import com.backend.murasaki.dtos.LessonDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Lesson;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private StudentService studentService;

    @Transactional
    public Lesson save(LessonDTO dto, int student_id) {
        Student student = this.studentService.findById(student_id);
        Lesson lesson = new Lesson(dto.getDate(), dto.getLessonNumber(), dto.getContent(), dto.getHomework());
        student.addLesson(lesson);
        return this.lessonRepository.save(lesson);
    }

    @Transactional(readOnly = true)
    public List<Lesson> findAll(){
        return this.lessonRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Lesson findById(int lesson_id) {
        return this.lessonRepository.findById(lesson_id).orElseThrow(() -> new NotFoundException("The requested lesson was not found."));
    }
}
