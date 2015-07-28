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
		objActionList.clickDelete();
		Assert.assertEquals(true, objActionList.confirmDelete());
	}
	
	public void deleteFile() {
		ActionList objActionList = new ActionList(driver, "test.doc");
		objActionList.clickDelete();
		Assert.assertEquals(true, objActionList.confirmDelete());
	}
	
	@BeforeMethod
	public void beforeMethod() {
		objNewFolderPage = new NewFolderPage(driver, "testFolder");
		objNewFolderPage.newFolder();
		objNewFolderPage.createPersonalFile();
		objNewFolderPage.confirmCreate("testFolder");
	}
	
	@AfterMethod
	public void afterMethod() {
	}
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		//login
		LogInPage objLogInPage = new LogInPage(driver);
		objLogInPage.clickLogIn();
		Assert.assertEquals(true, objLogInPage.confirmClickLogIn());
		objLogInPage.logIn();
		Assert.assertEquals(true, objLogInPage.confirmLogIn());
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
}

}
