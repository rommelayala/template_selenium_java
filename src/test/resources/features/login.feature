Feature: Login Functionality

  @test1
  Scenario: Successful load page and wait 6 seconds
    Given I go to URL using properties file
    And click on the button with 'css:a[data-test="nav-sign-in"]'
    And type 'admin@practicesoftwaretesting.com' on the element with 'id:email'
    And type 'welcome01' on the element with 'id:password'
    And click on the button with 'css:input[data-test="login-submit"]'

#  Scenario: Successful load page and wait 5 seconds
#    Given I go to URL using properties file
#    And I pause 5 seconds
#
#  Scenario: Successful load page and wait 4 seconds
#    Given I go to URL using properties file
#    And I pause 4 seconds
#
#  @test2
#  Scenario: Successful load page and wait 3 seconds
#    Given I go to URL using properties file
#    And I pause 3 seconds
#
#  Scenario: Successful load page and wait 2 seconds
#    Given I go to URL using properties file
#    And I pause 2 seconds
#
#  Scenario: Successful load page and wait 1 seconds
#    Given I go to URL using properties file
#    And I pause 1 seconds
#
#  @test3
#  Scenario: Successful load page and no wait
#    Given I go to URL using properties file
