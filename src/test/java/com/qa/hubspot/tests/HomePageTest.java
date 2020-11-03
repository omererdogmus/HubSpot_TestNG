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

public class HomePageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
		Thread.sleep(25000);
	}
	
	@Test(priority = 1, description = "verify home page title")
	public void verifyHomePageTitle() {
		String title = homePage.getHomePageTitle();
		System.out.println("homepage title is : " + title);
		Assert.assertEquals(title, "Account Setup | HubSpot");
	}
	
	@Test(priority = 2, description = "verify homepage header")
	public void verifyHomePageHeader() {
		String header = homePage.getHomePageHeader();
		System.out.println("homepage header is : " + header);
		Assert.assertEquals(header, "Setup Guide");
	}
	
	@Test(priority = 3, description = "verify account name method")
	public void verifyLoggedInUserTest() {
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("logged in account name is : " + accountName);
		Assert.assertEquals(accountName, "Erdogmus");
	}
	
	@Test(priority = 4, description = "verify page URL")
	public void verifyAuthentication() {
		String url = homePage.getHomeUrl();
		System.out.println("page url is : " + url);
		Assert.assertTrue(url.endsWith("https://app.hubspot.com/getting-started/8546472/v3/sales_leader_inexperienced_gob294"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
