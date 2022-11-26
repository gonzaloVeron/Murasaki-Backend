package com.backend.murasaki.dtos;

import java.util.List;

public class TranslateStudentDTO {

    private List<Integer> sourceIds;

    private List<Integer> targetIds;

    public TranslateStudentDTO(){

    }

    public TranslateStudentDTO(List<Integer> sourceIds, List<Integer> targetIds){
        this.sourceIds = sourceIds;
        this.targetIds = targetIds;
    }

    public List<Integer> getSourceIds() {
        return sourceIds;
    }

    public void setSourceIds(List<Integer> sourceIds) {
        this.sourceIds = sourceIds;
    }

    public List<Integer> getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(List<Integer> targetIds) {
        this.targetIds = targetIds;
    }

}
