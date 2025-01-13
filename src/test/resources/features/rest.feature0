@rest
Feature: Rest test Functionality

  Scenario: Successful load page and wait 6 seconds
    Given I prepare 'GET' request to 'https://rickandmortyapi.com/api/character/768'
    When I send the request
    Then the response body is '200'
    And the response body contains parameter 'location.url'
    And the response body parameter 'location.url' has exactly value 'https://rickandmortyapi.com/api/location/20'
    And the response body parameter 'location.url' contains value 'api/location/20'
    And the response body parameter 'created' type is 'date'
    And the response body parameter 'id' type is 'int'
    And the response body schema matches with 'schemas/character-schema.json'