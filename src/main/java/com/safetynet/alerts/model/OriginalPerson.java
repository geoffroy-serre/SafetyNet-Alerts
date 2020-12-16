package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
/**
 * Contains original data format
 *
 *@param String firstName;
 *@param String lastName;
 *@param String address;
 *@param String city;
 *@param String zip;
 *@param String phone;
 *@param String email;
 */
public class OriginalPerson {



  @JsonProperty("firstName")
  @NotBlank
  @NotNull
  private String firstName;

  @JsonProperty("lastName")
  @NotBlank
  @NotNull
  private String lastName;

  @JsonProperty("address")
  @NotBlank
  @NotNull
  private String address;

  @JsonProperty("city")
  @NotBlank
  @NotNull
  private String city;

  @JsonProperty("zip")
  @NotBlank
  @NotNull
  private String zip;

  @JsonProperty("phone")
  @NotBlank
  @NotNull
  private String phone;

  @JsonProperty("email")
  @NotBlank
  @NotNull
  private String email;

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getZip() {
    return zip;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCity() {
    return city;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhone() {
    return phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return
            "Person{"
                    + ",firstName = '" + firstName + '\''
                    + ",lastName = '" + lastName + '\''
                    + ",address = '" + address + '\''
                    + ",zip = '" + zip + '\''
                    + ",city = '" + city + '\''
                    + ",phone = '" + phone + '\''
                    + ",email = '" + email + '\''
                    + "}";
  }
}