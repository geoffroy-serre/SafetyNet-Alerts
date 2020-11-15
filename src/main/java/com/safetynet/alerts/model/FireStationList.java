package com.safetynet.alerts.model;

import java.util.ArrayList;

public class FireStationList {
   
    public ArrayList<FireStation> fireStation;

    public ArrayList<FireStation> getPerson() {
        return fireStation;
    }

    public void setPerson(ArrayList<FireStation> pFireStation) {
        this.fireStation = pFireStation;
    }

    @Override
    public String toString() {
        return  fireStation.toString() ;
    }

}
