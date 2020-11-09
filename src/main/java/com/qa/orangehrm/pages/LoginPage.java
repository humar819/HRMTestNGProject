package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.util.Credentials;
import com.qa.orangehrm.util.ElementUtil;

public class LoginPage extends BasePage {
	
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By username = By.id("txtUsername");
	By password = By.id("txtPassword");
	By btnLogin = By.id("btnLogin");
	By forgetPass = By.xpath("//a[contains(text(),'Forgot your password?')]");
	By loginErrorText = By.id("spanMessage");
		
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver); //2
	}
	
	//Methods
	
	
	// get title
	public String getPageTitle() {
		//elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
		
	}
	
	
	//isDisplayed
	public boolean checkForgetPassLink() {
		//elementUtil.waitForElementPresent(forgetPass);
		return elementUtil.doIsDisplayed(forgetPass);
		
	}
	
	//doLogin
    public HomePage doLogin(Credentials userCred) {
		
		elementUtil.doSendKeys(username, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(btnLogin);
       
		
		return new HomePage(driver);
	}
    
    
    
    public boolean checkLoginErrorMessage() {
		return elementUtil.doIsDisplayed(loginErrorText);
    	
	}

	
	
	

}
