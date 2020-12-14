package com.safetynet.alerts.Exceptions;

public class AllreadyInDatabaseException  extends RuntimeException{

  public AllreadyInDatabaseException(String property, String value) {
    super(String.format(
            "Resource with  "+property+" and +"+value+" already exists."));
  }
}
