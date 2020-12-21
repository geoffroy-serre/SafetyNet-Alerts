package com.safetynet.alerts.model;

import java.util.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionResponseTest {
  @Test
  public void testSetTimestamp() {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(1L), 1, "An error " +
            "occurred",
            "An error occurred", "Path");
    exceptionResponse.setTimestamp(new Date(1L));
    assertEquals(
            "ExceptionResponse{timestamp=Thu Jan 01 01:00:00 CET 1970, status=1, error='An error " +
                    "occurred', message='An"
                    + " error occurred', path='Path'}",
            exceptionResponse.toString());
  }

  @Test
  public void testSetStatus() {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(1L), 1, "An error " +
            "occurred",
            "An error occurred", "Path");
    exceptionResponse.setStatus(1);
    assertEquals(1, exceptionResponse.getStatus());
  }

  @Test
  public void testSetError() {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(1L), 1, "An error " +
            "occurred",
            "An error occurred", "Path");
    exceptionResponse.setError("An error occurred");
    assertEquals("An error occurred", exceptionResponse.getError());
  }

  @Test
  public void testSetPath() {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(1L), 1, "An error " +
            "occurred",
            "An error occurred", "Path");
    exceptionResponse.setPath("Path");
    assertEquals("Path", exceptionResponse.getPath());
  }

  @Test
  public void testSetMessage() {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(1L), 1, "An error " +
            "occurred",
            "An error occurred", "Path");
    exceptionResponse.setMessage("An error occurred");
    assertEquals("An error occurred", exceptionResponse.getMessage());
  }


}

