package com.safetynet.alerts.Exceptions;

public class NoExistingStation extends RuntimeException {

  public NoExistingStation(Integer property, String value) {

    super(String.format(
            "Resource with  " + property + " and +" + value));
  }
}
