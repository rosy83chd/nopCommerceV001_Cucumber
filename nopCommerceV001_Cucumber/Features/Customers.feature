Feature: Customers

Background: Below are the common steps for all scenarios 
	Given User launches Chrome Browser 
	When User opens URL "https://admin-demo.nopcommerce.com/login" 
	And User enters email as "admin@yourstore.com" and password as "admin" 
	And User clicks on login 
	Then User can view Dashboard 

@sanity	
Scenario: Add a new customer 
	When User clicks on customers menu 
	And User clicks on customer menu item 
	And User clicks on Add new button 
	Then User can view Add New Customer page 
	When User enters customer info 
	And click on Save button 
	Then User can view confirmation message "The new customer has been added successfully" 
	And Close browser 

@regression	
Scenario: Search a customer by emailID 
	When User clicks on customers menu 
	And User clicks on customer menu item 
	And User enters customer's email 
	And User clicks on search button 
	Then User should find email in the search table 
	And Close browser 

@regression	
Scenario: Search a customer by name 

	When User clicks on customers menu 
	And User clicks on customer menu item 
	And User enters the first name 
	And User enters the last name 
	And User clicks on search button 
	Then User should find the first and last name in the search table 
	And Close browser