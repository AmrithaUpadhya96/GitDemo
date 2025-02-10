
@Smoke
Feature: Ecommerce login feature validation


  Background: 
  Given I land on Ecommerce application

  @tag1
  Scenario Outline: Failed to login to Ecommerce application
    Given I am on Ecommerce application login page
    When I click on login by giving username <username> and password <password>
    Then I get "Incorrect email or password." message on the login page.

    Examples: 
      | username                  | password       |
      | amritha.abdn@gmail.com    | Pizzoooasta@10 |
      | amritha.upadhya@gmail.com | Pizzoooasta@10 |

  @tag2
  Scenario Outline: successful login to Ecommerce application
    Given I am on Ecommerce application login page
    When I click on login by giving username <username> and password <password>
    Then I should land on home page.
    
    Examples: 
      | username                  | password       |
      |amritha.upadhya@gmail.com  | PizzaPasta@10  |