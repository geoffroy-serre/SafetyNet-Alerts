package com.safetynet.alerts.Exceptions;

import com.safetynet.alerts.model.ExceptionResponse;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandling {
  private static final Logger logger = LogManager.getLogger("App");

  @ExceptionHandler(TypeMismatchException.class)
  @ResponseBody
  public ExceptionResponse handleTypeMismatchException(TypeMismatchException typeMismatchException,
                                                       HttpServletRequest request) {

    ExceptionResponse response = new ExceptionResponse(new Date(),400, "Bad parameter(s) type",
            typeMismatchException.getMessage(),request.getRequestURI()
            );
    logger.info(request.getMethod()  +" "+  request.getRequestURI() +" "+ request.getQueryString());
    logger.error("ERROR: "+response.toString());

    return response;
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  public ExceptionResponse handleTypeMismatchException(MissingServletRequestParameterException exception,
                                                       HttpServletRequest request) {

    ExceptionResponse response = new ExceptionResponse(new Date(),200, "Bad parameter(s) type",
            exception.getMessage(),request.getRequestURI()
    );
    logger.info(request.getMethod()  +" "+  request.getRequestURI() +" "+ request.getQueryString());
    logger.error("ERROR: "+response.toString());

    return response;
  }


}
