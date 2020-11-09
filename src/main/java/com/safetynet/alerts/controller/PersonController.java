package com.safetynet.alerts.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.services.PersonService;

@RestController
public class PersonController {

  private static final Logger logger = LogManager.getLogger("App");
    @Autowired
    PersonService personService;
    
    /**
     * Get Person Info from personService.
     * @param firstName
     * @param lastName
     * @return ArrayList of Person
     */
    @GetMapping("/personInfo?firstName={firstName}&lastName={lastName}")
    public ArrayList<Person> getPersonInfo(@PathVariable String firstName, String lastName){
      try {
        return personService.getPersonInfoService(firstName, lastName);
      } catch (IOException e) {
        System.out.println("erro");
        e.printStackTrace();
        
      }
      return null;
    }
    
    @GetMapping("/personInfo")
    public Person getPersonInfo(){
      
        try {
          return personService.getpersons();
        } catch (JsonParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          logger.info("trucqui marche");
        } catch (JsonMappingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          logger.info("trucqui marche");
        } catch (NoSuchFileException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          logger.info("trucqui marche");
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          logger.info("trucqui marche");
        }
        
        
     
      
      
      return null;
    }
  
  
}
