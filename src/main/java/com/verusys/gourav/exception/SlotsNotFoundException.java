package com.verusys.gourav.exception;

public class SlotsNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SlotsNotFoundException() {
	}
	
	public SlotsNotFoundException(String message) {
		super(message);
	}
}
