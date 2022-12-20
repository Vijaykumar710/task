package com.curdoperation.exception;

public class StudentNotFoundException extends Exception {
	
		public StudentNotFoundException(String s) {
			super(s);
		}
		
		public StudentNotFoundException() {
			super("Resource Not Found");
		}
	}

