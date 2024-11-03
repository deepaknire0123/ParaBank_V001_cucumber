/**
 * 
 */
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


/**
 * Log into the e-commerce admin page
 */
public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver rdriver) 
	{
		super(rdriver); //rdriver scope is only within the constructor
	}
	
	@FindBy(xpath = "//input[@name='username']")
	WebElement txtUserName;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Log In']")
	WebElement btnLogin;
	
	public void setUserName(String uName)
	{
		txtUserName.clear();
		txtUserName.sendKeys(uName);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLoginbtn()
	{
		btnLogin.click();
	}
	

}
