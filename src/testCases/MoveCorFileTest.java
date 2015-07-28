package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LogInPage;

public class MoveCorFileTest {
	private WebDriver driver;
	
	@Test
	public void f() {
		
	}
	  
	@BeforeTest
	public void BeforeTest(){
		driver = new FirefoxDriver();
		//login
		LogInPage objLogInPage = new LogInPage(driver);
		objLogInPage.clickLogIn();
		Assert.assertEquals(true, objLogInPage.confirmClickLogIn());
		objLogInPage.logIn();
		Assert.assertEquals(true, objLogInPage.confirmLogIn());
	}
	
	@AfterTest
	public void AfterTest(){
		driver.quit();
	}
	  
}
