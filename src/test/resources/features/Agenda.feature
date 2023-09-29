
Feature: Agenda menu functionality

  @Agenda1
  Scenario: Ensure events created by admin are visible to User
    Given the admin logs in with valid credentials
    When the admin clicks on Agenda
    And the admin adds a new event on Agenda
    And the admin searches and asserts for the newly created event
    And the admin edit event and again search and assert editted event
    And the admin logs out
    Then the User logs in with valid credentials
    And the User asserts that the new event is visible on the calendar
    And the User logs out
    Then the admin logs in again and go on Agenda
    And the admin deletes last added events

    @Agenda2
  Scenario: Ensure events created by user are not visible to Admin
    Given the User logs in with valid credentials
    When the User adds an event on Calender
    And the User logs out
    Then the admin logs in with valid credentials
    And the admin clicks on Agenda
    And the admin searches for user event on Agenda and assert not seen by admin