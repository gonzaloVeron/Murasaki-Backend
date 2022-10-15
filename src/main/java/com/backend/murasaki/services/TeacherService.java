package com.backend.murasaki.services;

import com.backend.murasaki.dtos.TeacherDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Teacher findByName(String teacherName) {
        return this.teacherRepository.findByName(teacherName).orElseThrow(() -> new NotFoundException("The requested teacher was not found."));
    }

    @Transactional(readOnly = true)
    public Teacher findById(int teacher_id) {
        return this.teacherRepository.findById(teacher_id).orElseThrow(() -> new NotFoundException("The requested teacher was not found."));
    }

    @Transactional(readOnly = true)
    public Page<Teacher> find(String search_text, int page, int size){
        Pageable p = PageRequest.of(page, size);
        return this.teacherRepository.findByNameLike(p, "%"+search_text+"%");
    }

    @Transactional
    public void delete(int teacher_id){
        Teacher teacher = this.findById(teacher_id);
        this.teacherRepository.delete(teacher);
    }

    @Transactional
    public Teacher update(int teacher_id, TeacherDTO dto){
        Teacher teacher = this.findById(teacher_id);
        teacher.setName(dto.getName());
        return this.teacherRepository.save(teacher);
    }

}
