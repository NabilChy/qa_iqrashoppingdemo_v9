package stepdefinitions;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static utils.Config.BASE_URL;


import io.restassured.response.Response;
import org.testng.Assert;
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

//    @When("I send a GET request to retrieve customer email")
//    public void iSendAGETRequestToRetrieveCustomerEmail() {
//        Response response = given()
//                .auth()
//                .preemptive()
//                .basic(Config.consumer_key, Config.consumer_secret)
//                .when()
//                .get(Config.BASE_URL + customerId)
//                .then()
//                .statusCode(200)
//                .extract()
//                .response()
//                ;
//        String retrievedEmail = response.jsonPath().getString("email");
//        System.out.println(retrievedEmail);
//
//
//        Assert.assertNotNull(retrievedEmail, "Customer email should not be null");
//
//    }
//
//    @Then("the customer email is retrieved successfully with status code 200")
//    public void theCustomerEmailIsRetrievedSuccessfullyWithStatusCode200() {
//
//    }
//
//    @When("I send a PUT request to update customer email")
//    public void iSendAPUTRequestToUpdateCustomerEmail() {
//        String newEmail = "updated_" + generateRandomString(5) + "@example.com";
//
//        // Create update data
//        HashMap<String, String> requestBody = new HashMap<>();
//        requestBody.put("email", newEmail);
//
//
//        // Send PUT request to update the customer's email
//        Response response = given()
//                .auth().preemptive().basic(Config.consumer_key, Config.consumer_secret)
//                .header("Content-Type", "application/json")
//                .body(requestBody)
//                .when().put(BASE_URL + customerId)
//                .then().statusCode(200).extract().response();
//
//
//        String updatedEmail = response.jsonPath().getString("email");
//        Assert.assertEquals(updatedEmail, newEmail, "Customer email should be updated.");
//
//    }
//
//    @Then("the customer email is changed successfully with status code 200")
//    public void theCustomerEmailIsChangedSuccessfullyWithStatusCode200() {
//
//    }
//
//    @When("I send a DELETE request to remove a customer")
//    public void iSendADELETERequestToRemoveACustomer() {
//        Response response = given()
//                .auth()
//                .preemptive()
//                .basic(Config.consumer_key, Config.consumer_secret)
//                .when()
//                .delete(Config.BASE_URL + customerId + "?force=true")
//                .then()
//                .extract()
//                .response();
//        if (response.statusCode() == 200) {
//            System.out.println("Customer deleted successfully");
//        } else if (response.statusCode() == 404) {
//            System.out.println("Customer is already deleted or does not exist.");
//        }
//
//        Assert.assertEquals(response.statusCode(), 200, "Customer should be deleted successfully.");
//    }
//
//    @Then("the customer is deleted successfully with status code 200")
//    public void theCustomerIsDeletedSuccessfullyWithStatusCode200() {
//    }
//
//
}
