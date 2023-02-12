package com.curiousfox.utils;

import java.util.regex.Pattern;

import com.curiousfox.exception.ValidationException;

public class Validation {
	public static boolean isNameValid(String name) throws ValidationException {
		if(name.isBlank()) {
			throw new ValidationException("Name can't be empty");
		}
		
		if(name.length() > 50) {
			throw new ValidationException("Name can't be longer than 50 characters");
		}
		
		return true;
	}

	public static boolean isUsernameValid(String username) throws ValidationException {
		if(username.isBlank()) {
			throw new ValidationException("Username can't be empty");
		}
		
		if(username.length() > 15) {
			throw new ValidationException("Username can't be longer than 15 characters");
		}
		
		if(!Pattern.matches("^[A-Za-z0-9_]{1,15}$", username)) {
			throw new ValidationException(" A username can only contain alphanumeric characters (letters A-Z, numbers 0-9) and underscores");
		}
		
		return true;
	}
	
	public static boolean isPasswordValid(String password, String confirmPassword) throws ValidationException {
		if(password.isBlank()) {
			throw new ValidationException("Password can't be empty");
		}
		
		if(password.length() > 50) {
			throw new ValidationException("Password can't be longer than 50 characters");
		}
		
		if(!password.equals(confirmPassword)) {
			throw new ValidationException("Passwords doesn't match");
		}
			
		return true;
	}
}
