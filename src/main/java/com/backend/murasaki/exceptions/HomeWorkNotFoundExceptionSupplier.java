package com.backend.murasaki.exceptions;

import java.util.function.Supplier;

public class HomeWorkNotFoundExceptionSupplier implements Supplier<HomeWorkNotFoundException> {

    @Override
    public HomeWorkNotFoundException get(){
        return new HomeWorkNotFoundException("The requested homework was not found");
    }

}
