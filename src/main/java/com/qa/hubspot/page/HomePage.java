package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	// Locator
	By header = By.xpath("//i18n-string[contains(text(),'Setup Guide')]");
	By accountName = By.xpath("//span[contains(text(),'Erdogmus')]");
	
	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// Methods
	public String getHomePageTitle() {
		return elementUtil.doGetPageTitle();
	}
	
	public String getHomePageHeader() {
		return elementUtil.doGetText(header);
	}
	
	public String getHomeUrl() {
		return elementUtil.doGetPageUrl();
	}
	
	public String getLoggedInUserAccountName() {
		return elementUtil.doGetText(accountName);
	}
	
}
