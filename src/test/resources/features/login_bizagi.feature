Feature: TP+TC6 SMO New Trip Services � 1 Kit Using 3rd Party

  @test_login_bizagi
  Scenario Outline: Create a New TP+API Request SMO New Trip Services � 1 Kit Using 3rd Party and Submit
    Given I go to this URL "https://test-processes-travelport.bizagi.com/processes-test/"
  # Login
    And click on the element with 'id:btn-login'
    And click on the element with 'id:user'
    And type '<Username>' on the element with 'id:user1'
    And type '<Password>' on the element with 'id:password'
    And click on the element with 'id:btn-login'

    Examples:
      | Username | Password     |
      | admon    | Travelport1! |
