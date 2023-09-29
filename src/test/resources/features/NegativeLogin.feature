@negativeLogin
Feature: Login
  As a user
  I want to be able to login to my account
  So that I can access my personal information

  Scenario: Login with invalid credentials
    Given I am on the login page
    When I enter invalid username "testuser" and invalid password "incorrectpassword"
    Then I should see an error message
    And I should remain on the login page