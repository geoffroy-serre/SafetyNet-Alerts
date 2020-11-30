package com.safetynet.alerts.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OriginalResponse{

	@JsonProperty("persons")
	private ArrayList<OriginalPersons> persons;

	@JsonProperty("firestations")
	private ArrayList<OriginalFirestation> firestations;

	@JsonProperty("medicalrecords")
	private ArrayList<OriginalMedicalrecords> medicalrecords;

	public void setPersons(ArrayList<OriginalPersons> persons){
		this.persons = persons;
	}

	public ArrayList<OriginalPersons> getPersons(){
		return persons;
	}

	public void setFirestations(ArrayList<OriginalFirestation> firestations){
		this.firestations = firestations;
	}

	public ArrayList<OriginalFirestation> getFirestations(){
		return firestations;
	}

	public void setMedicalrecords(ArrayList<OriginalMedicalrecords> medicalrecords){
		this.medicalrecords = medicalrecords;
	}

	public ArrayList<OriginalMedicalrecords> getMedicalrecords(){
		return medicalrecords;
	}

	@Override
 	public String toString(){
		return 
			"OriginalResponse{" + 
			"persons = '" + persons + '\'' + 
			",firestations = '" + firestations + '\'' + 
			",medicalrecords = '" + medicalrecords + '\'' + 
			"}";
		}
}