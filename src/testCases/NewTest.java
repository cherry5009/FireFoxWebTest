package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ActionList;
import pages.CopyList;
import pages.LogInPage;
import pages.NewFolderPage;

public class NewTest {
	private WebDriver driver;
    @Test
    public void f() {
    	//先删掉根目录下原来存在的testFolder
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
    	ActionList oldFolder = new ActionList(driver, "testFolder");
		oldFolder.clickDelete();
		Assert.assertEquals(true, oldFolder.confirmDelete());
		//进入到要复制的testFolder所在的位置
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder3\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.clickMoveAndCopy();
		CopyList copyList = new CopyList(driver);
		copyList.copyToRoot();
		Assert.assertEquals(true, copyList.confirmCopyToRoot("testFolder"));
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
		//new testFolder
		NewFolderPage objNewFolderPage = new NewFolderPage(driver, "testFolder");
		objNewFolderPage.newFolder();
		objNewFolderPage.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage.confirmCreate("testFolder"));
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
		//在testFolder1里面new testFolder
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder1\"]"))).click();
		NewFolderPage objNewFolderPage11 = new NewFolderPage(driver,"testFolder");
		objNewFolderPage11.newFolder();
		objNewFolderPage11.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage11.confirmCreate("testFolder"));
		//在testFolder2里面新建testFolder3
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		NewFolderPage objNewFolderPage3 = new NewFolderPage(driver, "testFolder3");
		objNewFolderPage3.newFolder();
		objNewFolderPage3.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage3.confirmCreate("testFolder3"));
		//在testFolder3里面新建testFolder
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder3\"]"))).click();
		NewFolderPage objNewFolderPage31 = new NewFolderPage(driver, "testFolder");
		objNewFolderPage31.newFolder();
		objNewFolderPage31.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage31.confirmCreate("testFolder"));
		
    }
    
    @AfterTest
    public void afterTest(){
    	//reset data
		//delete the testFolder and 1 which includes testFolder2
    	new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
//		ActionList testFolder = new ActionList(driver, "testFolder");
//		testFolder.clickDelete();
//		Assert.assertEquals(true, testFolder.confirmDelete());
		ActionList testFolder1 = new ActionList(driver, "testFolder1");
		testFolder1.clickDelete();
		Assert.assertEquals(true, testFolder1.confirmDelete());
		ActionList testFolder2 = new ActionList(driver, "testFolder2");
		testFolder2.clickDelete();
		Assert.assertEquals(true, testFolder2.confirmDelete());
		driver.quit();
    }
}
