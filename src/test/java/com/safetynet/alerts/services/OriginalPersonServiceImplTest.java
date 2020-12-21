package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OriginalPersonServiceImplTest {

  @Mock
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @InjectMocks
  OriginalPersonServiceImpl originalPersonService;

  private OriginalPerson originalPerson = new OriginalPerson();
  private OriginalPerson originalPerson2 = new OriginalPerson();
  private OriginalPerson originalPerson3 = new OriginalPerson();
  private OriginalPerson originalPerson4 = new OriginalPerson();
  private ArrayList<OriginalPerson> originalPersons = new ArrayList<>();
  private OriginalResponse originalResponse = new OriginalResponse();

  @BeforeEach
  void setup() {
    originalPerson.setFirstName("Geff");
    originalPerson.setLastName("mwa");
    originalPerson.setAddress("15 rue de paris");
    originalPerson.setCity("Orange");
    originalPerson.setZip("J6T5A6");
    originalPerson.setEmail("toto@toto.fr");
    originalPerson.setPhone("0619674945");
    originalPersons.add(originalPerson);

    originalPerson2.setFirstName("Hubert");
    originalPerson2.setLastName("trotro");
    originalPerson2.setAddress("1bis rue des eperviers");
    originalPerson2.setCity("Paris");
    originalPerson2.setZip("75000");
    originalPerson2.setEmail("wesg@toto.fr");
    originalPerson2.setPhone("0619688945");
    originalPersons.add(originalPerson2);

    originalPerson3.setFirstName("Sarah");
    originalPerson3.setLastName("twa");
    originalPerson3.setAddress("55 impasse roudoudou");
    originalPerson3.setCity("Paris");
    originalPerson3.setZip("75000");
    originalPerson3.setEmail("ratata@toto.fr");
    originalPerson3.setPhone("0698432518");
    originalPersons.add(originalPerson3);

    originalPerson4.setFirstName("Geff");
    originalPerson4.setLastName("Serre");
    originalPerson4.setAddress("15 boulevard paris");
    originalPerson4.setCity("Orange");
    originalPerson4.setZip("J6T5A6");
    originalPerson4.setEmail("hop@toto.fr");
    originalPerson4.setPhone("0698351752");

    originalResponse.setPersons(originalPersons);
  }

  @Test
  void postNewPerson() {
    String result = "[Person{,firstName = 'Geff',lastName = 'mwa',address = '15 rue de " +
            "paris',zip = 'J6T5A6',city = 'Orange',phone = '0619674945',email = 'toto@toto.fr'}, " +
            "Person{,firstName = 'Hubert',lastName = 'trotro',address = '1bis rue des eperviers'," +
            "zip = '75000',city = 'Paris',phone = '0619688945',email = 'wesg@toto.fr'}, Person{," +
            "firstName = 'Sarah',lastName = 'twa',address = '55 impasse roudoudou',zip = '75000'," +
            "city = 'Paris',phone = '0698432518',email = 'ratata@toto.fr'}, Person{,firstName = " +
            "'Geff',lastName = 'Serre',address = '15 boulevard paris',zip = 'J6T5A6',city = " +
            "'Orange',phone = '0698351752',email = 'hop@toto.fr'}]";
    assertEquals(result,
            originalPersonService.postNewPerson(originalPerson4, originalPersons).toString());


  }

  @Test
  void postNewPersonExisting() {
    String result = "[Person{,firstName = 'Geff',lastName = 'mwa',address = '15 rue de " +
            "paris',zip = 'J6T5A6',city = 'Orange',phone = '0619674945',email = 'toto@toto.fr'}, " +
            "Person{,firstName = 'Hubert',lastName = 'trotro',address = '1bis rue des eperviers'," +
            "zip = '75000',city = 'Paris',phone = '0619688945',email = 'wesg@toto.fr'}, Person{," +
            "firstName = 'Sarah',lastName = 'twa',address = '55 impasse roudoudou',zip = '75000'," +
            "city = 'Paris',phone = '0698432518',email = 'ratata@toto.fr'}]";
    assertEquals(result,
            originalPersonService.postNewPerson(originalPerson, originalPersons).toString());


  }

  @Test
  void getOriginalPersons() {
    String result = "[Person{,firstName = 'Geff',lastName = 'mwa',address = '15 rue de " +
            "paris',zip = 'J6T5A6',city = 'Orange',phone = '0619674945',email = 'toto@toto.fr'}, " +
            "Person{,firstName = 'Hubert',lastName = 'trotro',address = '1bis rue des eperviers'," +
            "zip = '75000',city = 'Paris',phone = '0619688945',email = 'wesg@toto.fr'}, Person{," +
            "firstName = 'Sarah',lastName = 'twa',address = '55 impasse roudoudou',zip = '75000'," +
            "city = 'Paris',phone = '0698432518',email = 'ratata@toto.fr'}]";
    assertEquals(result, originalPersonService.getOriginalPersons(originalResponse).toString());

  }

  @Test
  void getOriginalPersonHashMap() {
    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertFalse(originalPersonService.getOriginalPersonHashMap().isEmpty());
  }

  @Test
  void getOriginalPersonByFirstAndLastName() {
    String result = "Person{,firstName = 'Geff',lastName = 'mwa',address = '15 rue de paris',zip " +
            "= 'J6T5A6',city = 'Orange',phone = '0619674945',email = 'toto@toto.fr'}";
    assertEquals(result, originalPersonService.getOriginalPersonByFirstAndLastName("geff", "mwa",
            originalPersons).toString());
  }

  @Test
  void getOriginalPersonByFirstAndLastNameNotFound() {
    String result = "Person{,firstName = 'null',lastName = 'null',address = 'null',zip = 'null'," +
            "city = 'null',phone = 'null',email = 'null'}";
    assertEquals(result, originalPersonService.getOriginalPersonByFirstAndLastName("Hector",
            "dupré",
            originalPersons).toString());
  }

  @Test
  void deleteOriginalPerson() {
    String result = "[Person{,firstName = 'Hubert',lastName = 'trotro',address = '1bis " +
            "rue des eperviers',zip = '75000',city = 'Paris',phone = '0619688945',email = " +
            "'wesg@toto.fr'}, Person{,firstName = 'Sarah',lastName = 'twa',address = '55 impasse " +
            "roudoudou',zip = '75000',city = 'Paris',phone = '0698432518',email = 'ratata@toto" +
            ".fr'}]";
    assertEquals(result, originalPersonService.deleteOriginalPerson(originalPerson,
            originalPersons).toString());

  }

  @Test
  void deleteOriginalPersonNotFound() {
    String result = "[Person{,firstName = 'Geff',lastName = 'mwa',address = '15 rue de " +
            "paris',zip = 'J6T5A6',city = 'Orange',phone = '0619674945',email = 'toto@toto.fr'}, " +
            "Person{,firstName = 'Hubert',lastName = 'trotro',address = '1bis rue des eperviers'," +
            "zip = '75000',city = 'Paris',phone = '0619688945',email = 'wesg@toto.fr'}, Person{," +
            "firstName = 'Sarah',lastName = 'twa',address = '55 impasse roudoudou',zip = '75000'," +
            "city = 'Paris',phone = '0698432518',email = 'ratata@toto.fr'}]";
    assertEquals(result, originalPersonService.deleteOriginalPerson(originalPerson4,
            originalPersons).toString());

  }

  @Test
  void deletePersonbyFirstAndLastNames() {
    String result = "[Person{,firstName = 'Hubert',lastName = 'trotro',address = '1bis " +
            "rue des eperviers',zip = '75000',city = 'Paris',phone = '0619688945',email = " +
            "'wesg@toto.fr'}, Person{,firstName = 'Sarah',lastName = 'twa',address = '55 impasse " +
            "roudoudou',zip = '75000',city = 'Paris',phone = '0698432518',email = 'ratata@toto" +
            ".fr'}]";
    assertEquals(result, originalPersonService.deletePersonbyFirstAndLastNames("geff", "mwa",
            originalPersons).toString());

  }

  @Test
  void deletePersonbyFirstAndLastNamesNotFound() {
    String result = "[Person{,firstName = 'Geff',lastName = 'mwa',address = '15 rue de " +
            "paris',zip = 'J6T5A6',city = 'Orange',phone = '0619674945',email = 'toto@toto.fr'}, " +
            "Person{,firstName = 'Hubert',lastName = 'trotro',address = '1bis rue des eperviers'," +
            "zip = '75000',city = 'Paris',phone = '0619688945',email = 'wesg@toto.fr'}, Person{," +
            "firstName = 'Sarah',lastName = 'twa',address = '55 impasse roudoudou',zip = '75000'," +
            "city = 'Paris',phone = '0698432518',email = 'ratata@toto.fr'}]";
    assertEquals(result, originalPersonService.deletePersonbyFirstAndLastNames("geff", "serre",
            originalPersons).toString());

  }
}