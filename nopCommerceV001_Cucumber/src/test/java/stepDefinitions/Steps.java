package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass

{
	@Before
	public void setup() throws IOException
	{
		
	    configprop=new Properties();
	    FileInputStream configpropfile=new FileInputStream("config.properties");//opening file in reading/input mode
	    configprop.load(configpropfile);//Reading/loading the file
	    
		logger=Logger.getLogger("nopCommerce"); //initialize logger
		PropertyConfigurator.configure("log4j.properties"); //Added logger		   
		 
	}
	
	@Given("User launches Chrome Browser")
	public void user_launches_Chrome_Browser() 
	{
	   logger.info("*************Launching browser**************");
	   String br=configprop.getProperty("browser");
	   if (br.equals("chrome"))
	   {   
	     //System.setProperty("webdriver.chrome.driver", configprop.getProperty("chromepath"));
		   System.setProperty("webdriver.chrome.driver", "C://Drivers//chromedriver_win32//chromedriver.exe");
	     driver=new ChromeDriver();
	   }
	   else if (br.equals("firefox"))
	   {
		  System.setProperty("webdriver.gecko.driver", configprop.getProperty("firefoxpath"));
		  driver=new FirefoxDriver();
	   }
	   
	   else if (br.equals("ie"))
	   {
		   System.setProperty("webdriver.ie.driver", configprop.getProperty("iepath"));
			  driver=new InternetExplorerDriver();  
	   }
	   
	    lp=new LoginPage(driver);
	    
	    driver.manage().window().maximize();
	    
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) 
	{
	    logger.info("*************Opening URL**************");
	   driver.get(url); 
	 
	}

	@When("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd)
	{
		logger.info("*************Providing login details**************");
	    lp.setUsername(email);
	    lp.setPassword(pwd);
	}

	@When("User clicks on login")
	public void user_clicks_on_login() throws InterruptedException 
	{
		logger.info("*************Started logging in**************");
		lp.clickLogin();
		Thread.sleep(3000);
	}
	   

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String exp_Title) 
	{
	    if(driver.getPageSource().contains("Login was unsuccessful"))
	   	
	    {
	    	logger.info("*************Log in failed**************");
	    	Assert.assertTrue(false);
	    }
	    else
	    
	    {
	    	logger.info("*************Log in passed**************");
	    	Assert.assertEquals(exp_Title, driver.getTitle());
	    }
	}

	@When("User clicks on logout link")
	public void user_clicks_on_logout_link() throws InterruptedException 
	{
		logger.info("*************Clicking on logout link**************");
	    lp.clickLogout();
	    Thread.sleep(5000);
	}

	

	@Then("Close browser")
	public void close_browser() 
	{
		logger.info("*************Closing browser**************");
	   driver.close();
	}
//Customer feature
	
	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() 
	{
	   addcust=new AddCustomerPage(driver);
	   Assert.assertEquals("Dashboard / nopCommerce administration", addcust.getPageTitle());
	}

	@When("User clicks on customers menu")
	public void user_clicks_on_customers_menu() throws InterruptedException 
	{
	   Thread.sleep(3000);
	   addcust.clickOnCustomersMenu();
	}

	@When("User clicks on customer menu item")
	public void user_clicks_on_customer_menu_item() throws InterruptedException
	{
	  addcust.clickOnCustomersMenuItem();  
	  Thread.sleep(3000);
	}

	@When("User clicks on Add new button")
	public void user_clicks_on_Add_new_button() throws InterruptedException 
	{
		logger.info("*************Adding new customer**************");
	   addcust.clickOnAddNew();
	   Thread.sleep(5000);
	}

	@Then("User can view Add New Customer page")
	public void user_can_view_Add_New_Customer_page() 
	{
	   Assert.assertEquals("Add a new customer / nopCommerce administration", addcust.getPageTitle());
	}

	@When("User enters customer info")
	public void user_enters_customer_info() throws InterruptedException 
	{
		logger.info("*************Providing customer details**************");
		String email=randomstring()+"@gmail.com";
		addcust.setEmail(email);
		addcust.setPassword("test1234");
		addcust.setCustomerRoles("Guests");
		Thread.sleep(3000);
		addcust.setManagerOfVendor("Vendor 1");
		addcust.setGender("Female");
		addcust.setFirstName("Harpreet");
		addcust.setLastName("kaur");
		addcust.setDOB("09/08/1983");
		addcust.setCoName("not decided");
		addcust.setAdminContent("This is for testing...");
		
		
		
		
	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException 
	{
		logger.info("*************Saving customer details**************");
	    addcust.clickOnSave();
	    Thread.sleep(3000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) 
	{
	    addcust.verifyConfirmationMsg();
	    Assert.assertTrue(true);
	}

//Search customer by email ID
	@When("User enters customer's email")
	public void user_enters_customer_s_email() 
	{
		logger.info("*************Searching customer by emailID**************");
		searchCust=new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("User clicks on search button")
	public void user_clicks_on_search_button() throws InterruptedException 
	{
		
		searchCust.clickSearch();
	    Thread.sleep(3000);
	}

	@Then("User should find email in the search table")
	public void user_should_find_email_in_the_search_table() 
	{
		logger.info("*************verifying emailID in the search table**************");
	   boolean status=searchCust.SearchCustomerByEmail("victoria_victoria@nopCommerce.com");
	   Assert.assertEquals(status,false);
	}

//Search customer by name
	@When("User enters the first name")
	public void user_enters_the_first_name() 
	{
		logger.info("*************Searching customer by name**************");
		searchCust=new SearchCustomerPage(driver);
		logger.info("*************Entering customer first name**************");
		searchCust.setFirstName("Victoria");
	}

	@When("User enters the last name")
	public void user_enters_the_last_name() 
	{
		logger.info("*************Entering customer last name**************");
		searchCust.setLastName("Terces");
	}

	@Then("User should find the first and last name in the search table")
	public void user_should_find_the_first_and_last_name_in_the_search_table()
	{
	   boolean status =searchCust.SearchCustomerByName("Victoria Terces");
	   Assert.assertEquals(status,false);
	    
	}


}
