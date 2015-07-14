package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class NewFolderTest {
	private WebDriver driver;
	
	@Test
	public void f() {
	}
	@BeforeMethod
	public void beforeMethod() {
	}
	
	@AfterMethod
	public void afterMethod() {
	}
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		String baseUrl = "http://115.29.169.34/auth/login";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		
	}
	
	@AfterTest
	public void afterTest() {
	}

}
