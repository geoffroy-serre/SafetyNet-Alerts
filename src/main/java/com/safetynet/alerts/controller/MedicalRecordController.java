package com.safetynet.alerts.controller;


import com.safetynet.alerts.services.MedicalRecordService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MedicalRecordController {
  @Autowired
  MedicalRecordService medicalRecordService;


}
