package com.backend.murasaki.exceptions;
import java.util.function.Supplier;

public class TeacherNotFoundExceptionSupplier implements  Supplier<TeacherNotFoundException> {

    @Override
    public TeacherNotFoundException get() {
        return new TeacherNotFoundException("The requested teacher was not found");
    }
}