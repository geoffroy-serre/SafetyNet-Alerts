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

  /**
   * Exception Handler for TypeMismatchException.
   *
   * @param typeMismatchException Exception
   * @param request HttpServletRequest
   * @param responseCode HttpServletResponse
   * @return
   */
  @ExceptionHandler(TypeMismatchException.class)
  @ResponseBody
  public ExceptionResponse handleTypeMismatchException(TypeMismatchException typeMismatchException,
                                                       HttpServletRequest request,
                                                       HttpServletResponse responseCode) {
    responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Bad parameter(s) type",
            typeMismatchException.getMessage(), request.getRequestURI()
    );
    logger.info(request.getMethod() + " " + request.getRequestURI() + " " + request.getQueryString());
    logger.error("ERROR: " + response.toString());

    return response;
  }

  /**
   * Exception Handler for MissingServletRequestParameterException.
   *
   * @param exception Exception
   * @param request HttpServletRequest
   * @param responseCode HttpServletResponse
   * @return
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  public ExceptionResponse handleMissingParameterException(MissingServletRequestParameterException exception,
                                                           HttpServletRequest request,
                                                           HttpServletResponse responseCode) {
    responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Bad parameter(s) type",
            exception.getMessage(), request.getRequestURI()
    );
    logger.info(request.getMethod() + " " + request.getRequestURI() + " " + request.getQueryString());
    logger.error("ERROR: " + response.toString());

    return response;
  }

  /**
   * Exception Handler for HttpMediaTypeNotSupportedException.
   *
   * @param exception Exception
   * @param request HttpServletRequest
   * @param responseCode HttpServletResponse
   * @return
   */
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

  /**
   * Exception handler for HttpMessageNotReadableException.
   *
   * @param exception Exception
   * @param request HttpServletRequest
   * @param responseCode HttpServletResponse
   * @return
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseBody
  public ExceptionResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException exception,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse responseCode) {
    responseCode.setStatus(409);
    ExceptionResponse response = new ExceptionResponse(new Date(), 409, "Bad separator(s) type",
            exception.getMessage(), request.getRequestURI()
    );
    logger.info(request.getMethod() + " " + request.getRequestURI() + " " + request.getQueryString());
    logger.error("ERROR: " + response.toString());

    return response;
  }

  /**
   * Exception handler for MethodArgumentNotValidException.
   *
   * @param exception Exception
   * @param request HttpServletRequest
   * @param responsecode HttpServletResponse
   * @return
   */
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

  /**
   * Exception handler for AllreadyInDatabaseException.
   *
   * @param exception Exception
   * @param request HttpServletRequest
   * @param responseCode HttpServletResponse
   * @return
   */
  @ExceptionHandler(AllreadyInDatabaseException.class)
  @ResponseBody
  public ExceptionResponse allreadyInDatabaseException(AllreadyInDatabaseException exception,
                                                       HttpServletRequest request,
                                                       HttpServletResponse responseCode) {
    responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Data already stored with" +
            " this content ",
            exception.getMessage(), request.getRequestURI()
    );
    logger.error("ERROR: " + response.toString());

    return response;
  }

  /**
   * Exception handler for NoExistingStation.
   *
   * @param exception Exception
   * @param request HttpServletRequest
   * @param responseCode HttpServletResponse
   * @return
   */
  @ExceptionHandler(NoExistingStation.class)
  @ResponseBody
  public ExceptionResponse noExistingStation(NoExistingStation exception,
                                             HttpServletRequest request,
                                             HttpServletResponse responseCode) {
    responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Station unknown",
            exception.getMessage(), request.getRequestURI()
    );
    logger.error("ERROR: " + response.toString());

    return response;
  }

  /**
   * Exception handler for NoStationNumberException.
   *
   * @param exception Exception
   * @param request HttpServletRequest
   * @param responseCode HttpServletResponse
   * @return
   */
  @ExceptionHandler(NoStationNumberException.class)
  @ResponseBody
  public ExceptionResponse noStationNumberException(NoStationNumberException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse responseCode) {

    responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "Data already stored with" +
            " this content ",
            exception.getMessage(), request.getRequestURI()
    );
    logger.error("ERROR: " + response.toString());

    return response;
  }

  /**
   * Exception handler for NoDataInDataBaseException.
   *
   * @param exception Exception
   * @param request HttpServletRequest
   * @param responseCode HttpServletResponse
   * @return
   */
  @ExceptionHandler(NoDataInDataBaseException.class)
  @ResponseBody
  public ExceptionResponse noDataInDataBaseException(NoDataInDataBaseException exception,
                                                     HttpServletRequest request,
                                                     HttpServletResponse responseCode) {
    responseCode.setStatus(400);
    ExceptionResponse response = new ExceptionResponse(new Date(), 400, "No Data stored with" +
            " this content ",
            exception.getMessage(), request.getRequestURI()
    );
    logger.error("ERROR: " + response.toString());

    return response;
  }

  /**
   * Exception handler for NoDataInDataBaseException.
   *
   * @param exception Exception
   * @param request HttpServletRequest
   * @param responseCode HttpServletResponse
   * @return
   */
  @ExceptionHandler(EmptyDataretrievalException.class)
  @ResponseBody
  public ExceptionResponse emptyDataretrievalException(EmptyDataretrievalException exception,
                                                     HttpServletRequest request,
                                                     HttpServletResponse responseCode) {
    responseCode.setStatus(500);
    ExceptionResponse response = new ExceptionResponse(new Date(), 500, "Error retrieving data " +
            "for "+
            " this content ",
            exception.getMessage(), request.getRequestURI()
    );
    logger.error("ERROR: " + response.toString());

    return response;
  }


}



