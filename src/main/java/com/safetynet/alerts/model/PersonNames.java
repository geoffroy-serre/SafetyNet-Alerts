package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
/**
 * Wrapper for search with last and first name
 * @param String firstName;
 * @param String lastName;
 *
 */
public class PersonNames {

  @JsonProperty("firstName")
  @NotBlank
  @NotNull
  private String firstName;

  @JsonProperty("lastName")
  @NotBlank
  @NotNull
  private String lastName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "PersonNames{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
