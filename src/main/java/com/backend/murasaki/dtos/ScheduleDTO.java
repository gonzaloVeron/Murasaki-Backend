package com.backend.murasaki.dtos;

import com.backend.murasaki.exceptions.NotFoundException;
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

    public ScheduleDTO merge(ScheduleDTO dto) {
        // Lunes
        for(int i = 0; i < dto.getLunes().size(); i++) {
            RealSchedule actualLunesRsche = dto.getLunes().get(i);
            boolean existInLunes = this.lunes.stream().anyMatch(rsche -> rsche.getTime().equals(actualLunesRsche.getTime()));
            if (existInLunes) {
                RealSchedule lrsche = this.lunes.stream().filter(r -> r.getTime().equals(actualLunesRsche.getTime())).findFirst().orElseThrow(() -> new NotFoundException("The requested Schedule was not found"));
                lrsche.getStudentNames().addAll(actualLunesRsche.getStudentNames());
            } else {
                this.lunes.add(actualLunesRsche);
            }
        }

        // Martes
        for(int i = 0; i < dto.getMartes().size(); i++) {
            RealSchedule actualMartesRsche = dto.getMartes().get(i);
            boolean existInMartes = this.martes.stream().anyMatch(rsche -> rsche.getTime().equals(actualMartesRsche.getTime()));
            if(existInMartes) {
                RealSchedule marsche = this.martes.stream().filter(r -> r.getTime().equals(actualMartesRsche.getTime())).findFirst().orElseThrow(() -> new NotFoundException("The requested Schedule was not found"));
                marsche.getStudentNames().addAll(actualMartesRsche.getStudentNames());
            }else {
                this.martes.add(actualMartesRsche);
            }
        }

        // Miercoles
        for(int i = 0; i < dto.getMiercoles().size(); i++) {
            RealSchedule actualMiercolesRsche = dto.getMiercoles().get(i);
            boolean existInMiercoles = this.miercoles.stream().anyMatch(rsche -> rsche.getTime().equals(actualMiercolesRsche.getTime()));
            if(existInMiercoles) {
                RealSchedule mirsche = this.miercoles.stream().filter(r -> r.getTime().equals(actualMiercolesRsche.getTime())).findFirst().orElseThrow(() -> new NotFoundException("The requested Schedule was not found"));
                mirsche.getStudentNames().addAll(actualMiercolesRsche.getStudentNames());
            }else{
                this.miercoles.add(actualMiercolesRsche);
            }
        }

        // Jueves
        for(int i = 0; i < dto.getJueves().size(); i++) {
            RealSchedule actualJuevesRsche = dto.getJueves().get(i);
            boolean existInJueves = this.jueves.stream().anyMatch(rsche -> rsche.getTime().equals(actualJuevesRsche.getTime()));
            if(existInJueves) {
                RealSchedule jrsche = this.jueves.stream().filter(r -> r.getTime().equals(actualJuevesRsche.getTime())).findFirst().orElseThrow(() -> new NotFoundException("The requested Schedule was not found"));
                jrsche.getStudentNames().addAll(actualJuevesRsche.getStudentNames());
            }else{
                this.jueves.add(actualJuevesRsche);
            }
        }

        // Viernes
        for(int i = 0; i < dto.getViernes().size(); i++) {
            RealSchedule actualViernesRsche = dto.getViernes().get(i);
            boolean existInViernes = this.viernes.stream().anyMatch(rsche -> rsche.getTime().equals(actualViernesRsche.getTime()));
            if(existInViernes) {
                RealSchedule vrsche = this.viernes.stream().filter(r -> r.getTime().equals(actualViernesRsche.getTime())).findFirst().orElseThrow(() -> new NotFoundException("The requested Schedule was not found"));
                vrsche.getStudentNames().addAll(actualViernesRsche.getStudentNames());
            }else{
                this.viernes.add(actualViernesRsche);
            }
        }

        // Sabado
        for(int i = 0; i < dto.getSabado().size(); i++) {
            RealSchedule actualSabadoRsche = dto.getSabado().get(i);
            boolean existInSabado = this.sabado.stream().anyMatch(rsche -> rsche.getTime().equals(actualSabadoRsche.getTime()));
            if(existInSabado) {
                RealSchedule srsche = this.sabado.stream().filter(r -> r.getTime().equals(actualSabadoRsche.getTime())).findFirst().orElseThrow(() -> new NotFoundException("The requested Schedule was not found"));
                srsche.getStudentNames().addAll(actualSabadoRsche.getStudentNames());
            }else{
                this.sabado.add(actualSabadoRsche);
            }
        }

        // Domingo
        for(int i = 0; i < dto.getDomingo().size(); i++) {
            RealSchedule actualDomingoRsche = dto.getDomingo().get(i);
            boolean existInDomingo = this.domingo.stream().anyMatch(rsche -> rsche.getTime().equals(actualDomingoRsche.getTime()));
            if(existInDomingo) {
                RealSchedule drsche = this.domingo.stream().filter(r -> r.getTime().equals(actualDomingoRsche.getTime())).findFirst().orElseThrow(() -> new NotFoundException("The requested Schedule was not found"));
                drsche.getStudentNames().addAll(actualDomingoRsche.getStudentNames());
            }else{
                this.domingo.add(actualDomingoRsche);
            }
        }

        return this;
    }

}
