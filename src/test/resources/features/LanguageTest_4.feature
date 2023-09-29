@lang4
Feature: Language Menu
Background: Login
  Given user logs in  successfully and is on the website dashboard

  Scenario Outline: Verify correct display of selected language on the dashboard menu


    When  user clicks on language menu
    When  user opens the Excel file "ExcelA.xlsx"
    Then the expected values for the all "<language>" should be loaded from the Excel file <column_num> and assert with actual side menus

    Examples:
      | language   | column_num |
      | English    | 1          |
      | Türkçe     | 2          |
      | Nederlands | 3          |
      | Deutsch    | 4          |
      | Français   | 5          |
      | Española   | 6          |
      | Polski     | 7          |
      | Yкраїнська | 8          |
