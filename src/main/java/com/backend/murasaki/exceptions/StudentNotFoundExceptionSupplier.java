package com.backend.murasaki.exceptions;

import java.util.function.Supplier;

public class StudentNotFoundExceptionSupplier implements Supplier<StudentNotFoundException> {

    @Override
    public StudentNotFoundException get() {
        return new StudentNotFoundException("The requested student was not found");
    }

}
