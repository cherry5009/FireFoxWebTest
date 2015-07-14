package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import pages.ActionList;
import pages.LogInPage;
import pages.NewFolderPage;

public class DeleteTest {
	private WebDriver driver;
	private NewFolderPage objNewFolderPage;
	
	@Test
	public void deleteFolder() {
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.delete();
		Assert.assertEquals(true, objActionList.confirmDelete());
	}
	
	public void deleteFile() {
		ActionList objActionList = new ActionList(driver, "test.doc");
		objActionList.delete();
		Assert.assertEquals(true, objActionList.confirmDelete());
	}
	
	@BeforeMethod
	public void beforeMethod() {
		objNewFolderPage = new NewFolderPage(driver, "testFolder");
		objNewFolderPage.newFolder();
		objNewFolderPage.choosePersonalFile();
		objNewFolderPage.createPersonalFile();
		objNewFolderPage.confirmCreate("testFolder");
	}
	
	@AfterMethod
	public void afterMethod() {
	}
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		String baseUrl="http://115.29.169.34/auth/login";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		//login
		LogInPage objLogInPage = new LogInPage(driver);
		objLogInPage.setEmail("jiong09test@163.com");
		objLogInPage.setPassword("jiong0321");
		objLogInPage.clickLogIn();
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
}

}
