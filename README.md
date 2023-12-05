# City information API

## Quick start

### 1.) Build and Run with Docker Compose

To run the whole application using Docker, follow this steps:

1. Navigate to the project root directory
2. Build the application with `mvn clean install` command
3. Create and start the Docker containers with following command `docker-compose up --build`
4. This command will build the City Info Application image and start both the application and database containers.

### 2.) Run Database Separately and Application Locally

Alternatively, you can run just the database within the docker container,
and application from some IDE or by directly executing the JAR file.

1. Navigate to the project root directory
2. Build the application with `mvn clean install` command
3. Create and start the Docker database container with following command: `docker-compose up city-info-db`
4. Build and run the application from your IDE or by executing the JAR file:
   1. From IDE: Open the project in your preferred IDE and run the application.
   2. Using JAR file with following command: `java -jar ./target/city-information-0.0.1-SNAPSHOT.jar`


### 3.) Manual Setup (Without Docker)

As a third option, you can opt not to use Docker and instead have a locally installed database:

1. Ensure you have a locally installed database (e.g., PostgreSQL).
2. If needed, configure the database port within the application.yml file.
3. Navigate to the project root directory
4. Build the application with `mvn clean install` command 
5. Build and run the application from your IDE or by executing the JAR file:
   1. From IDE: Open the project in your preferred IDE and run the application.
   2. Using JAR file with following command: `java -jar ./target/city-information-0.0.1-SNAPSHOT.jar`

## Testing the Endpoints

`City Information API.postman_collection.json` file located at the project's root is a Postman collection that simplifies
the process of testing the application's endpoints. 

Import the collection into Postman to efficiently conduct comprehensive tests on the functionalities provided by the City Information API.
