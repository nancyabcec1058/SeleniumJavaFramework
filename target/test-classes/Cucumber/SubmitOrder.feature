Feature: Purchase the order from ecommerce website

Background:
Given I landed on ecommerce page

@Regression
Scenario Outline:Positive test to submit the order
Given Login with <username> and <password>
When  I add <productName> to cart
And checkout <productName> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

Examples:
   |username           | password  |productName |
   |nandini@mymail.com  |Abc&xyz123 |ZARA COAT 3 |  