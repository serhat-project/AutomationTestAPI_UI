@remember
Feature: Remember Me Cookie Should Be Set

  Scenario: User logs in with remember me checked
    Given the user is on the login page
    When the user enters their username as "skilic@mysoly.nl" and password as "skilic@mysoly.nl"
    And the user checks the remember me checkbox
    And the user clicks the login button
    Then the user should be logged in
#    And the remember me cookie should be set
    Then the user clicks the logout button
    Then the user close the current window
    And the user open the login page
    When the user come on username "skilic@mysoly.nl" should be seen