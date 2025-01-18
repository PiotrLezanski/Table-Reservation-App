# Foodie

app to reserve tables in restaurants.

There is profile for customer and restaurant owners. Customers can scan their reservations and create new. 
Owners can add new restaurants and manage their bookings.

## Boring stuff for developers

### UML
![Foodie_UML](https://github.com/user-attachments/assets/9b401a68-cf90-4903-a5f7-335bcdf05e8b)


### Running the application

The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any Maven project.

### Deploying to Production

To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/my-app-1.0-SNAPSHOT.jar`

### Project structure

- `MainLayout.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). This setup uses
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `themes` folder in `frontend/` contains the custom CSS styles.

### Deploying using Docker

To build the Dockerized version of the project, run

```
mvn clean package -Pproduction
docker build . -t my-app:latest
```

Once the Docker image is correctly built, you can test it locally using

```
docker run -p 8080:8080 my-app:latest
```
