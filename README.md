# Experis Coding Test - Backend

The purpose of this application is to help in the recruiting process of Experis Academy. 
It has designed and implemented based on needs of the customer, and it contains different kind of 
questions to test the suitability of candidates for the course. 

The application is developed by using Spring Boot, Spring Security, Hibernate and PostgreSQL. It contains also two separate frontend applications, 
one for the users and one for the admin. The test application for the users can be used by anonymous users but the admin application requires authentication 
The authentication has created by using Firebase and the backend verifies Firebase token. 

## API

Endpoints are secured by using Spring Security. They can be share to two categories, to the public endpoints which can be used without authentication 
for the test frontend) and the private endpoints which requires the authentication (for the admin frontend). 

### Public endpoints

Get all test questions:<br>
`GET /api/v1/questions/test`<br>

Check if the email already exists:<br>
`GET /api/v1/users/checkuser`<br>

Create user:<br>
`POST /api/v1/users`<br>

Verify recaptcha:<br>
`POST /api/v1/users/verify`<br>

Save answers from list to database with user id:<br>
`POST /api/v1/answers/user/{userId}`<br>

### Private endpoints

The rest of the endpoints are private and requires Firebase token. 
More information in the postman collection which can be found from the root folder of the application.

## Limitations

The application contains only required API endpoints and not full CRUD for all of the tables. 
For example the questions can be hidden but it is not possible to remove them. This is because admin frontend needs them even it has been "removed" from the test.

## Installation

Clone the source code: https://github.com/LLehtola/experis-coding-test-back.git

You need to set the following environment variables depending on your setup.

Database: 
DB_URI, PORT, DB_USERNAME and DB_PASSWORD

Firebase:
GOOGLE_CREDENTIALS

Google recaptcha:
google.recaptcha.secret

