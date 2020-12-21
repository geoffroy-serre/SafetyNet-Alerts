package com.safetynet.alerts.Exceptions;

public class NoStationNumberException extends RuntimeException {
  /**
   * Custom exception for no FireStation number.
   *
   * @param property Integer
   * @param value    String
   */
  public NoStationNumberException(Integer property, String value) {

    super(String.format(
            "Resource with  " + property + " and +" + value));
  }
}
