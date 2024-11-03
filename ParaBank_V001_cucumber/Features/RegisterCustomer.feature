Feature: Register Customer

Background: Steps common for all scenrios
	Given User Launch Chrome Browser
	When User Opens URL "https://parabank.parasoft.com/parabank/index.htm"
	And Click on register link
	Then User can view customer registration page

@sanity
Scenario: Register a new customer
	When User enters customer information
	And Click on Regsiter
	Then User can see confirmation message "Your account was created successfully. You are now logged in."
	When User Click on Logout link
	Then Page Title should be "ParaBank | Welcome | Online Banking"
	And Close Browser

@regression
Scenario: Open New Account
	When User enters customer information
	And Click on Regsiter
	Then User can see confirmation message "Your account was created successfully. You are now logged in."
	When Click on Open New Account link
	And Select the type of account
	And click on OPEN NEW ACCOUNT button
	Then User can see confirmation message as "Congratulations, your account is now open."
	When User Click on Logout link
	Then Page Title should be "ParaBank | Welcome | Online Banking"
	And Close Browser
	