package com.safetynet.alerts.model;

import java.util.ArrayList;

public class FireStationList {
   
    public ArrayList<FireStation> fireStation;

    public ArrayList<FireStation> getFireStation() {
        return fireStation;
    }

    public void setFireStation(ArrayList<FireStation> pFireStation) {
        this.fireStation = pFireStation;
    }

    @Override
    public String toString() {
        return  fireStation.toString() ;
    }

}
