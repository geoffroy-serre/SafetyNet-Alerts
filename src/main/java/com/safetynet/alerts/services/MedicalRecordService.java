package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IMedicalRecordDto;
import com.safetynet.alerts.model.MedicalRecordList;
@Component
public class MedicalRecordService {
  @Autowired
  private IMedicalRecordDto medicalRecordDto;
  
  public MedicalRecordList getMedicalRecordsList() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return medicalRecordDto.getMedicalRecordListDto();
  }

}
