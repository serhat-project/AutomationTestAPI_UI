@lang3
Feature: Language Menu

  Scenario: Verify correct display of selected language on the dashboard menu

    Given the user log in  successfully and is on the website dashboard
    When the user click on language menu
    And the user click language by one by from the language menu
    Then all side 35 menu should be seen in selected language