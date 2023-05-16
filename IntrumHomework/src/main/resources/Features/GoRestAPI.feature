#Author: jessejamesbalfe@gmail.com

Feature: I want to test the GoRest Users API

  Scenario: Check get user returns all current users
    When a user calls the api with "GET" http request
    Then the api call returns with a status code 200
    And the result contains users
    
  Scenario: Create a new user, edit the details of that user and then delete the new user
  	Given new user data from the CSV file "CleanUserData.csv" and row number 2
  	Then add a new user with that data
  	When the new user is created
  	Then this user should be returned by the API
  	When a change is made to this new user
  	Then the updated user details should be returned by the API
  	When the new user is deleted
  	Then the user should no longer exist 