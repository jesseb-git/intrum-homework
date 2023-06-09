# Intrum QA homework
This repo contains code for an Intrum QA automation interview project

## Objective
The goal was to create a test automation solution from scratch and cover with test cases for the following API endpoint
   + [GoRest API](https://gorest.co.in/public/v2/users)

## Acceptance Criteria

+ Programming language and project type - Java (Maven).
+ Testing framework used - [Cucumber](https://cucumber.io/)
+ Functionality of GET / POST / PUT / DELETE requests should be covered
+ USERS data used in test scenarios (example: user creation) is kept in the project as a resource CSV file.
+ Implemented report generating functionality.
+ Readme file created with basic information - requirements / how to run tests / how to generate reports etc.


## Requirements
+ Maven
+ Java
+ Cucumber
+ JUnit

## How to run the tests
1. Ensure that you have installed the requirements as listed above
2. Clone the repo
3. Open a console window in the /IntrumHomework folder
4. Execute the following command to run the tests
   `mvn clean package`

## How to view the reports
A report is generated after each run and it is available to view in target/html-reports/. The image below shows how they appear when viewed in a browser

![image](https://github.com/jesseb-git/intrum-homework/assets/133359394/c9b1c51d-d42e-409a-866b-37e6ab19cc9f)

Cucumber also generates a cloud report after each run and the link for that is available in the console after each run

![image](https://github.com/jesseb-git/intrum-homework/assets/133359394/fbf5731a-310e-4e12-9862-b9f5e0771c8c)
![image](https://github.com/jesseb-git/intrum-homework/assets/133359394/25261ebc-3780-4661-9547-a8b1a1584a73)

