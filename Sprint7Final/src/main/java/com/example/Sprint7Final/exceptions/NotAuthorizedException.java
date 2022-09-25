package com.example.Sprint7Final.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException{
	
	private static final long serialVersionUID = 4090760080695095933L;
	
	private String message;
}
