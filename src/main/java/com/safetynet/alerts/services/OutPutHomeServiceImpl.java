package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.constants.OfAgeRules;
import com.safetynet.alerts.interfaces.OutPutHomeService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.model.WorkingHome;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OutPutHomeServiceImpl implements OutPutHomeService {
  final Logger logger = LogManager.getLogger("OutPutHomeServiceImpl");
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutHome> setStationNumberNull(ArrayList<OutPutHome> outPutHomes) {
    logger.debug("Entering setStationNumberNull ");
    ArrayList<OutPutHome> result = new ArrayList<>();
    for (OutPutHome outPutHome : outPutHomes) {
      outPutHome.setStationNumber(null);
      result.add(outPutHome);
    }
    logger.debug("Success setStationNumberNull ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutHome> getCountChildrenAndAdultsforList(ArrayList<OutPutHome> outPutHomes) {
    logger.debug("Entering getCountChildrenAndAdultsforList ");
    for (OutPutHome outPutHome : outPutHomes) {
      int underAge = 0;
      int ofAge = 0;
      for (OutPutPerson outPutPerson : outPutHome.getPersons()) {
        outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
        if (outPutPerson.getAge() < OfAgeRules.OF_AGE_FR) {
          underAge++;
        } else if (outPutPerson.getAge() >= OfAgeRules.OF_AGE_FR) {
          ofAge++;
        }
        outPutPerson.setBirthdate(null);
        outPutPerson.setEmail(null);

      }
      outPutHome.setNumberOfAdults(ofAge);
      outPutHome.setNumberOfChildren(underAge);
    }
    logger.debug("Success getCountChildrenAndAdultsforList ");
    return outPutHomes;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutHome> getHomeByStationNumber(ArrayList<OutPutHome> outPutHomes,
                                                      OutPutFireStation firestation) {
    logger.debug("Entering getHomeByStationNumber ");
    int stationNumber = firestation.getStationNumber();
    ArrayList<OutPutHome> fireStationHomes = new ArrayList<>();

    for (UUID outPutHomeId : firestation.getHomeListIds()) {
      if (outPutHomeId != null) {
        for (OutPutHome outPutHome : outPutHomes) {
          if (outPutHomeId.equals(outPutHome.getIdHome())) {
            outPutHome.setStationNumber(stationNumber);
            fireStationHomes.add(outPutHome);
          }
        }
      }

    }
    logger.debug("Success getHomeByStationNumber ");
    return fireStationHomes;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutHome> setPersons(ArrayList<OutPutPerson> persons,
                                          ArrayList<OutPutHome> homes) {
    logger.debug("Entering setPersons ");
    ArrayList<OutPutHome> result = new ArrayList<>();
    for (OutPutHome outPutHome : homes) {
      ArrayList<OutPutPerson> personeHome = new ArrayList<>();
      for (OutPutPerson outPutPerson : persons) {
        if (outPutHome.getIdHome().equals(outPutPerson.getIdHome())) {
          personeHome.add(outPutPerson);
        }
        outPutHome.setPersons(personeHome);
      }
      result.add(outPutHome);
    }
    logger.debug("Success setPersons ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public OutPutHome setPersonsHome(ArrayList<OutPutPerson> persons,
                                   OutPutHome home) {

    logger.debug("Entering setPersonsHome ");
    ArrayList<OutPutPerson> personeHome = new ArrayList<>();
    for (OutPutPerson outPutPerson : persons) {
      if (home.getIdHome().equals(outPutPerson.getIdHome())) {
        if (outPutPerson.getBirthdate() != null) {
          outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
        }
        personeHome.add(outPutPerson);
      }
      home.setPersons(personeHome);
    }
    logger.debug("Success setPersonsHome ");
    return home;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<UUID> getHomesIds(ArrayList<OutPutHome> homes) {
    logger.debug("Entering getHomesIds ");
    ArrayList<UUID> result = new ArrayList<>();
    for (OutPutHome outPutHome : homes) {
      result.add(outPutHome.getIdHome());
    }
    logger.debug("Success getHomesIds ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutHome> getHomesbyIds(ArrayList<OutPutFireStation> firestations,
                                             ArrayList<OutPutHome> homes) {
    logger.debug("Entering getHomesbyIds ");
    ArrayList<OutPutHome> homesResult = new ArrayList<>();
    for (OutPutFireStation outPutFireStation : firestations) {
      for (UUID fireStationHomeId : outPutFireStation.getHomeListIds()) {
        for (OutPutHome outPutHome : homes) {
          if (outPutHome.getIdHome().equals(fireStationHomeId)) {
            outPutHome.setStationNumber(outPutFireStation.getStationNumber());
            homesResult.add(outPutHome);
          }
        }
      }
    }
    logger.debug("Success getHomesbyIds ");
    return homesResult;
  }

  @Override
  /**
   * @inheritDoc
   */
  public OutPutHome getHomeByAddress(String address) {
    logger.debug("Entering getHomeByAddress ");
    OutPutHome selectedHome = new OutPutHome();
    for (OutPutHome outPutHome : getOutPutHomeList()) {
      if (outPutHome.getAddress().equalsIgnoreCase(address)) {
        selectedHome = outPutHome;
      }
    }
    logger.debug("Success getHomeByAddress ");
    return selectedHome;
  }

  @Override
  /**
   * @inheritDoc
   */
  public HashSet<UUID> getHomesByCity(String city) {
    logger.debug("Entering getHomesByCity ");
    HashSet<UUID> homesIds = new HashSet<>();

    for (OutPutHome outPutHome : getOutPutHomeList()) {
      if (outPutHome.getCity().equalsIgnoreCase(city)) {
        homesIds.add(outPutHome.getIdHome());
      }
    }
    logger.debug("Success getHomesByCity ");
    return homesIds;
  }

  @Override
  /**
   * @inheritDoc
   */
  public OutPutHome transformWorkingIntoOutPut(WorkingHome inputHome) {
    logger.debug("Entering transformWorkingIntoOutPut ");
    OutPutHome result = new OutPutHome();

    result.setAddress(inputHome.getAddress());
    result.setCity(inputHome.getCity());
    result.setZip(inputHome.getZip());
    logger.debug("Success transformWorkingIntoOutPut ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutHome> getOutPutHomeList() {
    logger.debug("Entering getOutPutHomeList ");
    ArrayList<OutPutHome> outPutHomes =
            retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE).getHomes();
    logger.debug("Success getOutPutHomeList ");
    return outPutHomes;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutHome> transformWorkingIntoOutPut(ArrayList<WorkingHome> inputHome) {
    logger.debug("Entering transformWorkingIntoOutPut ");
    ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
    for (WorkingHome current : inputHome) {
      OutPutHome result = new OutPutHome();
      result.setAddress(current.getAddress());
      result.setCity(current.getCity());
      result.setZip(current.getZip());

      outPutHomes.add(result);
    }
    logger.debug("Success transformWorkingIntoOutPut ");
    return outPutHomes;
  }
}
