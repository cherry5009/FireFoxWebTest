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
import pages.CopyList;
import pages.LogInPage;
import pages.NewFolderPage;

public class CopyPersonalFolderTest {
	private WebDriver driver;
	
	//初始状态：根目录下有testFolder,testFolder1和testFolder2,其中testFolder2->testFolder3
	//从根目录向下复制一级目录
	//把testFolder复制到testFolder1中去，结构变成test,1->test,2->3
	@Test(priority=0)
	public void test0() {
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		CopyList copyList = new CopyList(driver);
		copyList.copyOneLevelIn("testFolder1");
		Assert.assertEquals(true, copyList.confirmCopyOneLevelIn("testFolder", "testFolder1"));
	}
	
	//复制到父文件夹的兄弟文件夹的子文件夹中
	//把testFolder复制到testFolder3中，结构变成test,1->test,2->3->test
	@Test(priority=1)
	public void test1(){
		//先进入到testFolder所在的位置
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder1\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		CopyList copyList = new CopyList(driver);
		copyList.copyTwoLevelIn("testFolder2", "testFolder3");
		Assert.assertEquals(true, copyList.confirmCopyTwoLevelIn("testFolder", "testFolder2", "testFolder3"));  //有重名，后面加了（1）
	}
	
	//复制到父文件夹中去
	//把testFolder复制到testFolder2中，结构变成test,1->test,2->(3->test,test)
	@Test(priority=2)
	public void test2(){
		//先进入到testFolder所在的位置
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder3\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		CopyList copyList = new CopyList(driver);
		copyList.copyOneLevelIn("testFolder2");
		Assert.assertEquals(true, copyList.confirmCopyOneLevelIn("testFolder", "testFolder2"));
	}
	
	//复制到根目录去
	//先删掉原来根目录下存在的testFolder，再把testFolder复制到根目录下，结构变成test,1->test,2->(3->test,test)
	@Test(priority=3)
	public void test3(){
		//先删掉根目录下最初存在的testFolder
		ActionList oldFolder = new ActionList(driver, "testFolder");
		oldFolder.initActionList();
		oldFolder.clickDelete();
		Assert.assertEquals(true, oldFolder.confirmDelete());
		//进入到testFolder所在的位置
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		CopyList copyList = new CopyList(driver);
		copyList.copyToRoot();
		Assert.assertEquals(true, copyList.confirmCopyToRoot("testFolder"));
	}
	
	//复制两级目录
	//先删掉原来testFolder3下的testFolder，再把testFolder复制到testFolder3下，结构变成test,1->test,2->(3->test,test)
	@Test(priority=4)
	public void test4(){
		//先删掉testFolder3下原来存在的testFolder
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder3\"]"))).click();
		ActionList oldFolder = new ActionList(driver, "testFolder");
		oldFolder.initActionList();
		oldFolder.clickDelete();
		Assert.assertEquals(true, oldFolder.confirmDelete());
		//把testFolder复制到testFolder3下
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		CopyList copyList = new CopyList(driver);
		copyList.copyTwoLevelIn("testFolder2", "testFolder3");
		Assert.assertEquals(true, copyList.confirmCopyTwoLevelIn("testFolder", "testFolder2", "testFolder3"));
	}
	
	//复制两级目录，复制到根目录下
	//先删掉根目录下的testFolder，再把testFolder复制到根目录下，结构变成test,1->test,2->(3->test,test)
	@Test(priority=5)
	public void test5(){
		//先删掉根目录下原来存在的testFolder
		ActionList oldFolder = new ActionList(driver, "testFolder");
		oldFolder.initActionList();
		oldFolder.clickDelete();
		Assert.assertEquals(true, oldFolder.confirmDelete());
		//进入到要复制的testFolder所在的位置
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder3\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		CopyList copyList = new CopyList(driver);
		copyList.copyToRoot();
		Assert.assertEquals(true, copyList.confirmCopyToRoot("testFolder"));
	}
	
	//每次执行test前，先回到首页
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
		NewFolderPage objNewFolderPage2 = new NewFolderPage(driver,"testFolder2");
		objNewFolderPage2.newFolder();
		objNewFolderPage2.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage.confirmCreate("testFolder2"));
		//在testFolder2里面新建testFolder3
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		NewFolderPage objNewFolderPage3 = new NewFolderPage(driver, "testFolder3");
		objNewFolderPage3.newFolder();
		objNewFolderPage3.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage1.confirmCreate("testFolder3"));
	}
	
	@AfterTest
	public void afterTest(){
		//reset data
		//delete the testFolder,1 and 2.Because test,1->test,2->(test,3->test)
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		ActionList testFolder = new ActionList(driver, "testFolder");
		testFolder.initActionList();
		testFolder.clickDelete();
		Assert.assertEquals(true, testFolder.confirmDelete());
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
