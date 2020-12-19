package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.OutPutResponse;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutPutFireStationServiceImplTest {

  @Mock
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @InjectMocks
  OutPutFireStationServiceImpl outPutFireStationService;

  OutPutFireStation outPutFireStation = new OutPutFireStation();
  OutPutFireStation outPutFireStation2 = new OutPutFireStation();
  OutPutFireStation outPutFireStation3 = new OutPutFireStation();

  OutPutHome outPutHome = new OutPutHome();
  OutPutHome outPutHome2 = new OutPutHome();
  OutPutHome outPutHome3 = new OutPutHome();

OutPutResponse outPutResponse = new OutPutResponse();
  ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();

  @BeforeEach
  void setup(){
    outPutFireStation.setStationNumber(10);
    outPutFireStation.setIdFirestation(UUID.randomUUID());
    ArrayList<UUID> idhomes = new ArrayList<>();
    idhomes.add(UUID.randomUUID());
    outPutFireStation.setHomeListIds(idhomes);
    outPutHome.setStationNumber(10);
    outPutHome.setIdHome(idhomes.get(0));
    ArrayList<OutPutHome> addHome = new ArrayList<>();
    addHome.add(outPutHome);
    outPutFireStation.setHomes(addHome);
    outPutFireStations.add(outPutFireStation);

    outPutFireStation2.setStationNumber(15);
    outPutFireStation2.setIdFirestation(UUID.randomUUID());
    ArrayList<UUID> idhomes2 = new ArrayList<>();
    idhomes2.add(UUID.randomUUID());
    outPutFireStation2.setHomeListIds(idhomes2);
    outPutHome2.setStationNumber(15);
    outPutHome2.setIdHome(idhomes2.get(0));
    ArrayList<OutPutHome> addHome2 = new ArrayList<>();
    addHome2.add(outPutHome2);
    outPutFireStation2.setHomes(addHome2);
    outPutFireStations.add(outPutFireStation2);

    outPutFireStation3.setStationNumber(8);
    outPutFireStation3.setIdFirestation(UUID.randomUUID());
    ArrayList<UUID> idhomes3 = new ArrayList<>();
    idhomes3.add(UUID.randomUUID());
    outPutFireStation3.setHomeListIds(idhomes3);
    outPutHome3.setStationNumber(8);
    outPutHome3.setIdHome(idhomes3.get(0));
    ArrayList<OutPutHome> addHome3 = new ArrayList<>();
    addHome3.add(outPutHome3);
    outPutFireStation3.setHomes(addHome3);
    outPutFireStations.add(outPutFireStation3);

    outPutResponse.setFirestations(outPutFireStations);

  }

  @Test
  void isFireStationAlreadyInFile() {
    assertTrue(outPutFireStationService.isFireStationAlreadyInFile(8,"anyString()",
            outPutFireStations));
  }
  @Test
  void isNotFireStationAlreadyInFile() {
    assertFalse(outPutFireStationService.isFireStationAlreadyInFile(33,"anyString()",
            outPutFireStations));
  }

  @Test
  void getStationNumberByHomeId() {
    UUID currentId = outPutFireStation3.getHomeListIds().get(0);
    assertEquals(8,outPutFireStationService.getStationNumberByHomeId(currentId, outPutFireStations));
  }
  @Test
  void getStationNumberByHomeIdNotFound() {
    UUID currentId = UUID.randomUUID();
    assertEquals(0,outPutFireStationService.getStationNumberByHomeId(currentId,
            outPutFireStations));
  }

  @Test
  void setStationNumberHomesToNull() {
    outPutFireStationService.setStationNumberHomesToNull(outPutFireStations);
    for (OutPutFireStation currentFS : outPutFireStations   ) {
      for(OutPutHome currentHome : currentFS.getHomes()){
        assertTrue(currentHome.getStationNumber() == null);
      }
    }

  }

  @Test
  void getFiresStations() {
    when(retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE)).thenReturn(outPutResponse);
    assertFalse(outPutFireStationService.getFiresStations().isEmpty());
  }

  @Test
  void getFireStationByNumber() {

    assertEquals(8,outPutFireStationService.getFireStationByNumber(outPutFireStations,8).getStationNumber());
  }
  @Test
  void getFireStationByNumberNotFound() {

    assertEquals(0,
            outPutFireStationService.getFireStationByNumber(outPutFireStations,90).getStationNumber());
  }

  @Test
  void getFireStationByNumbers() {
    ArrayList<Integer>  nums = new ArrayList<>();
    nums.add(8);
    assertEquals(8,
            outPutFireStationService.getFireStationByNumbers(outPutFireStations,nums).get(0).getStationNumber());
  }
  @Test
  void getFireStationByNumbersNotfound() {
    ArrayList<Integer>  nums = new ArrayList<>();
    nums.add(8);
    nums.add(95);
    assertEquals(8,
            outPutFireStationService.getFireStationByNumbers(outPutFireStations,nums).get(0).getStationNumber());
    assertEquals(1,
            outPutFireStationService.getFireStationByNumbers(outPutFireStations,nums).size());
  }

  @Test
  void setHomes() {
    UUID num = UUID.randomUUID();
    ArrayList<OutPutHome> outPutHomestest = new ArrayList<>();
    outPutHome.setIdHome(num);
    outPutHomestest.add(outPutHome);
    assertEquals(num,
            outPutFireStationService.setHomes(outPutFireStations,outPutHomestest).get(0).getHomes().get(0).getIdHome());
    assertEquals(10,
            outPutFireStationService.setHomes(outPutFireStations,outPutHomestest).get(0).getHomes().get(0).getStationNumber());
  }

  @Test
  void setHome() {
    UUID num = UUID.randomUUID();
    ArrayList<OutPutHome> outPutHomestest = new ArrayList<>();
    outPutHome.setIdHome(num);
    outPutHomestest.add(outPutHome);
    assertEquals(num,
            outPutFireStationService.setHome(outPutFireStation,outPutHomestest).getHomes().get(0).getIdHome());
    assertEquals(10,
            outPutFireStationService.setHome(outPutFireStation,outPutHomestest).getHomes().get(0).getStationNumber());
  }
}