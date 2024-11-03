/**
 * 
 */
package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import pageObjects.RegisterCustomerPage;

/**
 * Common functions used in stepDefinition class
 */
public class BaseClass {
	
	WebDriver driver;
	public static Logger logger;
	public Properties configProp;
	
	public LoginPage lp;
	public DashboardPage dp;
	public RegisterCustomerPage regCust;
	
	//Generating Random Strings for Registering customers;
	public static String randomString()
	{
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return generatedString1;
	}
	
//	//Generates random User Name
//	public static String randomUName()
//	{
//		String generatedUName = RandomStringUtils.randomAlphanumeric(7);
//		return generatedUName+"@xyz.com";
//	}
	
	// Generates a random numeric string for zip code
	public static String randomZipCode() 
	{
	    return RandomStringUtils.randomNumeric(5);
	}

	// Generates a random numeric string for phone number
	public static String randomPhoneNumber() 
	{
	    return RandomStringUtils.randomNumeric(10);
	}

	// Generates a random numeric string for SSN
	public static String randomSSN() 
	{
	    return RandomStringUtils.randomNumeric(9);
	}

}
