Feature: Error Validation for  ecommerce website

@Error
Scenario Outline:Verify error message for wrong credentials
Given I landed on ecommerce page
When  Login with <username> and <password>
Then "Incorrect email or password." message is displayed on login page

Examples:
   |username           | password  |
   |nandii@mymail.com  |Abc&xyz123 |