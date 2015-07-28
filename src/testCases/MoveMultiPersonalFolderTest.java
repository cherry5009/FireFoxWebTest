package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ActionList;
import pages.LogInPage;
import pages.NewFolderPage;

public class MoveMultiPersonalFolderTest {
	private WebDriver driver;
	
	@Test(priority=0)
	public void test1(){
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
	}
	
	@BeforeTest
	public void beforeTest(){
		driver = new FirefoxDriver();
		//login
		LogInPage objLogInPage = new LogInPage(driver);
		objLogInPage.clickLogIn();
		Assert.assertEquals(true, objLogInPage.confirmClickLogIn());
		objLogInPage.logIn();
		Assert.assertEquals(true, objLogInPage.confirmLogIn());
		//new test1
		NewFolderPage test1 = new NewFolderPage(driver,"test1");
		test1.newFolder();
		test1.createPersonalFile();
		Assert.assertEquals(true, test1.confirmCreate("test1"));
		//new test2
		NewFolderPage test2 = new NewFolderPage(driver, "test1");
		test2.newFolder();
		test2.createPersonalFile();
		Assert.assertEquals(true, test2.confirmCreate("test2"));
		//new testFolder1
		NewFolderPage objNewFolderPage1 = new NewFolderPage(driver,"testFolder1");
		objNewFolderPage1.newFolder();
		objNewFolderPage1.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage1.confirmCreate("testFolder1"));
		//new testFolder2
		NewFolderPage objNewFolderPage2 = new NewFolderPage(driver, "testFolder2");
		objNewFolderPage2.newFolder();
		objNewFolderPage2.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage2.confirmCreate("testFolder2"));
		//在testFolder2里面新建testFolder3
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		NewFolderPage objNewFolderPage3 = new NewFolderPage(driver, "testFolder3");
		objNewFolderPage3.newFolder();
		objNewFolderPage3.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage3.confirmCreate("testFolder3"));
	}
	
	@AfterTest
	public void afterTest(){
		//reset data
		//delete the testFolder and 1 which includes testFolder2
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		ActionList test1 = new ActionList(driver, "test1");
		test1.initActionList();
		test1.clickDelete();
		Assert.assertEquals(true, test1.confirmDelete());
		ActionList test2 = new ActionList(driver, "test2");
		test2.initActionList();
		test2.clickDelete();
		Assert.assertEquals(true, test2.confirmDelete());
		ActionList testFolder1 = new ActionList(driver, "testFolder1");
		testFolder1.initActionList();
		testFolder1.clickDelete();
		Assert.assertEquals(true, testFolder1.confirmDelete());
		ActionList testFolder2 = new ActionList(driver, "testFolder2");
		testFolder2.initActionList();
		testFolder2.clickDelete();
		Assert.assertEquals(true, testFolder2.confirmDelete());
		driver.quit();
	}
}
