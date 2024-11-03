/**
 * 
 */
package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Register customer page object
 */
public class RegisterCustomerPage extends BasePage{

	public RegisterCustomerPage(WebDriver rdriver) 
	{
		super(rdriver);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//h1[normalize-space()='Signing up is easy!']")
	WebElement msgSignUp;
	
	@FindBy(xpath = "//input[@id='customer.firstName']")
	WebElement txtFName;
	
	@FindBy(xpath = "//input[@id='customer.lastName']")
	WebElement txtLName;
	
	@FindBy(xpath = "//input[@id='customer.address.street']")
	WebElement txtAddress;
	
	@FindBy(xpath = "//input[@id='customer.address.city']")
	WebElement txtCity;
	
	@FindBy(xpath = "//input[@id='customer.address.state']")
	WebElement txtState;
	
	@FindBy(xpath = "//input[@id='customer.address.zipCode']")
	WebElement txtZipcode;
	
	@FindBy(xpath = "//input[@id='customer.phoneNumber']")
	WebElement txtPhoneNumber;
	
	@FindBy(xpath = "//input[@id='customer.ssn']")
	WebElement txtSSN;
	
	@FindBy(xpath = "//input[@id='customer.username']")
	WebElement txtUserName;
	
	@FindBy(xpath = "//input[@id='customer.password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='repeatedPassword']")
	WebElement txtCnfPassword;
	
	@FindBy(xpath = "//input[@value='Register']")
	WebElement btnRegister;
	
	@FindBy(xpath = "//tr/td[3]/span[@class='error']")
	List<WebElement> errorMsgLists;
	
	@FindBy(xpath = "//p[contains(text(),'Your account was created successfully. You are now')]")
	WebElement msgAccountCreationSuccess; //lblAccountCreationSuccessMessage
	
	public void clickRegisterlink() 
	{
        lnkRegister.click();
    }
	
	public boolean validateRegPage()
	{
		return msgSignUp.isDisplayed();
	}
	
	public void fillCustomerDetails(String firstName, String lastName, String address, String city, String state, String zipCode, String phoneNumber, 
			String ssn, String username, String password, String confirmPassword) 
	{
		txtFName.sendKeys(firstName);
        txtLName.sendKeys(lastName);
        txtAddress.sendKeys(address);
        txtCity.sendKeys(city);
        txtState.sendKeys(state);
        txtZipcode.sendKeys(zipCode);
        txtPhoneNumber.sendKeys(phoneNumber);
        txtSSN.sendKeys(ssn);
        txtUserName.sendKeys(username);
        txtPassword.sendKeys(password);
        txtCnfPassword.sendKeys(confirmPassword);
    }
	
	public void clickRegisterbtn() 
	{
		btnRegister.click();
    }
	
	public List<String> listErrorMsg()
	{
		List<String> errorMessages = new ArrayList<>();
		
		for(WebElement errorMsg: errorMsgLists)
		{
			errorMessages.add(errorMsg.getText());
		}
		return errorMessages;
	}
	
	public String validateAccountRegisterText()
	{
		return msgAccountCreationSuccess.getText();
	}

}
