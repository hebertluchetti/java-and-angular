# Web Applicaton - Blood Bank using:
# Angular (front-end client), Spring Boot (back-end rest api) and MyQSL (relational database) 

This system is a blood bank agency that has a list of candidate donors that processes this data to extract some information.
The application, based on the data in the JSON file, displays the following results:

• How many candidates do we have on this list in each state of Brazil?
• Average BMI in each age group every ten years: 0 to 10; 11 to 20; 21 to 30, etc. (BMI = weight /
height ^ 2)
• What is the percentage of obese men and women? (Those who have a BMI> 30 are obese)
• What is the average age for each blood type?
• The number of possible donors for each recipient blood type.
ATTENTION: Only people aged 16 to 69 years and weighing more than 50 kg can donate blood.

The application built with Angular 10 and Spring Boot that connects to the MySQL database. 

## Requirements

1. Java - 11

2. Maven - 3.x.x

3. MySQL - 8.x.x

4. Angular - 10.1.1

5. Node - 12.18.3

6. Npm - 6.14.6

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/hebertluchetti/java-and-angular
```

**2. Create MySQL database**
```bash
The database (bloodbankdb) and all tables are created automatically by the JPA
TThe json (data.json) file with donor information is loaded during the first execution of the java application.
```

**3. Change MySQL username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your MySQL installation

**4. Build and run the app using maven (blood-bank)**

```bash
mvn install
java -jar target/blood-bank-1.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines 5 Rest endpoints. 
The endpoints documentation can be found through the link below:
http://localhost:8080/bloodbank-app/swagger-ui.html#

    
You can test them using postman or any other rest client.

**5. Install node modules for angular application (blood-bank)**

```bash
npm install
```

**6. Run the angular application**

```bash
ng serve
```

Contact Hebert Luchetti Ribeiro
```bash
email: hebert.luchetti@gmail.com
linkedin: https://www.linkedin.com/in/hebert-luchetti-ribeiro-aa42923
youtube: https://www.youtube.com/channel/UCf0w-raMzq6rNxxjNJ7D1gQ
githun: https://github.com/hebertluchetti

```
