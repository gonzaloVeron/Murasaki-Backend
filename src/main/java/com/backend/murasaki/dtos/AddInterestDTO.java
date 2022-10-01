package com.backend.murasaki.dtos;

public class AddInterestDTO {

    private int student_id;

    private int interest_id;

    public AddInterestDTO(){

    }

    public AddInterestDTO(int student_id, int interest_id){
        this.student_id = student_id;
        this.interest_id = interest_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(int interest_id) {
        this.interest_id = interest_id;
    }
}
