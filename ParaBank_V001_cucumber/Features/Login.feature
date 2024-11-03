Feature: Login

@sanity1
Scenario: Successful Login With Valid Credentials
	Given User Launch Chrome Browser
	When User Opens URL "https://parabank.parasoft.com/parabank/index.htm"
	And User Enters Email as "asd@asd.com" and Password as "asd"
	And Click on Login
	Then Page Title should be "ParaBank | Accounts Overview"
	When User Click on Logout link
	Then Page Title should be "ParaBank | Welcome | Online Banking"
	And Close Browser

@sanity
Scenario: Transfer Funds
	Given User Launch Chrome Browser
	When User Opens URL "https://parabank.parasoft.com/parabank/index.htm"
	And User Enters Email as "asd@asd.com" and Password as "asd"
	And Click on Login
	Then Page Title should be "ParaBank | Accounts Overview"
	When User click on Transfer funds
	And Enter amount to transfer as "100"
	And click Transfer
	Then User can see confirmation message containing "Transfer Complete!" 
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
			|	sqwe@qwe.com	|	Test@123	|
			|		|	xdfvdfv	|
	