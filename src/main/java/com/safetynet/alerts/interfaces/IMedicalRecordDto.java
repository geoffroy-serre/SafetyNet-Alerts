package com.safetynet.alerts.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.MedicalRecordList;


public interface IMedicalRecordDto {
  
  public MedicalRecordList getMedicalRecordListDto() throws JsonParseException, JsonMappingException, IOException;

}
