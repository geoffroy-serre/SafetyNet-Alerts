package com.safetynet.alerts.Exceptions;

public class NoDataInDataBaseException extends RuntimeException{




    public NoDataInDataBaseException(String property, String value) {
      super(String.format(
              "Resource with  "+property+" and " +value));
    }


}
