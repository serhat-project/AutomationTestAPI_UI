@lang2
Feature: Language Menu

  Scenario: Verify correct display of selected language on the dashboard menu

    Given the user logs in  successfully and is on the website dashboard
    When the user clicks on language menu
    And the user clicks language by one by from the language menu
    Then the dashboard menu should be seen as selected language



