package com.safetynet.alerts.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class OutPutChild {

  private ArrayList<OutPutPerson> child;
  private ArrayList<OutPutPerson> familly;

  public ArrayList<OutPutPerson> getChild() {
    return child;
  }

  public void setChild(ArrayList<OutPutPerson> child) {
    this.child = child;
  }

  public ArrayList<OutPutPerson> getFamilly() {
    return familly;
  }

  public void setFamilly(ArrayList<OutPutPerson> familly) {
    this.familly = familly;
  }


  @Override
  public String toString() {
    return "OutPutChild{" +
            "child=" + child +
            ", familly=" + familly +
            '}';
  }
}
