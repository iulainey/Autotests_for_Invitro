@ui @city
Feature: City change

  Scenario Outline: Change city and verify it is applied
    When I change city to "<city_ru>"
    Then current city should be "<city_ru>"
    And URL should contain "<city_slug>"

    Examples:
      | city_ru | city_slug |
      | Омск    | omsk      |
      | Москва  | moscow    |
      | Казань  | kazan     |