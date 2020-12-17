package com.safetynet.alerts.Exceptions;

public class NoStationNumberException extends RuntimeException {

  public NoStationNumberException(Integer property, String value) {

    super(String.format(
            "Resource with  " + property + " and +" + value));
  }
}
