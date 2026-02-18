@ui @cart
Feature: Cart price validation

  Scenario: Remember analysis price, add to cart, compare price in cart
    Given I open analyses for doctors page
    When I remember analysis price and add it to cart
    And I go to cart
    Then product price in cart should equal remembered price