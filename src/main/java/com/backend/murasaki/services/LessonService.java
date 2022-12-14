package com.backend.murasaki.services;

import com.backend.murasaki.dtos.LessonDTO;
import com.backend.murasaki.dtos.LinkDTO;
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
        Lesson lesson = new Lesson(dto.getDate(), dto.getLessonNumber(), dto.getContent(), dto.getHomework(), links, dto.getTitle());
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
        lessonFound.setTitle(dto.getTitle());

        List<Link> newLinks = dto.getLinkDTOS().stream().filter(ldto -> ldto.getId() == null).map(ldto -> new Link(ldto.getTitle(), ldto.getUrl())).toList();

        List<Link> toUpdateLinks = dto.getLinkDTOS().stream().filter(ldto -> ldto.getId() != null).map(ldto -> new Link(ldto.getId(), ldto.getTitle(), ldto.getUrl())).toList();

        List<Integer> ids = dto.getLinkDTOS().stream().map(dTo -> dTo.getId()).toList();

        List<Link> linksToRemove = this.filterLinksByIds(lessonFound.getLinks(), ids);

        for(int i = 0; i < linksToRemove.size(); i++){
            lessonFound.getLinks().remove(linksToRemove.get(i));
        }

        for(int i = 0; i < toUpdateLinks.size(); i++){
            Link actual = toUpdateLinks.get(i);
            Link found = lessonFound.getLinks().stream().filter(link -> link.getId() == actual.getId()).findFirst().orElseThrow(() -> new NotFoundException("The link requested was not found."));
            found.setTitle(actual.getTitle());
            found.setUrl(actual.getUrl());
        }

        lessonFound.getLinks().addAll(newLinks);

        return this.lessonRepository.save(lessonFound);
    }

    private List<Link> filterLinksByIds(List<Link> links, List<Integer> ids){
        return links.stream().filter(link -> !ids.stream().anyMatch(n -> n == link.getId())).toList();
    }

}
