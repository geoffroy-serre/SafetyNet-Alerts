package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.OutPutResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutPutHomeServiceImplTest {

  private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
  @Mock
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;
  @InjectMocks
  OutPutHomeServiceImpl outPutHomeService;
  OutPutHome outPutHome = new OutPutHome();
  OutPutHome outPutHome2 = new OutPutHome();
  OutPutHome outPutHome3 = new OutPutHome();
  OutPutFireStation outPutFireStation = new OutPutFireStation();
  OutPutPerson outPutPerson = new OutPutPerson();
  OutPutPerson outPutPerson2 = new OutPutPerson();
  OutPutPerson outPutPerson3 = new OutPutPerson();
  OutPutPerson outPutPerson4 = new OutPutPerson();
  ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
  ArrayList<OutPutPerson> outPutPersons = new ArrayList<>();
  ArrayList<OutPutPerson> outPutPersons2 = new ArrayList<>();
  ArrayList<OutPutPerson> outPutPersons3 = new ArrayList<>();

  @BeforeEach
  void setUp() {
    outPutHome.setStationNumber(10);
    outPutHome.setIdHome(UUID.randomUUID());
    outPutHome.setAddress("8 avenue foch");
    outPutHome.setCity("Aytré");
    outPutHome.setZip("17300");
    outPutPerson.setBirthdate(LocalDate.parse("08/23/1984", dtf));
    outPutPerson.setFirstName("Geff");
    outPutPerson.setLastName("mwa");
    outPutPerson.setIdHome(outPutHome.getIdHome());
    outPutPersons.add(outPutPerson);
    outPutHome.setPersons(outPutPersons);

    outPutHome2.setStationNumber(20);
    outPutHome2.setIdHome(UUID.randomUUID());
    outPutHome2.setAddress("35 avenue des boulots");
    outPutHome2.setCity("Toulouse");
    outPutHome2.setZip("31100");
    outPutPerson2.setBirthdate(LocalDate.parse("08/23/2012", dtf));
    outPutPerson2.setFirstName("Geff");
    outPutPerson2.setLastName("twa");
    outPutPerson2.setIdHome(outPutHome2.getIdHome());
    outPutPersons2.add(outPutPerson2);
    outPutHome2.setPersons(outPutPersons2);

    outPutHome3.setStationNumber(30);
    outPutHome3.setIdHome(UUID.randomUUID());
    outPutHome3.setAddress("15 rue des prés");
    outPutHome3.setCity("Pamiers");
    outPutHome3.setZip("31100");
    outPutPerson3.setBirthdate(LocalDate.parse("08/23/2000", dtf));
    outPutPerson4.setBirthdate(LocalDate.parse("08/23/2012", dtf));
    outPutPerson3.setFirstName("Geff");
    outPutPerson3.setLastName("lui");
    outPutPerson4.setFirstName("Geff");
    outPutPerson4.setLastName("toi");
    outPutPerson3.setIdHome(outPutHome3.getIdHome());
    outPutPerson4.setIdHome(outPutHome3.getIdHome());
    outPutPersons3.add(outPutPerson3);
    outPutPersons3.add(outPutPerson4);
    outPutHome3.setPersons(outPutPersons3);


    outPutHomes.add(outPutHome);
    outPutHomes.add(outPutHome2);
    outPutHomes.add(outPutHome3);
  }

  @Test
  void setStationNumberNull() {
    assertEquals(null,
            outPutHomeService.setStationNumberNull(outPutHomes).get(0).getStationNumber());
    assertEquals(null,
            outPutHomeService.setStationNumberNull(outPutHomes).get(1).getStationNumber());
    assertEquals(null,
            outPutHomeService.setStationNumberNull(outPutHomes).get(2).getStationNumber());
  }

  @Test
  void getCountChildrenAndAdultsforList() {
    ArrayList<OutPutHome> test = outPutHomeService.getCountChildrenAndAdultsforList(outPutHomes);

    assertEquals(1, test.get(0).getNumberOfAdults());
    assertEquals(0, test.get(0).getNumberOfChildren());

    assertEquals(0, test.get(1).getNumberOfAdults());
    assertEquals(1, test.get(1).getNumberOfChildren());

    assertEquals(1, test.get(2).getNumberOfAdults());
    assertEquals(1, test.get(2).getNumberOfChildren());

  }

  @Test
  void getHomeByStationNumber() {
    outPutFireStation.setStationNumber(30);
    ArrayList<UUID> uuids = new ArrayList<>();
    uuids.add(outPutHome3.getIdHome());
    outPutFireStation.setHomeListIds(uuids);
    assertEquals(30,
            outPutHomeService.getHomeByStationNumber(outPutHomes, outPutFireStation).get(0).getStationNumber());
  }

  @Test
  void setPersons() {

    assertEquals("Geff",
            outPutHomeService.setPersons(outPutPersons, outPutHomes).get(0).getPersons().get(0).getFirstName());
    assertEquals("mwa",
            outPutHomeService.setPersons(outPutPersons, outPutHomes).get(0).getPersons().get(0).getLastName());


    assertEquals("Geff",
            outPutHomeService.setPersons(outPutPersons2, outPutHomes).get(1).getPersons().get(0).getFirstName());
    assertEquals("twa",
            outPutHomeService.setPersons(outPutPersons2, outPutHomes).get(1).getPersons().get(0).getLastName());

    assertEquals("Geff",
            outPutHomeService.setPersons(outPutPersons3, outPutHomes).get(2).getPersons().get(0).getFirstName());
    assertEquals("lui",
            outPutHomeService.setPersons(outPutPersons3, outPutHomes).get(2).getPersons().get(0).getLastName());
    assertEquals("Geff",
            outPutHomeService.setPersons(outPutPersons3, outPutHomes).get(2).getPersons().get(1).getFirstName());
    assertEquals("toi",
            outPutHomeService.setPersons(outPutPersons3, outPutHomes).get(2).getPersons().get(1).getLastName());
  }

  @Test
  void setPersonsHome() {
    assertEquals("mwa",
            outPutHomeService.setPersonsHome(outPutPersons, outPutHome).getPersons().get(0).getLastName());
  }

  @Test
  void getHomesIds() {
    assertEquals(outPutHome.getIdHome(), outPutHomeService.getHomesIds(outPutHomes).get(0));
    assertEquals(outPutHome2.getIdHome(), outPutHomeService.getHomesIds(outPutHomes).get(1));
    assertEquals(outPutHome3.getIdHome(), outPutHomeService.getHomesIds(outPutHomes).get(2));
  }

  @Test
  void getHomesbyIds() {
    ArrayList<OutPutFireStation> outputs = new ArrayList<>();
    outPutFireStation.setStationNumber(30);
    ArrayList<UUID> uuids = new ArrayList<>();
    uuids.add(outPutHome3.getIdHome());
    outPutFireStation.setHomeListIds(uuids);
    outputs.add(outPutFireStation);
    assertEquals(30,
            outPutHomeService.getHomesbyIds(outputs, outPutHomes).get(0).getStationNumber());
  }

  @Test
  void getHomeByAddress() {
    OutPutResponse outPutResponse = new OutPutResponse();
    outPutResponse.setHomes(outPutHomes);
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertEquals("15 rue des prés",
            outPutHomeService.getHomeByAddress("15 rue des prés").getAddress());
  }

  @Test
  void getHomesByCity() {
    OutPutResponse outPutResponse = new OutPutResponse();
    outPutResponse.setHomes(outPutHomes);
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertTrue(outPutHomeService.getHomesByCity("Aytré").contains(outPutHome.getIdHome()));
    assertTrue(outPutHomeService.getHomesByCity("Toulouse").contains(outPutHome2.getIdHome()));
    assertTrue(outPutHomeService.getHomesByCity("Pamiers").contains(outPutHome3.getIdHome()));


  }

  @Test
  void getOutPutHomeList() {
    OutPutResponse outPutResponse = new OutPutResponse();
    outPutResponse.setHomes(outPutHomes);
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertEquals("15 rue des prés",
            outPutHomeService.getHomeByAddress("15 rue des prés").getAddress());
  }

}