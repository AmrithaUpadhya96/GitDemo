@tag
Feature: Verify product and submit order for the product

 
  Background: 
  Given I land on Ecommerce application
  
@Regression
  Scenario Outline: verify and submit order for a product
     Given I am on Ecommerce application login page
     When I click on login by giving username <username> and password <password>
     And I add the product <productName> in cart and verify the same in cart before checkout
     And I checkout the product
    Then I get "THANKYOU FOR THE ORDER." order confirmation message.

    Examples: 
      | username                  | password       | productName |
      |amritha.upadhya@gmail.com  | PizzaPasta@10  |QWERTY|
