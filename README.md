# Foodie

your go-to app for crafting the perfect dining experience.

## Description

Say farewell to the era of booking tables via phone and anxiously waiting for a waiter's response – with Foodie, the dining game has evolved.
Now, securing a table at your favorite restaurant is a breeze, putting you in control of when, where, and how you indulge in your gastronomic delights.

Key Features:

📅 Effortless Reservations: No more long queues or uncertainty. Foodie empowers you to effortlessly secure a table at your preferred restaurant, 
ensuring that your dining plans align with your schedule.

🌟 Table Tailoring: Immerse yourself in the dining ambiance you crave. Foodie lets you pick your preferred table, whether it's a cozy corner for an intimate date or a lively spot for a group celebration. Your perfect setting, your way.

🕰️ Time Precision: Time is a precious commodity, and Foodie respects that. Specify the exact time you want to dine, and witness the magic as your chosen restaurant prepares to welcome you at your preferred hour.

🎁 Exclusive Additions: Elevate your Foodie experience with exclusive perks! Enjoy personalized recommendations based on your taste preferences, unlock special offers from partner restaurants, and get a sneak peek at mouthwatering menu highlights before you even set foot in the door.

👨‍💼 Adding Your Restaurant: If you are a restaurant owner and want to add your place to Foodie, you can do this very easily! Just create a "Restaurant Owner" account on our site and let a broader audience savor the exceptional experience you have to offer..

🤩 Something Extra: Foodie goes beyond the ordinary, adding that extra dash of delight to your culinary journey. Whether it's surprise discounts, chef's specials, or insider tips, Foodie keeps the excitement alive with every reservation.

Download Foodie now and transform your dining escapades into unforgettable experiences. <br> 
Because every meal should be a celebration, and with Foodie, you're always at the head of the table. <br> 
Bon appétit! 🎉

## Boring stuff for developers

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
