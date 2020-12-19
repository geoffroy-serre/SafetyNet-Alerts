package com.safetynet.alerts.Exceptions;

import com.safetynet.alerts.model.ExceptionResponse;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandling {
  private static final Logger logger = LogManager.getLogger("SafetyNetAlerts");

  @ExceptionHandler(TypeMismatchException.class)
  @ResponseBody
  public ExceptionResponse handleTypeMismatchException(TypeMismatchException typeMismatchException,
                                                       HttpServletRequest request, HttpServletResponse responseCode) {
responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Bad parameter(s) type",
            typeMismatchException.getMessage(), request.getRequestURI()
    );
    logger.info(request.getMethod() + " " + request.getRequestURI() + " " + request.getQueryString());
    logger.error("ERROR: " + response.toString());

    return response;
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  public ExceptionResponse handleMissingParameterException(MissingServletRequestParameterException exception,
                                                           HttpServletRequest request, HttpServletResponse responseCode) {
responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Bad parameter(s) type",
            exception.getMessage(), request.getRequestURI()
    );
    logger.info(request.getMethod() + " " + request.getRequestURI() + " " + request.getQueryString());
    logger.error("ERROR: " + response.toString());

    return response;
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseBody
  public ExceptionResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception,
                                                                    HttpServletRequest request,
                                                                    HttpServletResponse responseCode) {

    responseCode.setStatus(204);
    ExceptionResponse response = new ExceptionResponse(new Date(), 204, "Bad input type",
            exception.getMessage(), request.getRequestURI()
    );
    logger.info(request.getMethod() + " " + request.getRequestURI() + " " + request.getQueryString());
    logger.error("ERROR: " + response.toString());

    return response;
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseBody
  public ExceptionResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException exception,
                                                                 HttpServletRequest request, HttpServletResponse responseCode) {
responseCode.setStatus(409);
    ExceptionResponse response = new ExceptionResponse(new Date(), 409, "Bad separator(s) type",
            exception.getMessage(), request.getRequestURI()
    );
    logger.info(request.getMethod() + " " + request.getRequestURI() + " " + request.getQueryString());
    logger.error("ERROR: " + response.toString());

    return response;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ExceptionResponse methodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                           HttpServletRequest request,
                                                           HttpServletResponse responsecode) {

    responsecode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Missing or blank " +
            "parameter in request",
            exception.getMessage(), request.getRequestURI()
    );

    logger.info(request.getMethod() + " " + request.getRequestURI() + " " + request.getQueryString());
    logger.error("ERROR: " + response.toString());

    return response;
  }

  @ExceptionHandler(AllreadyInDatabaseException.class)
  @ResponseBody
  public ExceptionResponse allreadyInDatabaseException(AllreadyInDatabaseException exception,
                                                       HttpServletRequest request, HttpServletResponse responseCode) {
responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Data already stored with" +
            " this content ",
            exception.getMessage(), request.getRequestURI()
    );
    logger.error("ERROR: " + response.toString());

    return response;
  }

  @ExceptionHandler(NoExistingStation.class)
  @ResponseBody
  public ExceptionResponse NoExistingStation(NoExistingStation exception,
                                             HttpServletRequest request, HttpServletResponse responseCode) {
responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Station unknown",
            exception.getMessage(), request.getRequestURI()
    );
    logger.error("ERROR: " + response.toString());

    return response;
  }

  @ExceptionHandler(NoStationNumberException.class)
  @ResponseBody
  public ExceptionResponse NoStationNumberException(NoStationNumberException exception,
                                                    HttpServletRequest request, HttpServletResponse responseCode) {

    responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Data already stored with" +
            " this content ",
            exception.getMessage(), request.getRequestURI()
    );
    logger.error("ERROR: " + response.toString());

    return response;
  }

  @ExceptionHandler(NoDataInDataBaseException.class)
  @ResponseBody
  public ExceptionResponse NoDataInDataBaseException(NoDataInDataBaseException exception,
                                                     HttpServletRequest request,HttpServletResponse responseCode) {
responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "No Data stored with" +
            " this content ",
            exception.getMessage(), request.getRequestURI()
    );
    logger.error("ERROR: " + response.toString());

    return response;
  }


}



