@language
Feature: Language Selection
  As a user
  I want to be able to choose from 10 different languages
  So that I can view the portal in my preferred language

  @UI
  Scenario Outline: Selecting different languages from the language section
    Given I am on the portal's website and have logged in successfully
    When I click on the language section
    And I click "<language>" from the language options
    Then the dashboard menu should be seen as "<dashboardMenu>"

    Examples:
      | language        | dashboardMenu          |
      | English (US)         | My Dashboard           |
      | Türkçe          | Gösterge Tablom        |
      | Nederlands      | Mijn dashboard         |
      | Deutsch         | Mein Dashboard         |
      | Français        | Mon tableau de bord     |
      | Española        | Mi panel de control    |
      | Polski          | Mój pulpit nawigacyjny |
      | Yкраїнська      | Моя інформаційна панель |
