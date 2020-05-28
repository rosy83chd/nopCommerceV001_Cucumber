Feature: Login

@sanity
Scenario: Successful login with valid credentials
 Given User launches Chrome Browser
 When User opens URL "https://admin-demo.nopcommerce.com/login"
 And User enters email as "admin@yourstore.com" and password as "admin"
 And User clicks on login
 Then Page Title should be "Dashboard / nopCommerce administration"
 When User clicks on logout link
 Then Page Title should be "Your store. Login"
 And Close browser
 
 @regression
 Scenario Outline: Login data driven
 Given User launches Chrome Browser
 When User opens URL "https://admin-demo.nopcommerce.com/login"
 And User enters email as "<email>" and password as "<password>"
 And User clicks on login
 Then Page Title should be "Dashboard / nopCommerce administration"
 When User clicks on logout link
 Then Page Title should be "Your store. Login"
 And Close browser
 
 Examples:
 |	email	|	password	|
 |	admin@yourstore.com	|	admin	|
 |	admin@yourstore.com	|	admin	|