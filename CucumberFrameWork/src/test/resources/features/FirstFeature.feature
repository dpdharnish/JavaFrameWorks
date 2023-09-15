@FullTest
Feature: Web Login

  @FirstTest
  Scenario: User should be able to login with valid credentials
    Given User is on the Login page
    When User enter the valid credentials
    And hits submit
    Then the user should be in login page

  @SecondTest
  Scenario Outline: User should be able to login with valid credentials
    Given User is on the Login page
    When User enter "<id>" and "<password>"
    And hits submit
    Then the user should be in login page
    @Set1
    Examples:
      | id    | password  |
      | HS123 | dharnish  |
      | HS124 | sathvukha |

    @Set2
    Examples:
      | id    | password  |
      | HS125 | anusha    |
      | HS126 | bharath   |

