# Cart service

## Installation

You only need to be in the project's root directory and execute the command `mvn clean install`. Afterward, you can run the main Java class [CartApplication.java](src%2Fmain%2Fjava%2Fcom%2Fgonzalo%2Fcart%2FCartApplication.java) or use `mvn springboot:run`.

## Testing

The service includes Swagger documentation, which allows you to test it by visiting http://localhost:4000/cart/swagger-ui/index.html or by using Postman by importing the [cart.postman_collection.json](DOC%2Fcart.postman_collection.json) file.

The project also includes unit tests using JUnit and Mockito

## Error control

I have implemented basic **ExceptionHandlers** to demonstrate how customized errors can return custom responses.

## Cart expiration

or this implementation, I've considered that each user will have their own cart, achieved through user sessions. If you want to change the time-to-live (TTL) for sessions, you need to modify two files:
1. [application.yml](src%2Fmain%2Fresources%2Fapplication.yml) change the property `server.servlet.session.timeout`.
2. [Constants.java](src%2Fmain%2Fjava%2Fcom%2Fgonzalo%2Fcart%2Fmodel%2FConstants.java) change the variable `SESSION_TIMEOUT`.

With these configurations, the session will be renewed every time the cart is updated.

If you want to test that two individuals have different carts, you can add one cart using Postman and another using Swagger. This will create two separate sessions with two different carts. The session from Postman will never be able to access the cart from Swagger, and vice versa.

## Extras

Since this is a test project, I have implemented various methods to solve similar problems. This demonstrates my knowledge of different techniques, such as using the `application.properties` file to generate logs and creating a class with static variables for use as constants in other classes.