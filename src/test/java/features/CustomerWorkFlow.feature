Feature: CustomerWorkFlow
  Scenario: Create a new customer
    Given the API base URL is configured
    When I send a POST request to create a customer
    Then the customer is created successfully with status code 201

#  Scenario: Retrieve customer email
#    Given the API base URL is configured
#    When I send a GET request to retrieve customer email
#    Then the customer email is retrieved successfully with status code 200
#
#  Scenario: Update customer email
#    Given the API base URL is configured
#    When I send a PUT request to update customer email
#    Then the customer email is changed successfully with status code 200
#
#  Scenario: Delete customer
#    Given the API base URL is configured
#    When I send a DELETE request to remove a customer
#    Then the customer is deleted successfully with status code 200