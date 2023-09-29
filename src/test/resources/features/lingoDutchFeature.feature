@lingo
Feature: LingoDutch.nl Grammar and Spelling Correction Test

  Scenario: Verify grammar and spelling correction of Dutch sentences
    Given I am logged in to "https://lingodutch.nl/" with valid credentials
    And I have an excel file with a list of Dutch sentences
    Then I paste each sentence from the excel input column into text area the corrected sentence should be the same as the result in the excel output