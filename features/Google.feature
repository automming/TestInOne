@Google 
Feature: Google 

Scenario: User types selenium in Google 
	Given user loads browser
	When user opens Google site
	And types "Selenium" in search
	Then user should view results