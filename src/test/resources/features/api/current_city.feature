@api
Feature: Current city API validation

  Scenario Outline: Verify current city by CODE parameter
    When I request current city with code "<code>"
    Then response status should be 200
    And response json field "code" should match "<expected>"

    Examples:
      | code    | expected |
      | moscow  | null     |
      | london  | null     |
      | bajmak  | null     |