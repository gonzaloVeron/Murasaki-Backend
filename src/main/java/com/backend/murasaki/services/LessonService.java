package com.backend.murasaki.services;

import com.backend.murasaki.dtos.LessonDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.*;
import com.backend.murasaki.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LinkService linkService;

    @Transactional
    public Lesson save(LessonDTO dto) {
        ArrayList<Link> links = new ArrayList<Link>(dto.getLinkDTOS().stream().map(dTo -> new Link(dTo.getTitle(), dTo.getUrl())).toList());
        Lesson lesson = new Lesson(dto.getDate(), dto.getLessonNumber(), dto.getContent(), dto.getHomework(), links);
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

    @Transactional
    public Lesson update(int lesson_id, LessonDTO dto){
        Lesson lessonFound = this.findById(lesson_id);
        lessonFound.setLessonNumber(dto.getLessonNumber());
        lessonFound.setContent(dto.getContent());
        lessonFound.setDate(dto.getDate());
        lessonFound.setHomework(dto.getHomework());
        List<Link> links = dto.getLinkDTOS().stream().map(dTo -> new Link(dTo.getTitle(), dTo.getUrl())).toList();
        lessonFound.setLinks(links);
        return this.lessonRepository.save(lessonFound);
    }

}
