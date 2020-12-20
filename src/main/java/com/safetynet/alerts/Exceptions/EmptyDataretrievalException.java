package com.safetynet.alerts.Exceptions;

public class EmptyDataretrievalException extends RuntimeException{

  public EmptyDataretrievalException(String property, String value) {
    super(String.format(
            property + " : " + value));
  }

}
