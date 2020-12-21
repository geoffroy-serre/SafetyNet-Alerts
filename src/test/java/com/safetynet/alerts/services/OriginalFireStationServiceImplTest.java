package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OriginalFireStationServiceImplTest {

  @Mock
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;
  @InjectMocks
  OriginalFireStationServiceImpl originalFireStationService;
  private OriginalFirestation originalFirestation = new OriginalFirestation();
  private OriginalFirestation originalFirestation2 = new OriginalFirestation();
  private OriginalFirestation originalFirestation3 = new OriginalFirestation();
  private ArrayList<OriginalFirestation> originalFirestations = new ArrayList<>();
  private OriginalResponse originalResponse = new OriginalResponse();

  @BeforeEach
  void setup() {
    originalFirestation.setStation(15);
    originalFirestation.setAddress("15 rue des gens");
    originalFirestation2.setStation(15);
    originalFirestation2.setAddress("12 avenue de paris");
    originalFirestation3.setStation(14);
    originalFirestation3.setAddress("11 impasse des codeurs");
    originalFirestations.add(originalFirestation);
    originalFirestations.add(originalFirestation2);
    originalFirestations.add(originalFirestation3);
    originalResponse.setFirestations(originalFirestations);


  }

  @Test
  void deleteOriginalFireStation() {
    String result = "[FirestationsItem{address = '12 avenue de paris',station = '15'}, " +
            "FirestationsItem{address = '11 impasse des codeurs',station = '14'}]";
    assertNotNull(originalFireStationService.deleteOriginalFireStation(originalFirestation,
            originalFirestations));
    assertEquals(result, originalFireStationService.deleteOriginalFireStation(originalFirestation,
            originalFirestations).toString());
  }

  @Test
  void isFireStationAlreadyInFile() {
    assertTrue(originalFireStationService.isFireStationAlreadyInFile(15, "12 avenue de " +
                    "paris",
            originalFirestations));
  }

  @Test
  void testIsFireStationAlreadyInFile() {
    assertTrue(originalFireStationService.isFireStationAlreadyInFile(15,
            originalFirestations));
  }

  @Test
  void testIsFireStationAlreadyInFile1() {
    assertTrue(originalFireStationService.isFireStationAlreadyInFile("12 avenue de " +
                    "paris",
            originalFirestations));
  }

  @Test
  void testIsFireStationAlreadyInFileFalse2() {
    assertFalse(originalFireStationService.isFireStationAlreadyInFile(55,
            originalFirestations));
  }

  @Test
  void testIsFireStationAlreadyInFileFalse3() {
    assertFalse(originalFireStationService.isFireStationAlreadyInFile("15 avenue de " +
                    "toulouse",
            originalFirestations));
  }

  @Test
  void testIsFireStationAlreadyInFileFalse4() {
    assertFalse(originalFireStationService.isFireStationAlreadyInFile(45, "15 avenue de " +
                    "toulouse",
            originalFirestations));
  }


  @Test
  void isAdressLinked() {
    assertTrue(originalFireStationService.isAdressLinked("12 avenue de Paris",
            originalFirestations));
  }

  @Test
  void isAdressLinkedFalse() {
    assertFalse(originalFireStationService.isAdressLinked("12 rue de Paris",
            originalFirestations));
  }

  @Test
  void checkIfAdressExist() {
    assertEquals(originalFirestation2,
            originalFireStationService.checkIfAdressExist(originalFirestations, "12 " +
                    "avenue de Paris"));
  }

  @Test
  void checkIfAdressExistUnknown() {
    OriginalFirestation test = new OriginalFirestation();
    assertEquals(test.toString(),
            originalFireStationService.checkIfAdressExist(originalFirestations, "55 " +
                    "avenue de Bordeaux").toString());
  }

  @Test
  void getOriginalFireStations() {
    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertNotNull(originalFireStationService.getOriginalFireStations());
    assertFalse(originalFireStationService.getOriginalFireStations().isEmpty());

  }

  @Test
  void getFireStationByNumberAndAddress() {
    assertNotNull(originalFireStationService.getFireStationByNumberAndAddress(originalFirestations,
            15, "12 avenue de paris").getAddress());
    assertNotNull(originalFireStationService.getFireStationByNumberAndAddress(originalFirestations,
            15, "12 avenue de paris").getStation());
  }

  @Test
  void getFireStationByNumberAndAddressNoFound() {
    assertNull(originalFireStationService.getFireStationByNumberAndAddress(originalFirestations,
            15, "12 rue de paris").getStation());
    assertNull(originalFireStationService.getFireStationByNumberAndAddress(originalFirestations,
            15, "12 rue de paris").getAddress());
  }

  @Test
  void getFireStationByAddress() {
    assertNotNull(originalFireStationService.getFireStationByAddress(originalFirestations,
            "12 avenue de paris").getAddress());
    assertNotNull(originalFireStationService.getFireStationByAddress(originalFirestations,
            "12 avenue de paris").getStation());
  }

  @Test
  void getFireStationByAddressNotFound() {
    assertNull(originalFireStationService.getFireStationByAddress(originalFirestations,
            "12 rue de paris").getStation());
    assertNull(originalFireStationService.getFireStationByAddress(originalFirestations,
            "12 rue de paris").getAddress());
  }

  @Test
  void getFireStationByNumber() {
    assertNotNull(originalFireStationService.getFireStationByNumber(originalFirestations,
            15).getAddress());
    assertNotNull(originalFireStationService.getFireStationByNumber(originalFirestations,
            15).getStation());
  }

  @Test
  void getFireStationsWithoutThisStation() {
    String result = "[FirestationsItem{address = '11 impasse des codeurs',station = '14'}]";
    assertEquals(result,
            originalFireStationService.getFireStationsWithoutThisStation(originalFirestations,
                    15).toString());
  }

  @Test
  void postNewFireStation() {
    OriginalFirestation originalFirestation4 = new OriginalFirestation();
    originalFirestation4.setStation(16);
    originalFirestation4.setAddress("15 boulevard de java");
    String result = "[FirestationsItem{address = '15 rue des gens',station = '15'}, " +
            "FirestationsItem{address = '12 avenue de paris',station = '15'}, " +
            "FirestationsItem{address = '11 impasse des codeurs',station = '14'}, " +
            "FirestationsItem{address = '15 boulevard de java',station = '16'}]";

    assertEquals(result, originalFireStationService.postNewFireStation(originalFirestation4,
            originalFirestations).toString());

  }

  @Test
  void postNewFireStationAlreadyIn() {

    String result = "[FirestationsItem{address = '15 rue des gens',station = '15'}, " +
            "FirestationsItem{address = '12 avenue de paris',station = '15'}, " +
            "FirestationsItem{address = '11 impasse des codeurs',station = '14'}]";

    assertEquals(result, originalFireStationService.postNewFireStation(originalFirestation,
            originalFirestations).toString());

  }
}