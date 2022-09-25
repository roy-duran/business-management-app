package com.example.Sprint7Final.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2927650003708703242L;
	
	private String message;

}
