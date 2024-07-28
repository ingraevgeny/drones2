## Drones

[[_TOC_]]

---

:scroll: **START**

### Description

This project is a part of Microservice Architecture Application example (see approot project)

### Requirements
___

JDK 17, Maven 3+

### How To Build

---
open the console, go to the drone application project folder and enter the commands sequentially: 

 _mvn install_

 _cd target_

 _java -jar drones-0.0.1-SNAPSHOT.jar_

### How To Use

___

to use the Drones API, open your browser and follow the link:
  _http://localhost:8085/drones-api.html_

you have to see all the endpoints of the app.
You can expand e.g. /getAvailableDrones, press _Execute_ button and then discover a server response. 

Also, you can research any endpoint by the same way.

### Explore H2 DB

---
go to: 
 _http://localhost:8080/h2-console_

### Run Tests

---
from root folder run command:
 
_mvn test_

### Periodic Task

---
on the server console you can see how the periodic task works e.g.:

_Log battery level task performed at 2023-12-07T14:39:00.54331840_

Result of the periodic task performing you can find in DB, run query:

_SELECT * FROM "LogBatteryLevel"_

:scroll: **END** 
