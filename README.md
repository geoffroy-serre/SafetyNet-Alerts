# SafetyNet Alert

## Technical Requirements

Java JDK 11 , Maven 3.6.3

## Installation Notes
Run with terminal / command prompt or powershell, form the project folder
```Bash
mvn clean install
```
The output jar files will be generated in the target folder of the project.</br>
Don't forget to move your jar file and data.json in the same folder !!! </br>
Open terminal / command prompt /powershell and run 
```bash
java -jar {nameOfTheFile}.jar
```
Tomcat is embedded so just launch the jar and it's ready to work</br>
Port 8080 is use by the app.


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


