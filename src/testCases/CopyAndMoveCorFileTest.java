package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LogInPage;

public class CopyAndMoveCorFileTest {
	private WebDriver driver;
	private LogInPage objLogInPage;
	
	@Test
	public void f() {
		
	}
	  
	@BeforeTest
	public void BeforeTest(){
		//login
		objLogInPage = new LogInPage(driver);
		objLogInPage.setEmail("jiong09test@163.com");
		objLogInPage.setPassword("jiong0321");
		objLogInPage.clickLogIn(); 
	}
	
	@AfterTest
	public void AfterTest(){
		
	}
	  
	@BeforeClass
	public void BeforeClass(){
		 driver = new FirefoxDriver();
		 String baseUrl = "http://115.29.169.34/auth/login";
		 driver.get(baseUrl);
		 driver.manage().window().maximize();
	}
	
	@AfterClass
	public void AfterClass(){
		driver.quit();
	}
}
