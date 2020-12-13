package com.safetynet.alerts.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestLogger {
  private static final Logger logger = LogManager.getLogger("App");
  public static void logRequest(final HttpServletRequest request) {

    logger.info(request.getMethod()  +" "+  request.getRequestURI() +" "+ request.getQueryString().replace("%20", " "));
  }

}
