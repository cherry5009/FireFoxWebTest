package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import pages.ActionList;
import pages.LogInPage;
import pages.NewFolderPage;

public class CopyAndMoveFileTest {
	private WebDriver driver;
	private LogInPage objLogInPage;
	private NewFolderPage objNewFolderPage;
	
	//把文件从根目录复制到同级的个人文件夹中
	@Test(priority=0)
	public void copyFile() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFile.doc");
		objActionList.copy("testFolder");
		Assert.assertEquals(true, objActionList.confirmCopy("testFolder"));
	}
	
	//把文件从根目录下的个人文件夹中复制到根目录下
	@Test(priority=1)
	public void copyFileOut(){
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();  //首先回到主页
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder\"]")));
		driver.findElement(By.cssSelector("div[title=\"testFolder\"]")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFile.doc\"]")));
		ActionList testFile = new ActionList(driver, "testFile.doc");
		testFile.copyOut();
		Assert.assertEquals(true, testFile.confirmCopyOut());
	}
	
	//把文件从根目录移动到同级的个人文件夹中
	@Test(priority=2)
	public void moveFile(){
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		ActionList objActionList = new ActionList(driver, "testFile.doc");
		objActionList.move("testFolder");  //把testFile.doc移动到testFolder中去
		Assert.assertEquals(true, objActionList.confirmMove("testFolder"));
	}
	
	//把文件从根目录下的个人文件夹中移动到根目录下
	@Test(priority=3)
	public void moveFileOut(){
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder\"]")));
		driver.findElement(By.cssSelector("div[title=\"testFolder\"]")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFile.doc\"]")));
		ActionList testFile = new ActionList(driver, "testFile.doc");
		testFile.moveOut();
		Assert.assertEquals(true, testFile.confirmMoveOut());
	}
	
	@BeforeTest 
	public void beforeTest() {
		//new testFolder
		objNewFolderPage = new NewFolderPage(driver, "testFolder");
		objNewFolderPage.newFolder();
		objNewFolderPage.choosePersonalFile();
		objNewFolderPage.createPersonalFile();
		objNewFolderPage.confirmCreate("testFolder");
	}
	
	@AfterTest  
	public void afterTest() {
		//reset data
		ActionList objActionList = new ActionList(driver, "testFolder");
		objActionList.delete();
		Assert.assertEquals(true, objActionList.confirmDelete());
	}
	
	@BeforeClass   //登录
	public void BeforeClass(){
		driver = new FirefoxDriver();
		String baseUrl="http://115.29.169.34/auth/login";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		//login
		objLogInPage = new LogInPage(driver);
		objLogInPage.setEmail("jiong09test@163.com");
		objLogInPage.setPassword("jiong0321");
		objLogInPage.clickLogIn();
	}
	
	@AfterClass  //退出浏览器
	public void AfterClass(){
		driver.quit();
	}

}
