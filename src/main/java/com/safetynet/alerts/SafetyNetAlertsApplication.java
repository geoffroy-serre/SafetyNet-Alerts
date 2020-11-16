package com.safetynet.alerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alerts.services.CreateWorkingFileService;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		CreateWorkingFileService cw = new CreateWorkingFileService();
		cw.createWorkingFileWithAssociatedProcessedData();
		
	}

}
