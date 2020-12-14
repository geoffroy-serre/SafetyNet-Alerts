package com.safetynet.alerts.utils;

import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestLogger {
  private static final Logger logger = LogManager.getLogger("App");
  public static void logRequest(final HttpServletRequest request) {

    logger.info(request.getMethod()  +" "+  request.getRequestURI() +" "+ request.getQueryString().replace("%20", " "));
  }
  public static void logObjectRequest(final HttpServletRequest request, String controller
                                             ) {

      logger.info(request.getMethod()  +" "+ " "+ controller+" "+request.getRequestURI()+" "+request.getParameter(request.toString()) );


  }

}
