package com.example.Sprint7Final.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = -8224071650964569461L;
	
	private String message;
}
