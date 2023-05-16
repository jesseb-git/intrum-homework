#Author: jessejamesbalfe@gmail.com

Feature: I want to verify that the API handles invalid data gracefully

  Scenario: Confirm that duplicate data is not added 
    Given an email from "InvalidUserData.csv" that already exists for a user
    When I attempt to add a new user with this data
    Then the correct error code should be returned
   
  Scenario: Confirm that invalid data is not added 
    Given an email from "InvalidUserData.csv" that is not valid
    When I attempt to add a new user with this data
    Then the correct error code should be returned 

    Scenario: Confirm that authentication is required to create a user
    Given valid new user data from the CSV file "CleanUserData.csv" and row number 3
    When I attempt to add a new user with this data without an AccessToken
    Then the correct error code should be returned 