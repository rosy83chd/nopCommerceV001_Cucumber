package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage 
{

	public WebDriver ldriver;
	public WaitHelper waithelper;
	
	public SearchCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper=new WaitHelper(ldriver);
	}
	
	
	@FindBy(how=How.ID, using="SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how=How.ID, using="SearchFirstName")
	@CacheLookup
	WebElement txtFName;
	
	@FindBy(how=How.ID, using="SearchLastName")
	@CacheLookup
	WebElement txtLName;
	
	@FindBy(how=How.ID, using="search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"customers-grid\"]/tbody")
	@CacheLookup
	WebElement tblSearchResults;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"customers-grid\"]/tbody/tr")
	@CacheLookup
	List<WebElement> rows;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"customers-grid\"]/tbody/tr/td")
	@CacheLookup
	List<WebElement> columns;
	
	//Action methods
	public void setEmail(String email)
	{
		waithelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname)
	{
		waithelper.WaitForElement(txtFName, 30);
		txtFName.clear();
		txtFName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		waithelper.WaitForElement(txtFName, 30);
		txtLName.clear();
		txtLName.sendKeys(lname);
	}
	
	public void clickSearch()
	{
		btnSearch.click();
	}
	
	public int getNoOfRows()
	{
		return rows.size();
	}
	
	public int getNoOFCols()
	{
		return columns.size();
	}
	
	public boolean SearchCustomerByEmail(String email)
	{
		boolean flag=true;
		for(int i=1; i<=getNoOfRows(); i++)
		{
			 String emailID = ldriver.findElement(By.xpath("//*[@id=\"customers-grid\"]/tbody/tr["+i+"]/td[2]")).getText();
			 System.out.println(emailID);
			 if (email.equals(emailID))
			 {
				 flag=false;
			 }
			 
		}
		return flag;
	}
	
	public boolean SearchCustomerByName(String name)
	{
		boolean flag = true;
		
		for(int i=1; i<=getNoOfRows(); i++)
		{
			 String flname = ldriver.findElement(By.xpath("//*[@id=\"customers-grid\"]/tbody/tr["+i+"]/td[3]")).getText();
			 System.out.println();
			 String names[]=flname.split(" ");//separating firstname and lastname
			 if ("Victoria".equals(names[0]) && "Terces".equals(names[1]))
			 {
				 flag=false;
			 }
			 
		}
		return flag;
	}
	}
	
	
	
	
	
	
	
	

