package com.safetynet.alerts.controller;


import java.util.ArrayList;

import com.safetynet.alerts.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MedicalRecordController {
  @Autowired
  MedicalRecordService medicalRecordService;

  @GetMapping("/medicalRecord")
  public ArrayList<?> getMedicalRecord() {
    return medicalRecordService.getMedicalRecordService();

  }
}
