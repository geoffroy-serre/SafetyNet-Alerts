package com.safetynet.alerts.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MedicalRecordController {
  @Autowired
  MedicalRecordService medicalRecordService;


}
