@logout
Feature: Logout Scenario

  Scenario: User logs out after closing notification
    Given User is on the login page
    When User enters valid username "skilic@mysoly.nl" and valid password "skilic@mysoly.nl"
    And User logs in
    And User closes the notification
    When User selects the logout menu
    And User clicks on the logout button
    Then User should be logged out