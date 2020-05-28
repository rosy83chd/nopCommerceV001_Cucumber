package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage 
{
	WebDriver ldriver;
	WebElement listitem;
	
	public AddCustomerPage (WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
		ldriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	By lnkcustomers_menu=By.xpath(" /html[1]/body[1]/div[3]/div[2]/div[1]/ul[1]/li[4]/a[1]");
	
	By lnkcustomes_menuItem=By.xpath("//li[@class='treeview menu-open']//li[1]//a[1]");
	
	By btnAddNew=By.xpath("//a[@class='btn bg-blue']");
	
	By txtEmail=By.id("Email");
	
	By txtPassword =By.id("Password");
	
	By txtFirstName=By.id("FirstName");
	
	By txtLastName=By.id("LastName");
	
	By rdbtnMale=By.id("Gender_Male");
	
	By rdbtnFemale=By.id("Gender_Female");
	
	By txtDOB= By.id("DateOfBirth");
	
	By txtCoName=By.id("Company");
	
	By chkTaxExempt=By.id("IsTaxExempt");
	
	By chkStoreName=By.name("SelectedNewsletterSubscriptionStoreIds");
	
	By txtCustomerRoles=By.xpath("//li[@class='k-button']");
	
	By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemForumModerators=By.xpath("//li[contains(text(),'Forum Moderators')]");
	By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
	By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
	By lstitemVendors= By.xpath("//li[contains(text(),'Vendors')]");
	
	By drpMgrOfVendor=By.xpath("//select[@id='VendorId']");
	
	By txtAdminContent=By.id("AdminComment");
	
	By btnSave=By.xpath("//button[@name='save']");
	
	By txtmsg=By.xpath("//div[@class='alert alert-success alert-dismissable']");
	
	
	//Action methods
	public String getPageTitle()
	{
		return ldriver.getTitle();
		
	}	
		
	public void clickOnCustomersMenu()
	{
	 ldriver.findElement(lnkcustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem()
	{
	 ldriver.findElement(lnkcustomes_menuItem).click();
	}
	
	public void clickOnAddNew()
	{
     ldriver.findElement(btnAddNew).click();
	}
	
	public void setEmail(String email)
	{
	 ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
	 ldriver.findElement(txtPassword).sendKeys(pwd);
	}
	
	public void setFirstName(String fname)
	{
	 ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
	 ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setGender(String gender)
	{
		if (gender.equals("Male"))
		{
			ldriver.findElement(rdbtnMale).click();
		}
		
		else if(gender.equals("Female"))
		{
		    ldriver.findElement(rdbtnFemale).click();
		}
		
	}
	
	public void setDOB(String dob)
	{
		ldriver.findElement(txtDOB).sendKeys(dob);
	}
	
	public void setCoName(String co_name)
	{
		ldriver.findElement(txtCoName).sendKeys(co_name);
	}
	
	public void setTaxExemptStatus()
	{
		ldriver.findElement(chkTaxExempt).click();
	}
	
	public void setCustomerRoles(String role)
	{
		ldriver.findElement(txtCustomerRoles).click();
		if(role.equals("Administrators"))
		{
			listitem=ldriver.findElement(lstitemAdministrators);
		}
		else if(role.equals("Forum Moderators"))
		{
			listitem=ldriver.findElement(lstitemForumModerators);
		}
		else if(role.equals("Guests"))
		{
			ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
			listitem=ldriver.findElement(lstitemGuests);
		}
		else if(role.equals("Vendors"))
		{
			listitem=ldriver.findElement(lstitemVendors);
		}
		
		
		JavascriptExecutor js=(JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();",listitem);
	}
	
	public void setManagerOfVendor(String value)
	{
		Select drp=new Select(ldriver.findElement(drpMgrOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setAdminContent(String content)
	{
		ldriver.findElement(txtAdminContent).sendKeys(content);
		
	}
	public void clickOnSave()
	{
		ldriver.findElement(btnSave).click();
	}
	
	public boolean verifyConfirmationMsg()
	{
		String msg=ldriver.findElement(txtmsg).getText();
		if(msg.contains("The new customer has been added successfully"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

