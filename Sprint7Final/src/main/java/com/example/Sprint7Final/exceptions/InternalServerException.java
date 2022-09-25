package com.example.Sprint7Final.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InternalServerException extends RuntimeException {

    private static final long serialVersionUID = -5552076667106114422L;

    private String message;

}

