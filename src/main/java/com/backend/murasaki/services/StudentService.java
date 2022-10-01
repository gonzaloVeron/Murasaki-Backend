package com.backend.murasaki.services;

import com.backend.murasaki.dtos.StudentDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.HomeWork;
import com.backend.murasaki.models.Interest;
import com.backend.murasaki.models.Student;
import com.backend.murasaki.models.Teacher;
import com.backend.murasaki.repositories.HomeWorkRepository;
import com.backend.murasaki.repositories.InterestRespository;
import com.backend.murasaki.repositories.StudentRepository;
import com.backend.murasaki.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private HomeWorkRepository homeWorkRepository;

    @Autowired
    private InterestRespository interestRespository;

    @Transactional
    public Student save(StudentDTO dto) {
        Teacher teacher = this.teacherRepository.findById(dto.getTeacherAsignedId()).orElseThrow(() -> new NotFoundException("The requested teacher was not found."));
        Student student = new Student(dto.getName(), dto.getJlptLevel(), teacher, dto.getSchedule(), dto.getPriorKnowledge(), dto.getAge());
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
    public List<Student> searchByLevel(int jlptLevel){
        return this.studentRepository.findByJlptLevel(jlptLevel);
    }

    @Transactional(readOnly = true)
    public List<Student> searchByTeacher(int teacher_id) {
        Teacher teacher = this.teacherRepository.findById(teacher_id).orElseThrow(() -> new NotFoundException("The requested Teacher was not found."));
        return this.studentRepository.findByTeacherAssigned(teacher);
    }

    @Transactional
    public void setTemplate(){

        Date date = new Date();

        List<Student> ls1 = new ArrayList<Student>();
        List<Student> ls2 = new ArrayList<Student>();

        Teacher t1 = new Teacher("Borja Julian", ls1);
        Teacher t2 = new Teacher("Anastasia Acevedo", ls2);

        Student s1 = new Student("Isidoro Cebrian", 3, t1, date, "Sabe escribir en Hiragana y Katakana.", 17);
        Student s2 = new Student("Alfonsa Villalba", 1, t2, date, "Tiene conocimientos sobre los Kanji.", 15);
        Student s3 = new Student("Mariana Florez", 1, t1, date, "No tiene.", 25);

        ls1.add(s1);
        ls2.add(s2);
        ls2.add(s3);

        Interest i1 = new Interest("Trabajo", "pi pi-check");
        Interest i2 = new Interest("Cultura", "pi pi-check");
        Interest i3 = new Interest("Anime", "pi pi-check");
        Interest i4 = new Interest("Historia", "pi pi-check");
        Interest i5 = new Interest("Estudios", "pi pi-check");

        List<Interest> lsI1 = new ArrayList<Interest>();
        List<Interest> lsI2 = new ArrayList<Interest>();
        List<Interest> lsI3 = new ArrayList<Interest>();

        lsI1.add(i1);
        lsI1.add(i4);
        lsI1.add(i5);

        lsI2.add(i2);
        lsI2.add(i4);

        lsI3.add(i3);

        s1.setInterests(lsI1);
        s2.setInterests(lsI2);
        s3.setInterests(lsI3);

        // 寒い = Frio

        // String title, String description, Map<String, Boolean> multipleChoise, Student studentAsigned
        HashMap<String, Boolean> multipleChoise = new HashMap<String, Boolean>();
        multipleChoise.put("Frio", false);
        multipleChoise.put("Tenedor", false);
        multipleChoise.put("Guitarra", false);
        HomeWork h1 = new HomeWork("Indica cual es la opción correcta", "¿Cuál es el significado de 寒い?.", multipleChoise, s1);

        List<HomeWork> lsH1 = new ArrayList<HomeWork>();

        lsH1.add(h1);

        s3.setHomeWorks(lsH1);

        this.teacherRepository.save(t1);
        this.teacherRepository.save(t2);

        this.interestRespository.save(i1);
        this.interestRespository.save(i2);
        this.interestRespository.save(i3);
        this.interestRespository.save(i4);
        this.interestRespository.save(i5);

        this.studentRepository.save(s1);
        this.studentRepository.save(s2);
        this.studentRepository.save(s3);

        this.homeWorkRepository.save(h1);
    }

}