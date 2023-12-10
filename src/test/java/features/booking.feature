Feature: Book a room

@book
Scenario: Option to book a room is available on the page
Given I am on the home page
When I browse through the page
Then I have the option to book a room


  @book
  Scenario: Book a room for 2 nights successfully
    Given I am on the home page and button to book a room is visible
    When  I click the book button
    And the calendar today is displayed
    And I fill in the information for booking with:
      | Field        | Value                |
      | First Name   | Clint                |
      | Last Name    | Eastwood             |
      | Email        | eastwood@gmail.com   |
      | Phone        | +32 444 44 44 44     |
    And I select a stay period of two nights
    And I see chosen days
    And I click on the Book button
    Then I should validate booking dates
