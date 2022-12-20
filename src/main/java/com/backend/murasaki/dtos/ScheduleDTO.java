package com.backend.murasaki.dtos;

import com.backend.murasaki.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDTO {

    private List<RealSchedule> lunes;

    private List<RealSchedule> martes;

    private List<RealSchedule> miercoles;

    private List<RealSchedule> jueves;

    private List<RealSchedule> viernes;

    private List<RealSchedule> sabado;

    private List<RealSchedule> domingo;

    public ScheduleDTO(){

    }

    public ScheduleDTO(List<RealSchedule> lunes, List<RealSchedule> martes, List<RealSchedule> miercoles, List<RealSchedule> jueves, List<RealSchedule> viernes, List<RealSchedule> sabado, List<RealSchedule> domingo){
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
    }

    public List<RealSchedule> getLunes() {
        return lunes;
    }

    public void setLunes(List<RealSchedule> lunes) {
        this.lunes = lunes;
    }

    public List<RealSchedule> getMartes() {
        return martes;
    }

    public void setMartes(List<RealSchedule> martes) {
        this.martes = martes;
    }

    public List<RealSchedule> getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(List<RealSchedule> miercoles) {
        this.miercoles = miercoles;
    }

    public List<RealSchedule> getJueves() {
        return jueves;
    }

    public void setJueves(List<RealSchedule> jueves) {
        this.jueves = jueves;
    }

    public List<RealSchedule> getViernes() {
        return viernes;
    }

    public void setViernes(List<RealSchedule> viernes) {
        this.viernes = viernes;
    }

    public List<RealSchedule> getSabado() {
        return sabado;
    }

    public void setSabado(List<RealSchedule> sabado) {
        this.sabado = sabado;
    }

    public List<RealSchedule> getDomingo() {
        return domingo;
    }

    public void setDomingo(List<RealSchedule> domingo) {
        this.domingo = domingo;
    }

    public List<RealSchedule> getByDay(String day){
        switch (day) {
            case "Lunes":
                return this.lunes;
            case "Martes":
                return this.martes;
            case "Miercoles":
                return this.miercoles;
            case "Jueves":
                return this.jueves;
            case "Viernes":
                return this.viernes;
            case "Sabado":
                return this.sabado;
            case "Domingo":
                return this.domingo;
            default:
                return new ArrayList<RealSchedule>();
        }
    }

}
