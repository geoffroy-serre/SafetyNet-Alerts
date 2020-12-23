# SafetyNet Alert

## Technical Requirements

Java JDK 11 min, Maven 3.x
Postman for testing purposes.

## Notes
data.json is the used file for input datas. 
workingData.json for processing the data in the working given format  (with use for ids, and differents things).
data_save.json is a save of original data.json in case there is a lost or a wrong manipulation, so we can get original given data back.

## Post Put Delete Formats

To use the applications properly, you have to use these proper format

### Person for Post and PuT

```Json
 {
 "firstName":"someFirstName",
 "lastName":"someLastName",
 "address" :"anAdress",
 "city":"aCity",
 "zip":"zipCode",
 "phone":"phoneNumber",
 "email":"email@email.com
}
```

### Person for delete

```Json
 {
 "firstName":"someFirstName",
 "lastName":"someLastName"
}
```

### FireStation for Post and Pu

```Json
 {
 "address":"anAdress",
 "station":"stationNumber",
}

```

### FireStation for Delete : deleting a station

```Json
 {
 "station":"stationNumber",
}
```

### FireStation for Delete : deleting a mapped address

```Json
 {
 "address":"anAdress",
}

```

### MedicalRecord for Post and Put (allergies and medications can be empty, or with 1 2 3 ... values

```Json
 {
  "firstName":"someFirstName",
 "lastName":"someLastName",
 "birthdate":"MM/dd/yyy",
 "medications":["medications1","medications2"],
 "allergies":["allergies1","allergies2"],
}

```

### MedicalRecord for Delete

```Json
 {
  "firstName":"someFirstName",
 "lastName":"someLastName",
}

```

## Endpoints

```html
/firestation
/medicalRecord
 /persons
 ```

## URL

```html
/firestation?stationNumber=<station_number>
 ```

People covered by the given station. Return firstName, lastName, adress, phone and a count of child
and adults.

 ```html
/childAlert?address=<address>
 ```

Return a list of children for given address (+18, can be set to +21 with ofAge constant)

 ```html
/phoneAlert?firestation=<firestation_number>
 ```

Return a lsit of phone of people covered by given station.

 ```html
/fire?address=<address>
 ```

Return a list of people living at given address and the linked stationNumber. Show firstNAme,
lastName, phone, age, medications, and allergies.

 ```html
/flood/stations?stations=<a list of station_numbers>
 ```

Return homes served by given station(s). Persons are ordered by adress. Show firstName, lastName,
phone, age, medications, allergies.

 ```html
/personInfo?firstName=<firstName>&lastName=<lastName>
 ```

Return firstName, lastName, adress, age, email, medications, allergies, for given parameters

 ```html
/communityEmail?city=<city>
```

Return all email for persons living in given city.


