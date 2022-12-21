package com.backend.murasaki.services;

import com.backend.murasaki.dtos.RealSchedule;
import com.backend.murasaki.dtos.ScheduleDTO;
import com.backend.murasaki.dtos.TeacherDTO;
import com.backend.murasaki.dtos.TranslateStudentDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.models.User;
import com.backend.murasaki.repositories.TeacherRepository;
import com.backend.murasaki.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Teacher getActualTeacher(int user_id){
        User user = this.userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("The requested user was not found"));
        return user.getTeacher();
    }

    @Transactional
    public Teacher save(TeacherDTO dto) {
        Teacher teacher = new Teacher(dto.getName());
        return this.teacherRepository.save(teacher);
    }

    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        List<Teacher> teachers = this.teacherRepository.findAll();
        return teachers.stream().filter(teacher -> teacher.getId() != 0).toList();
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
    public Page<Teacher> find(Integer user_id, String search_text, int page, int size){
        User user = this.userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("The requested user was not found"));
        Pageable p = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        List<Integer> idsToFilter = new ArrayList<Integer>();
        idsToFilter.add(0);
        idsToFilter.add(user.getTeacher().getId());
        return this.teacherRepository.findByNameLikeAndIdNotIn(p, "%"+search_text+"%", idsToFilter);
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

    @Transactional
    public ScheduleDTO getTeacherSchedules(int user_id){
        User userFound = this.userRepository.findById(user_id).orElseThrow(() -> new NotFoundException("The requested user was not found"));
        if(userFound.getRole().getName().equals("administrator")){
            List<Teacher> teachers = this.teacherRepository.findAll();
            List<ScheduleDTO> dtos = teachers.stream().map(t -> t.getScheduleDTO()).toList();
            ScheduleDTO res = new ScheduleDTO(
                    new ArrayList<RealSchedule>(),
                    new ArrayList<RealSchedule>(),
                    new ArrayList<RealSchedule>(),
                    new ArrayList<RealSchedule>(),
                    new ArrayList<RealSchedule>(),
                    new ArrayList<RealSchedule>(),
                    new ArrayList<RealSchedule>()
            );
            for(int i = 0; i < dtos.size(); i++) {
                ScheduleDTO actual = dtos.get(i);
                res.merge(actual);
            }
            return res;
        }else{
            return userFound.getTeacher().getScheduleDTO();
        }
    }

}
