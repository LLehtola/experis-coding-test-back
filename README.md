# Experis Coding Test - Backend

The purpose of this application is to help in the recruiting process of Experis Academy. 
It has designed and implemented based on needs of the customer, and it contains different kind of 
questions to test the suitability of candidates for the course. 

The application is developed by using Spring, Spring Security Hibernate and PostgreSQL. It contains also two separate frontend applications, 
one for the users and one for the admin. The test application for the users can be used by anonymous users but the admin application requires authentication 
The authentication has created by using Firebase and the backend verifies Firebase token. 

## API

Endpoints are secured by using Spring Security. They can be share to two categories, to the open endpoints which can be used without authentication 
for the test frontend) and the closed endpoints which requires the authentication (for the admin frontend). 

The postman collection can be found from the root folder of the application.

## Installation

Clone the source code: https://github.com/LLehtola/experis-coding-test-back.git

You need to set the following environment variables depending on your setup.

Database: 
DB_URI, PORT, DB_USERNAME and DB_PASSWORD

Firebase:
GOOGLE_CREDENTIALS

Google recaptcha:
google.recaptcha.secret

