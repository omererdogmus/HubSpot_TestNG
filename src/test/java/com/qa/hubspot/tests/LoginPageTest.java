package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Credentials;

public class LoginPageTest extends BasePage{

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;

	@BeforeMethod
	public void setUp() {

		// launch browser
		// launch url
		// config prop
		// basePage
		
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1, description = "verify page title method")
	public void verifyLoginPageTitle() throws InterruptedException {
		Thread.sleep(5000);
		String title = loginPage.getPageTitle();
		System.out.println("Login page title is : " + title);
		Assert.assertEquals(title, "HubSpot Login");
	}
	
	@Test(priority = 2, description = "verify signup link is displayed")
	public void verifySignUpLink() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}

	@Test(priority = 3, description = "login system with valid credentials")
	public void loginTest() {
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("logged in account name is : " + accountName);
		Assert.assertEquals(accountName, "Erdogmus");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
