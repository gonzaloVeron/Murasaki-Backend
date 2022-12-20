package com.backend.murasaki.models;

import com.backend.murasaki.dtos.RealSchedule;
import com.backend.murasaki.dtos.ScheduleDTO;
import com.backend.murasaki.dtos.TeacherDTO;
import com.backend.murasaki.exceptions.NotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "teachers")
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "teacherAssigned", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Student> students;

    public Teacher(){

    }

    public Teacher(int id, String name, List<Student> students){
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public Teacher(String name){
        this.name = name;
        this.students = new ArrayList<Student>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public TeacherDTO toDTO(){
        return new TeacherDTO(this.name);
    }

    public ScheduleDTO getScheduleDTO() {
        ScheduleDTO dto = new ScheduleDTO(
                new ArrayList<RealSchedule>(),
                new ArrayList<RealSchedule>(),
                new ArrayList<RealSchedule>(),
                new ArrayList<RealSchedule>(),
                new ArrayList<RealSchedule>(),
                new ArrayList<RealSchedule>(),
                new ArrayList<RealSchedule>()
        );
        for(int i = 0; i < this.students.size(); i++) {
            Student st = this.students.get(i);
            List<Schedule> stRealSchedules = st.getSchedules();
            for(int j = 0; j < stRealSchedules.size(); j++) {
                Schedule sche = stRealSchedules.get(j);
                List<RealSchedule> scheDay = dto.getByDay(sche.getDay());
                boolean existWithEqualTime = scheDay.stream().anyMatch(rsched -> rsched.getTime() == sche.getTime());
                if(existWithEqualTime){
                    RealSchedule rsche = scheDay.stream().filter(rsched -> rsched.getTime() == sche.getTime()).findFirst().orElseThrow(() -> new NotFoundException("The requested schedule was not found"));
                    rsche.getStudentNames().add(st.getName());
                }else{
                    List<String> studentNames = new ArrayList<String>();
                    studentNames.add(st.getName());
                    RealSchedule rsche = new RealSchedule(sche.getTime(), studentNames);
                    scheDay.add(rsche);
                }
            }
        }
        return dto;
    }

}
