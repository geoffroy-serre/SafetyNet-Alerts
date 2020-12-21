# SafetyNet Alert

## Technical Requirements
Java JDK 11 min

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
/childAlert?address=<address>
/phoneAlert?firestation=<firestation_number>
/fire?address=<address>
/flood/stations?stations=<a list of station_numbers>
/personInfo?firstName=<firstName>&lastName=<lastName>
/communityEmail?city=<city>
```


