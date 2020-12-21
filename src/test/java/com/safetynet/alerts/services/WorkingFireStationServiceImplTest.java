package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingFireStation;
import com.safetynet.alerts.model.WorkingResponse;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkingFireStationServiceImplTest {

  @Mock
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;
  @Mock
  RetrieveWorkingDataRepository retrieveWorkingDataRepository;

  @InjectMocks
  WorkingFireStationServiceImpl workingFireStationService;

  private OriginalFirestation originalFirestation = new OriginalFirestation();
  private OriginalFirestation originalFirestation2 = new OriginalFirestation();
  private OriginalFirestation originalFirestation3 = new OriginalFirestation();
  private ArrayList<OriginalFirestation> originalFirestations = new ArrayList<>();
  private OriginalResponse originalResponse = new OriginalResponse();
  private WorkingResponse workingResponse = new WorkingResponse();
  private WorkingFireStation workingFireStation = new WorkingFireStation();
  private WorkingFireStation workingFireStation2 = new WorkingFireStation();
  private ArrayList<WorkingFireStation> workingFireStations = new ArrayList<>();
  private ArrayList<UUID> uuids = new ArrayList<>();

  @BeforeEach
  void setUp() {
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

    uuids.add(UUID.randomUUID());
    uuids.add(UUID.randomUUID());

    workingFireStation.setIdFireStation(UUID.randomUUID());
    workingFireStation.setStationNumber(50);
    workingFireStation.setWorkingHomeIds(uuids);
    workingFireStations.add(workingFireStation);

    workingFireStation2.setIdFireStation(UUID.randomUUID());
    workingFireStation2.setStationNumber(18);
    workingFireStation2.setWorkingHomeIds(uuids);
    workingFireStations.add(workingFireStation2);

    workingResponse.setFirestations(workingFireStations);
  }


  @Test
  void createWorkingFiresStationHashMap() {
    when(retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE)).thenReturn(originalResponse);
    assertEquals(originalFirestation.getStation(),
            workingFireStationService.createWorkingFiresStationHashMap().get(15).getStationNumber());
    assertEquals(originalFirestation3.getStation(),
            workingFireStationService.createWorkingFiresStationHashMap().get(14).getStationNumber());
    assertTrue(workingFireStationService.createWorkingFiresStationHashMap().get(14).getWorkingHomeIds().isEmpty());
  }

  @Test
  void getWorkingFireStationHashMap() {
    when(retrieveWorkingDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE)).thenReturn(workingResponse);
    assertFalse(workingFireStationService.getWorkingFireStationHashMap().isEmpty());
    assertEquals(workingFireStation.getStationNumber(),
            workingFireStationService.getWorkingFireStationHashMap().get(50).getStationNumber());
    assertEquals(workingFireStation2.getStationNumber(),
            workingFireStationService.getWorkingFireStationHashMap().get(18).getStationNumber());
  }
}