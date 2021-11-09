package com.verusys.gourav.exception;

import java.lang.RuntimeException;
import java.lang.String;

public class PatientNotFoundException extends RuntimeException {
  public PatientNotFoundException() {
  }

  public PatientNotFoundException(String message) {
    super(message);
  }
}
