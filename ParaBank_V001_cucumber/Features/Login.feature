Feature: Login

@sanity
Scenario: Successful Login With Valid Credentials
	Given User Launch Chrome Browser
	When User Opens URL "https://parabank.parasoft.com/parabank/index.htm"
	And User Enters Email as "test_123@xyz.com" and Password as "Test@123"
	And Click on Login
	Then Page Title should be "ParaBank | Accounts Overview"
	When User Click on Logout link
	Then Page Title should be "ParaBank | Welcome | Online Banking"
	And Close Browser

@regression
Scenario Outline: Login Data Driven
	Given User Launch Chrome Browser
	When User Opens URL "https://parabank.parasoft.com/parabank/index.htm"
	And User Enters Email as "<email>" and Password as "<password>"
	Then Click on Login
	Then Page Title should be "ParaBank | Accounts Overview"
	When User Click on Logout link
	Then Page Title should be "ParaBank | Welcome | Online Banking"
	And Close Browser
	
	Examples:
			|	email	|	password	|
			|	test_123@xyz.com	|	Test@123	|
			|		|	xdfvdfv	|
	