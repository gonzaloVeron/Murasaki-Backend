package com.backend.murasaki.services;

import com.backend.murasaki.dtos.*;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.*;
import com.backend.murasaki.repositories.LessonRepository;
import com.backend.murasaki.repositories.ScheduleRepository;
import com.backend.murasaki.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;
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
    private UserService userService;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Transactional
    public Student save(int user_id, StudentDTO dto) {
        User user = this.userService.findById(user_id);
        String userRole = user.getRole().getName();
        Teacher teacher;
        List<Integer> interestIds = dto.getInterests().stream().map(Interest::getId).toList();
        Set<Interest> interests = this.interestService.findAll().stream().filter(interest -> interestIds.stream().anyMatch(i -> i == interest.getId())).collect(Collectors.toSet());
        if(Objects.equals(userRole, "administrator")){
            teacher = this.teacherService.findById(dto.getTeacherAsignedId());
        }else{
            teacher = this.teacherService.findById(user.getTeacher().getId());
        }
        String emailTutor = (dto.getEmailTutor() != null) ? dto.getEmailTutor() : "No tiene";
        Student student = new Student(dto.getName(), dto.getJlptLevel(), teacher, dto.getPriorKnowledge(), dto.getAge(), dto.getTel(), dto.getEmail(), emailTutor, interests, new ArrayList<>(), dto.getSchedules());
        this.studentRepository.save(student);
        return student;
    }

    @Transactional
    public Student update(int user_id, int student_id, StudentDTO dto){
        User userFound = this.userService.findById(user_id);
        //dto.setTeacherAsignedId(userFound.getTeacher().getId());
        Student student = this.findById(student_id);
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        student.setName(dto.getName());
        student.setEmailTutor(dto.getEmailTutor());
        student.setJlptLevel(dto.getJlptLevel());
        student.setPriorKnowledge(dto.getPriorKnowledge());
        student.setTel(dto.getTel());
        if(userFound.getRole().getName().equals("administrator")){
            Teacher teacher = this.teacherService.findById(dto.getTeacherAsignedId());
            student.setTeacherAssigned(teacher);
        }
//        if(student.getTeacherAssigned().getId() != dto.getTeacherAsignedId()){
//            Teacher teacher = this.teacherService.findById(dto.getTeacherAsignedId());
//            student.setTeacherAssigned(teacher);
//        }
        List<Integer> interestIds = dto.getInterests().stream().map(Interest::getId).toList();
        Set<Interest> interests = this.interestService.findAll().stream().filter(interest -> interestIds.stream().anyMatch(i -> i == interest.getId())).collect(Collectors.toSet());
        student.setInterests(interests);
        return student;
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student findById(int student_id) {
        return this.studentRepository.findById(student_id).orElseThrow(() -> new RuntimeException("The requested Student was not found."));
    }

    @Transactional(readOnly = true)
    public StudentDTO findByIdDTO(int student_id) {
        StudentDTO dto = this.findById(student_id).toDTO();
        dto.getLessons().sort(Comparator.comparing(Lesson::getDate));
        return dto;
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
        ArrayList<Link> links = new ArrayList<Link>(dto.getLinkDTOS().stream().map(dTo -> new Link(dTo.getTitle(), dTo.getUrl())).toList());
        //List<Link> links = dto.getLinkDTOS().stream().map(dTo -> new Link(dTo.getTitle(), dTo.getUrl())).toList();
        Lesson lesson = new Lesson(dto.getDate(), dto.getLessonNumber(), dto.getContent(), dto.getHomework(), links, dto.getTitle());
        this.lessonRepository.save(lesson);
        student.addLesson(lesson);
        return this.studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public Page<StudentDTOout> find(int user_id, String user_role, String search_text, int page, int size){
        Pageable p = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
//        Pageable p = PageRequest.of(page, size);
        Page<Student> students;
        if(Objects.equals(user_role, "administrator")){
            if(this.isInteger(search_text)){
                students = this.studentRepository.findByJlptLevel(p, Integer.parseInt(search_text));
            }else{
                students = this.studentRepository.findByNameLike(p,"%"+search_text+"%");
            }
        }else{
            User user = this.userService.findById(user_id);
            if(this.isInteger(search_text)){
                students = this.studentRepository.findByJlptLevelAndTeacherAssigned(p, Integer.parseInt(search_text), user.getTeacher());
            }else{
                students = this.studentRepository.findByNameLikeAndTeacherAssigned(p,"%"+search_text+"%", user.getTeacher());
            }
        }
        return students.map(student -> new StudentDTOout(student.getId(), student.getName(), student.getJlptLevel(), student.getEmail(), student.getTel(), student.getTeacherAssigned().toDTO(), student.getStatus()));
    }

    private boolean isInteger(String str){
        return Pattern.matches("-?[0-9]+", str);
    }

    @Transactional
    public void delete(int student_id){
        Student student = this.findById(student_id);
        this.studentRepository.delete(student);
    }

    @Transactional
    public void changeStatus(int student_id){
        Student studentFound = this.findById(student_id);
        studentFound.setStatus(!studentFound.getStatus());
        this.studentRepository.save(studentFound);
    }

    @Transactional
    public void removeLesson(int student_id, int lesson_id){
        Student studentFound = this.findById(student_id);
        Lesson lessonFound = studentFound.getLessons().stream().filter(lesson -> lesson.getId() == lesson_id).findFirst().orElseThrow(() -> new NotFoundException("The requested Lesson was not found."));
        studentFound.deleteLesson(lessonFound);
        this.studentRepository.save(studentFound);
    }

    @Transactional
    public void translateStudents(int source_teacher_id, int target_teacher_id, TranslateStudentDTO dto){
        Teacher sourceTeacher = this.teacherService.findById(source_teacher_id);
        Teacher targetTeacher = this.teacherService.findById(target_teacher_id);
        List<Student> sourceStudents = this.studentRepository.findByIdIn(dto.getSourceIds());
        List<Student> targetStudents = this.studentRepository.findByIdIn(dto.getTargetIds());
        sourceStudents.forEach(sourceStudent -> {
            sourceStudent.setTeacherAssigned(sourceTeacher);
        });
        targetStudents.forEach(targetStudent -> {
            targetStudent.setTeacherAssigned(targetTeacher);
        });
        this.studentRepository.saveAll(sourceStudents);
        this.studentRepository.saveAll(targetStudents);
    }

    @Transactional
    public Student removeSchedule(int student_id, int schedule_id){
        Student studentFound = this.findById(student_id);
        Schedule schedule = this.scheduleRepository.findById(schedule_id).orElseThrow(() -> new NotFoundException("The requested schedule was not found"));
        studentFound.getSchedules().remove(schedule);
        return this.studentRepository.save(studentFound);
    }

    @Transactional
    public Student addSchedule(int student_id, Schedule sche){
        Student studentFound = this.findById(student_id);
        studentFound.getSchedules().add(sche);
        return this.studentRepository.save(studentFound);
    }

}