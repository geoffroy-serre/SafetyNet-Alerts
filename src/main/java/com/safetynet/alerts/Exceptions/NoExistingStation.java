package com.safetynet.alerts.Exceptions;

public class NoExistingStation extends RuntimeException {
  /**
   * Custom exception for no existing firestation.
   *
   * @param property Integer
   * @param value String
   */
  public NoExistingStation(Integer property, String value) {

    super(String.format(
            "Resource with  " + property + " and +" + value));
  }
}
