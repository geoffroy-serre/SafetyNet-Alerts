package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class OutPutChild {

  @JsonProperty("children")
  private ArrayList<OutPutPerson> child;
  @JsonProperty("adults")
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
