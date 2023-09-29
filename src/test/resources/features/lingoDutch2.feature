@lingo2
Feature: LingoDutch.nl Grammar and Spelling Correction Test

  Scenario: Verify grammar and spelling correction of Dutch sentences2
    Given I am logged in to url with valid credentials
    When I have an url with a list of Dutch sentences as a table with the columns input and output
    Then I paste each sentence from the Url input colomn into the text Area and the corrected sentence should be the same as the result in the Url output column after press query button