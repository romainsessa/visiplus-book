Feature: Retrieve all books
	As a user
  I want to receive all books
  So that I know the books on this library
  
  Scenario: Retrieve all books
    Given the client calls book endpoint
    When the response is provided
    Then the client receives a list with 4 elements