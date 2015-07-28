package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import pages.ActionList;
import pages.LogInPage;
import pages.MoveList;
import pages.NewFolderPage;

public class MovePersonalFolderTest {
	private WebDriver driver;
	
	//初始状态：根目录下有testFolder,testFolder1和testFolder2,其中testFolder2->testFolder3
	//从根目录向下移动一级目录
	//把testFolder移动到testFolder1中去，结构变成1->test,2->3
	@Test(priority=0)
	public void test0(){
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();  
		MoveList moveList = new MoveList(driver);
		moveList.moveOneLevelIn("testFolder1");
		Assert.assertEquals(true, moveList.confirmMoveOneLevelIn("testFolder", "testFolder1"));
	}
	
	//移动到父文件夹的兄弟文件夹的子文件夹中
	//把testFolder移动到testFolder3中，结构变成1,2->3->test
	@Test(priority=1)
	public void test1(){
		//先进入到testFolder所在的位置
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder1\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		MoveList moveList = new MoveList(driver);
		moveList.moveTwoLevelIn("testFolder2", "testFolder3");
		Assert.assertEquals(true, moveList.confirmMoveTwoLevelIn("testFolder", "testFolder2", "testFolder3"));
	}
	
	//移动到父文件夹中去
	//把testFolder移动到testFolder2中，结构变成1,2->(test,3)
	@Test(priority=2)
	public void test2(){
		//先进入到testFolder所在的位置
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder3\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		MoveList moveList = new MoveList(driver);
		moveList.moveOneLevelIn("testFolder2");  //对于文件树而言，相当于从根目录进入到testFolder2中
		Assert.assertEquals(true, moveList.confirmMoveOneLevelIn("testFolder", "testFolder2"));
	}
	
	//移动到根目录下
	//把testFolder移动到根目录下，结构变成test,1,2->3
	@Test(priority=3)
	public void test3(){
		//先进入到testFolder所在的位置
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		MoveList moveList = new MoveList(driver);
		moveList.moveToRoot();
		Assert.assertEquals(true, moveList.confirmMoveToRoot("testFolder"));
	}
	
	//移动两级目录
	//把testFolder移动到testFolder3下，结构变成1,2->3->test
	@Test(priority=4)
	public void test4(){
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		MoveList moveList = new MoveList(driver);
		moveList.moveTwoLevelIn("testFolder2", "testFolder3");
		Assert.assertEquals(true, moveList.confirmMoveTwoLevelIn("testFolder", "testFolder2", "testFolder3"));
	}
	
	//移动两级目录，回到根目录下
	//把testFolder移动到root下，结构变成test,1,2->3
	@Test(priority=5)
	public void test5(){
		//先进入到testFolder所在的位置
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder3\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.initActionList();
		objActionList.clickMoveAndCopy();
		MoveList moveList = new MoveList(driver);
		moveList.moveToRoot();
		Assert.assertEquals(true, moveList.confirmMoveToRoot("testFolder"));
	}
	
//	//移动一级目录，把子文件夹移动到与自己同级的文件夹的子文件夹中
//	@Test(priority=4)
//	public void moveOutToBrother(){
//		ActionList objActionList = new ActionList(driver, "testFolder");
//		objActionList.clickMoveAndCopy();  
//		//首先把testFolder移动到testFolder3中去
//		MoveList moveList = new MoveList(driver);
//		moveList.moveOneLevelIn("testFolder3");
//		Assert.assertEquals(true, moveList.confirmMoveOneLevelIn("testFolder", "testFolder3"));
//		//把testFolder从testFolder3下移动到testFolder2下
//	}
	
	@BeforeMethod
	public void beforeMethod(){
		//回到首页
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
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
		//new testFolder
		NewFolderPage objNewFolderPage = new NewFolderPage(driver,"testFolder");
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
		//在testFolder2里面新建testFolder3
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder2\"]"))).click();
		NewFolderPage objNewFolderPage3 = new NewFolderPage(driver, "testFolder3");
		objNewFolderPage3.newFolder();
		objNewFolderPage3.createPersonalFile();
		Assert.assertEquals(true, objNewFolderPage3.confirmCreate("testFolder3"));
		
	}
	
	@AfterTest
	public void afterTest() {
		//reset data
		//delete the testFolder and 1 which includes testFolder2
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
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
