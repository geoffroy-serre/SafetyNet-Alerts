package com.safetynet.alerts.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class RequestLogger {

  private static final Logger logger = LogManager.getLogger("App");

  /**
   * logger info for Get request.
   *
   * @param request
   */
  public static void logRequest(final HttpServletRequest request) {

    logger.info(request.getMethod() + " " + request.getRequestURI() + " " + request.getQueryString().replace("%20", " "));
  }

  /**
   * Logger  info for post put delete request.
   *
   * @param request    HttpServletRequest
   * @param controller String
   */
  public static void logObjectRequest(final HttpServletRequest request, String controller
  ) {

    logger.info(request.getMethod() + " " + " " + controller + " " + request.getRequestURI() + " "
            + request.getParameter(request.toString()));


  }

}
