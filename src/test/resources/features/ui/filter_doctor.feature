@ui @doctors
Feature: Doctors page pediatric filter

  Scenario: Pediatric doctors filter persists after refresh
    Given I open doctors page
    When I enable pediatric doctors filter
    Then all doctor cards should have child doctor badge

    When I refresh the page
    Then pediatric doctors checkbox should be selected
    And all doctor cards should have child doctor badge