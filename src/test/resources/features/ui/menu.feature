@ui
Feature: Radiology menu click-through
  @all_menu
  Scenario: Click all menu items
    When I click all menu items

  @xpath_mrt
  Scenario: Click all menu items excluding MRT body by XPath
    When I click all menu items excluding MRT body by XPath

  @collection_mrt
  Scenario: Click all menu items excluding MRT body from collection
    When I click all menu items excluding MRT body from collection

  @loop_mrt
  Scenario: Click all menu items excluding MRT body in loop
    When I click all menu items excluding MRT body in loop