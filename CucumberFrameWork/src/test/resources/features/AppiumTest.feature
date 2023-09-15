Feature: Appium Test

  @Appium
  Scenario: User should be able to login with valid credentials
    Given User open web browser and go to URL
    When User enters correct credentials
    Then User should be able to validate the output