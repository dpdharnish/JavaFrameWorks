Feature: Youtube Test

  @Appium
  Scenario: Get Video Time User Flow
    Given Launch the app
    When Search for movie
    And Choose the top option
    Then Wait for the video Load