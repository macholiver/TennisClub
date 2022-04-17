# Tennis club
- **Technologies:** Java 11, Maven, Spring, Hibernate, Lombok

## How to run this app
- ```mvn clean install``` to compile
- ```cd rest``` to enter rest folder
- ```mvn cargo:run``` to run the local host

## Usage
### Command line
- Access to list of courts ```curl -X GET http://localhost:8080/rest/courts``` 
- Access to list of reservations for specific unique court id ```curl -X GET http://localhost:8080/rest/reservations/court/{id}```
- Access to list of reservations for specific phone number ```curl -X GET http://localhost:8080/rest/reservations/phone/{phone}```
- Create reservation for given court, game type, phone number and customer name. System return the cost of the reservation ```curl -X POST -i -H "Content-Type: application/json" --data '{"start":"{date}","end":"{date}","gameType":"SINGLE", "courtId":"{id}", "name":"{name}", "phone":"{phone}"}' http://localhost:8080/rest/reservations/create```

Where {date} is of "yyyy-dd-mm hh:mm", {id} is the id of court, {name} name of the customer, {phone} phone number of the customer

