package com.qa.orangehrm.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	public ChromeOptions co;
	public FirefoxOptions fo;
	public Properties prop;
	
	
	// Constructor 
	
	public OptionsManager(Properties prop){ // because i will use config to manipulate my chrom and firefoxOptions
		this.prop= prop;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ChromeOptions getChromOptions(){
		co = new ChromeOptions();
		if(prop.getProperty("incognito").equals("yes")) co.addArguments("--incognito");
		if(prop.getProperty("headless").equals("yes")) co.addArguments("--headless");
		
		//return co;
		return new ChromeOptions();
			
		
	}
	
	/**
	 * 
	 * @return
	 */
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(prop.getProperty("headless").equals("yes")) fo.addArguments("--headless");
		
		return fo;
		// Incognito mode in not available in fire fox
	}
	
	
	
	
}
