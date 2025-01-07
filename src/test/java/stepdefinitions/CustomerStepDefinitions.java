package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import utils.Config;

import java.util.HashMap;
import java.util.Random;


public class CustomerStepDefinitions {

    static int customerId;
    @Given("the API base URL is configured")
    public void theAPIBaseUrlIsConfigured(){
        baseURI = Config.BASE_URL;

    }

    @When("I send a POST request to create a customer")
    public void iSendAPostRequestToCreteACustomer(){
        // Generate random email and username
        String randomString = generateRandomString(8);
        String email = randomString + "@example.com";
        String username = "user_" + randomString;

        // Create user data using HashMap
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("first_name", "John");
        requestBody.put("last_name", "Doe");
        requestBody.put("username", username);
        requestBody.put("password", "password123");

        Response response = given()
                .auth().preemptive().basic(Config.consumer_key, Config.consumer_secret)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when().post()
                .then().statusCode(201).extract().response();

        // Extract and print the user ID
        customerId = response.jsonPath().getInt("id");
        System.out.println(customerId);

    }

    @Then("the customer is created successfully with status code 201")
    public void theCustomerIsCreatedSuccessfullyWithStatusCode201(){

    }
    private static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return builder.toString();
    }

}
