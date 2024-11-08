/**
 * 
 */
package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * After login the Dashboard page contains log out
 */
public class DashboardPage extends BasePage{

	public DashboardPage(WebDriver rdriver) 
	{
		super(rdriver);
	}
	

	@FindBy(xpath = "//a[normalize-space()='Log Out']")
	WebElement lnkLogout;
	
	@FindBy(xpath = "//a[normalize-space()='Open New Account']")
	WebElement lnkOpenNewAccount;
	
	@FindBy(xpath = "//select[@id='type']")
	WebElement drpDownAccountType;
	
	@FindBy(xpath = "//input[@value='Open New Account']")
	WebElement btnOpenNewAccount;
	
	@FindBy(xpath = "//p[contains(text(), 'Congratulations, your account is now open')]")
	WebElement msgCnfAccountOpen;
	
	@FindBy(xpath = "//a[normalize-space()='Transfer Funds']")
	WebElement lnkTransferFunds;
	
	@FindBy(xpath = "//input[@id='amount']")
	WebElement txtAmount;
	
	@FindBy(xpath = "//input[@value='Transfer']")
	WebElement btnTransfer;
	
	@FindBy(xpath = "//h1[normalize-space()='Transfer Complete!']")
	WebElement msgTransferComplete;

	
	public void clickOpenNewAccount()
	{
		lnkOpenNewAccount.click();
	}
	
	public void accountType(String acc_type)
	{
		Select selectType = new Select(drpDownAccountType);
		selectType.selectByVisibleText(acc_type);
	}
	
	public void clickOpenAccountBtn()
	{
		btnOpenNewAccount.click();
	}
	
	public String validateAccountOpen()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));  // Adjust the timeout as needed
	    wait.until(ExpectedConditions.visibilityOf(msgCnfAccountOpen));
	    return msgCnfAccountOpen.getText();
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
	
	public void clickTransferFunds()
	{
		lnkTransferFunds.click();
	}
	
	public void enterAmount(String amount)
	{
		txtAmount.sendKeys(amount);
	}
	
	public void clickTransfer()
	{
		btnTransfer.click();
	}
	
	public String confirmTransfer()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(msgTransferComplete));
		return msgTransferComplete.getText();
	}


}
