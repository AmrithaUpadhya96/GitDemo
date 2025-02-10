@tag
Feature: Login validation

  @tag1
  Scenario: Login to application successfully
    Given I want to write a step with precondition
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcomes
    And check more outcomes

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I have username <name> and password to login
    When I enter username <name> and password  <password>
    And click on login
    Then I verify the successful login

    Examples: 
      | username  | password |
