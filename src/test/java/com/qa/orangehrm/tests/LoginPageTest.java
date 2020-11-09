package com.qa.orangehrm.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.util.AppConstants;
import com.qa.orangehrm.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 101 : create login features")
@Feature("US - 501 : create tests for login system on HRM using valid and invalid credentials")
public class LoginPageTest {

	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	@BeforeMethod
	@Parameters(value= {"browser"})
	
	public void setUp(String browser)  {
	
		String browserName = null;
		
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		if(browser.equals("null")) {
			browserName = prop.getProperty("browser");
		}else {
			browserName = browser;
		}
		
		
		
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
	@Test(priority=1, description="verify page title method", enabled=true)
	@Description("verify login page title")
	@Severity(SeverityLevel.NORMAL)
	
	public void verifyLoginPageTitle() {
		
		log.info("starting title method-----------> verify login page");
		
		String title = loginPage.getPageTitle();
		System.out.println("login page title is "+ title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		
		log.info("ending title method--------------> verify login page");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	
	
	
	
	@Test(priority=2, description="verify forget password link is displayed", enabled=true)
	@Description("verify login page title")
	@Severity(SeverityLevel.NORMAL)
	
	public void verifyForgetPassLink() {
		
		log.info("starting ForgetPass link  method-----------> verify forget pass");
		
		Assert.assertTrue(loginPage.checkForgetPassLink());
		
		log.info("ending forget pass method--------------> verify forget pass link ");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	
	
	
	@Test(priority=3, description="login system with valid username and password", enabled=true)
	@Description("verify login page title")
	@Severity(SeverityLevel.CRITICAL)
	
	public void loginTest() {
		
		log.info("starting login test  method-----------> verify login test");
	//HomePage homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		HomePage homePage = loginPage.doLogin(userCred);
		
	String accountName= homePage.getLoggedInUserAccountName();
	Assert.assertTrue(accountName.contains(prop.getProperty("accountname")));
	//Assert.assertEquals(accountName, prop.getProperty("accountname")); does'nt work since it is accountname is dynamic component
	log.info("ending login test method--------------> verify login test");
	log.warn("some warning");
	log.error("some error");
	log.fatal("fatal error");	
	}
	
	/*
	 * So this method below has annoted with @DataProvider. This means  the username and password credentials will be read by this method
	 * and passed to this particular method of mine. login_InvalidTestCase() this method.
	 */
	
	
	@DataProvider
	public Object [][]getLoginInvalidData(){
		
		Object data [][] = {  {"Hasan", "test123"},
				              {"Refia", "321test"}, 
				              {"Richard", "ret123"},
				              {"Zulqar", "ewr123"} };
				           
		
		
		return data;
	}
	
	
	@Test(priority=4, dataProvider= "getLoginInvalidData", enabled= true)
	@Description("verify login page title")
	@Severity(SeverityLevel.MINOR)
	public void login_InvalidTestCase(String usern, String pwd) {
		
		userCred.setAppUsername(usern);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}

	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
