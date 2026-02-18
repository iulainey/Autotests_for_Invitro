@ui @results
Feature: Results analyzers validation

  Scenario: Validation and red highlight for empty fields, then highlight removed after filling
    Given I open Invitro home page
    When I open Results page
    And I click Find results
    Then validation message should be visible
    And validation message text should be "Поля Код ИНЗ Дата рождения Фамилия обязательны для заполнения"
    And all fields should be highlighted red

    When I fill order number "231231231"
    Then order number field should not be highlighted

    When I fill birthday "11.12.2000"
    Then birthday field should not be highlighted

    When I fill last name "тест"
    Then last name field should not be highlighted
    And validation message should be absent