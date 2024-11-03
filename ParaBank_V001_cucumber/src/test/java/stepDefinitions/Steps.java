/**
 * 
 */
package stepDefinitions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import pageObjects.RegisterCustomerPage;

/**
 * Step by step implementation for the Login Feature file
 * Page title should be step after logout uses the same with different parameter - Page Title should be {string}
 */
public class Steps extends BaseClass{
	
	//we can use @Before @After method for efficiency
	 @Before
	    public void setup() throws IOException {
			logger = LogManager.getLogger("Steps"); //initialize logger
			
			//Reading config properties file
			configProp = new Properties(); 
			FileReader file = new FileReader("./src/test/resources/config.properties");
			configProp.load(file);
			
			String br = configProp.getProperty("browser");
			switch (br.toLowerCase()) 
			{
			case "chrome":
				WebDriverManager.chromedriver().setup();
		        driver = new ChromeDriver();				
				break;
				
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
				
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
	
			default: 
				driver = null; 
				break;
				//System.out.println("Invalid browser..."); return;
			}
			logger.info("========================= Launchin browser ===============================");
	    }

	    @After
	    public void teardown(Scenario sc) throws IOException 
	    {
	    	if(sc.isFailed())
	    	{
	    		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh_mm_ss").format(new Date());
	    		
	    		String imagePath = "C:\\Users\\deepa\\git\\ParaBank_V001_cucumber\\ParaBank_V001_cucumber\\screenshot\\failedTest"+"_"+timeStamp+".png";
	    		TakesScreenshot scrshot = (TakesScreenshot)driver; //convert webdriver object to TakeScreenshot
	    		
	    		//Call getScreenshotAs method to create image file
	    		File srcFile = scrshot.getScreenshotAs(OutputType.FILE);
	    		
	    		//move image file to the new destination
	    		File destFile = new File(imagePath);
	    		try
	    		{
		    		//copy the file destination
		    		FileUtils.copyFile(srcFile, destFile);
	    		}
	    		catch (IOException e) 
	    		{
	    			e.printStackTrace();
				}
	    	}
	    	
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	
	@Given("User Launch Chrome Browser")
	public void user_launch_chrome_browser() 
	{
//		logger = LogManager.getLogger("Steps"); //initialize logger
//		
//		WebDriverManager.chromedriver().setup();
//		
//		logger.info("******* Launchin browser *************");
//		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
        lp = new LoginPage(driver);
        dp = new DashboardPage(driver);
        regCust = new RegisterCustomerPage(driver);
	}
	
	@When("User Opens URL {string}")
	public void user_opens_url(String url)
	{
		logger.info("******* Launchin URL *************");
		driver.get(url);
	}

	@When("User Enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password)
	{
		logger.info("**** Enter User Name and Password ******");
		lp.setUserName(email);
		lp.setPassword(password); 
	}

	@When("Click on Login")
	public void click_on_login() 
	{
		logger.info("**** Click Login Botton ****");
	    lp.clickLoginbtn();
	    
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) 	
	{
		logger.info("**** Velidate login/register/home page *****");
	    if(driver.getPageSource().contains("Please enter a username and password."))
	    {
	    	driver.close();
	    	Assert.assertTrue(false); // Not from TestNg - From org.junit
	    }
	    else
	    {
	    	Assert.assertEquals(title, driver.getTitle());//void org.junit.Assert.assertEquals (Object expected, Object actual)
	    }
	}

	@When("User Click on Logout link")
	public void user_click_on_logout_link()
	{
		logger.info("**** click logout *****");
	    dp.clickLogout();
	    //for validating page title it uses above step - Page Title should be {string}
	}

	@Then("Close Browser")
	public void close_browser() {
		logger.info("========================= close browser ============================");
	    driver.quit();
	}
	
	//Register customer feature steps..............................
	
	@When("Click on register link")
	public void click_on_register_link() 
	{
		logger.info("**** click on registration link *****");
		regCust.clickRegisterlink();
	    
	}
	
	@Then("User can view customer registration page")
	public void user_can_view_customer_registration_page() 
	{
//		boolean regPageStatus = regCust.validateRegPage();
//		System.out.println("Register Page Display status: "+ regPageStatus);
		
		logger.info("**** Verify Registration page *****");
		Assert.assertTrue("Registration page is not displayed", regCust.validateRegPage());
	}
	
	@When("User enters customer information")
	public void user_enters_customer_information() 
	{
		
		logger.info("**** Enter Registration Information *****");
		regCust.fillCustomerDetails(
				randomString(),					//firstname
		        randomString(),                  // lastName
		        randomString() + " Street",      // address
		        randomString(),                  // city
		        randomString(),                  // state
		        randomZipCode(),                 // zipCode
		        randomPhoneNumber(),             // phoneNumber
		        randomSSN(),                     // ssn
		        randomString() + "@xyz.com",     // username
		        "Test=123",                      // password
		        "Test=123"                       // confirmPassword
		    );
	}
	
	@When("Click on Regsiter")
	public void click_on_regsiter() 
	{
	    regCust.clickRegisterbtn();
	    
	    logger.info("**** Click on Register *****");
	    if(!regCust.listErrorMsg().isEmpty())
		{
			List<String> errors = regCust.listErrorMsg();
			
			for(String error:errors)
			{
				System.out.println("Error Message: "+error);
			}
			driver.close();
			Assert.assertTrue("Error occurred during customer registration", false);
		}
	}
	
	@Then("User can see confirmation message {string}")
	public void user_can_see_confirmation_message(String cnfAccRegMsg) 
	{
		logger.info("**** Registration confirmation *****");
		Assert.assertEquals(cnfAccRegMsg, regCust.validateAccountRegisterText());   
	}
	
	//Open new account..................................
	
	@When("Click on Open New Account link")
	public void click_on_open_new_account_link() 
	{
		logger.info("**** Open New account link *****");
		dp.clickOpenNewAccount(); 
	}
	
	@When("Select the type of account")
	public void select_the_type_of_account() 
	{
		logger.info("**** Selecting type of savings *****");
	    dp.accountType("SAVINGS");; //CHECKING or SAVINGS
	}
	
	@When("click on OPEN NEW ACCOUNT button")
	public void click_on_open_new_account_button() 
	{
		logger.info("** confirm open new account by click OPEN NEW ACCOUNT btn **");
	    dp.clickOpenAccountBtn();
	}
	
	@Then("User can see confirmation message as {string}")
	public void user_can_see_confirmation_message_as(String accountOpened) 
	{
		logger.info("**** Validate new account *****");
		
		Assert.assertEquals(accountOpened, dp.validateAccountOpen());
		
//		String actualMessage = dp.validateAccountOpen();
//	    System.out.println("Confirmation Message: " + actualMessage);  // For debugging
//	    Assert.assertEquals(accountOpened, actualMessage);
	}

	// Transfer Funds
	@When("User click on Transfer funds")
	public void user_click_on_transfer_funds() 
	{
		dp.clickTransferFunds();	    
	}
	
	@When("Enter amount to transfer as {string}")
	public void enter_amount_to_transfer_as(String amount)
	{
		dp.enterAmount(amount);
	}
	@When("click Transfer")
	public void click_transfer() 
	{
	    dp.clickTransfer();
	}
	
	@Then("User can see confirmation message containing {string}")
	public void user_can_see_confirmation_message_containing(String expectedKeyword) 
	{
	    logger.info("**** Validate dynamic confirmation message *****");

	    String actualMessage = dp.confirmTransfer();
	    Assert.assertTrue("The confirmation message does not contain the expected keyword.",
	            actualMessage.contains(expectedKeyword));
	}
}
