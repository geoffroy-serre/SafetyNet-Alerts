package com.safetynet.alerts.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.services.PersonService;



@WebMvcTest(value =PersonController.class)
public class PersonControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private PersonService personService;
  
  Person person = new Person();
  MedicalRecord medicalRecord= new MedicalRecord();
  Home adress = new Home();
  ArrayList<String> medication = new ArrayList<String>();
  ArrayList<String> allergy = new ArrayList<String>();
  ArrayList<Person> listPerson = new ArrayList<Person>();
  
  @Test
  public void return_Persons_Info_with_Adress_Age_And_MedicalRecord() throws Exception {
    person.setFirstName("Toto");
    person.setLastName("Zozo");
    person.setBirthDate(LocalDate.of(1982, 04, 14));
    person.setId(UUID.randomUUID());
    person.setIdHome(1);
    person.setIdMedicalRecord(1);
    person.setAge(38);
    
    medication.add("Seresta");
    allergy.add("Peanut");
    medicalRecord.setAllergy(allergy);
    medicalRecord.setMedication(medication);
    adress.setCity("Toulouse");
    
    
    Mockito.when(personService.getPersonInfoService(Mockito.anyString(),
            Mockito.anyString())).thenReturn(listPerson);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
        "/personInfo?firstName=Toto&lastName=Zozo").accept(
        MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    System.out.println(result.getResponse());
    String expected = "{id:1,firstName:Toto}";


    JSONAssert.assertEquals(expected, result.getResponse()
        .getContentAsString(), false);    
    
  }

}
