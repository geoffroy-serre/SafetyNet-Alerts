package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class OutPutPerson {

  @JsonProperty("firstName")
  private String firstName;
  @JsonProperty("lastName")
  private String lastName;
  @JsonProperty("home")
  private OutPutHome home;
  @JsonProperty("phone")
  private String phone;
  @JsonProperty("birthdate")
  private LocalDate birthdate;
  @JsonProperty("age")
  private Integer age;
  @JsonProperty("email")
  private String email;
  @JsonProperty("medicalRecord")
  private OutPutMedicalRecord medicalRecord  ;
  @JsonProperty("idMedicalRecord")
  private UUID idMedicalRecord  ;

  @JsonProperty("homeId")
  private UUID idHome;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public UUID getIdHome() {
    return idHome;
  }

  public void setIdHome(UUID idHome) {
    this.idHome = idHome;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public UUID getIdMedicalRecord() {
    return idMedicalRecord;
  }

  public void setIdMedicalRecord(UUID idMedicalRecord) {
    this.idMedicalRecord = idMedicalRecord;
  }

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

  public OutPutHome getHome() {
    return home;
  }

  public void setHome(OutPutHome home) {
    this.home = home;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public OutPutMedicalRecord getMedicalRecord() {
    return medicalRecord;
  }

  public void setMedicalRecord(OutPutMedicalRecord medicalRecord) {
    this.medicalRecord = medicalRecord;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof com.safetynet.alerts.model.OutPutPerson)) {
      return false;
    }
    OutPutPerson outPutPerson =
            (OutPutPerson) obj;
    return Objects.equals(firstName, outPutPerson.firstName) &&
            Objects.equals(lastName, outPutPerson.lastName) &&
            Objects.equals(birthdate, outPutPerson.birthdate) &&
            Objects.equals(email, outPutPerson.email) &&
            Objects.equals(phone, outPutPerson.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, birthdate, email, phone);
  }

  @Override
  public String toString() {
    return "OutPutPerson{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", home=" + home +
            ", phone='" + phone + '\'' +
            ", birthdate=" + birthdate +
            ", age=" + age +
            ", email='" + email + '\'' +
            ", medicalRecord=" + medicalRecord +
            ", idMedicalRecord=" + idMedicalRecord +
            ", idHome=" + idHome +
            '}';
  }
}


