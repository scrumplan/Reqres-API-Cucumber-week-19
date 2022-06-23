@reqres
Feature: As a user I would like to check the CRUD functionality of reqres.in

  Scenario: I want to create a new user
    Given I am on homepage of the given url
    When I send POST request to the the application using a valid payload to create a User
    Then I get status code 201
    And  I verify if user has been added to the application

  Scenario: I want to update new created user
    When I send PUT request to the the application using a valid payload to update an User
    Then I will get status code 200
    And I verify if user has been updated

  Scenario: I want to delete new created user
    When I send Delete request to the the application using userId
    Then I will get status code 204
    And I verify if user has been deleted




