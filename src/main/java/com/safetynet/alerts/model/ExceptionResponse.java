package com.safetynet.alerts.model;

import java.util.Date;

public class ExceptionResponse {
  private Date timestamp;
  private int status;
  private String error;
  private String message;
  private String path;

  public ExceptionResponse(Date timestamp, int status, String error, String message,
                           String path) {
    this.timestamp = timestamp;
    this.message  = message;
    this.status = status;
    this.error = error;
    this.path = path;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ExceptionResponse{" +
            "timestamp=" + timestamp +
            ", status=" + status +
            ", error='" + error + '\'' +
            ", message='" + message + '\'' +
            ", path='" + path + '\'' +
            '}';
  }
}
