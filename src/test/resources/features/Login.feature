Feature: login functionality positive

  Scenario Outline: login
    Given user on Login page
    When  user fill the login form with the following data
      | username | <username> |
      | password | <password> |
    And   user clicks Login button
    Then  login should be successfull

    Examples:
      | username           | password |
      | skilic@mysoly.nl  | skilic@mysoly.nl |
      | fatihstudent1@yopmail.com | E8BK3krNBW |




