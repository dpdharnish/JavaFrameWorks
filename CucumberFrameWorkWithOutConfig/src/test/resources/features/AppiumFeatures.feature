Feature: Youtube Test

  @Appium
  Scenario Outline: Get Video Time User Flow
    Given Launch the app "<udid>" and "<AppiumURL>"
    When Search for movie
    And Choose the top option
    Then Wait for the video Load
    Examples:
      | udid | AppiumURL |
      | RFCRC08KCAX | https://dev-in-blr-0.headspin.io:3012/v0/150f14a11db946ffb9505e3175ae9d95/wd/hub |