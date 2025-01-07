Feature: CustomerWorkFlow
  Scenario: Create a new customer
    Given the API base URL is configured
    When I send a POST request to create a customer
    Then the customer is created successfully with status code 201