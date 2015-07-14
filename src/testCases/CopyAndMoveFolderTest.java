package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import pages.ActionList;
import pages.LogInPage;
import pages.NewFolderPage;

public class CopyAndMoveFolderTest {
	private WebDriver driver;
	private LogInPage objLogInPage;
	private NewFolderPage objNewFilePage1;
	private NewFolderPage objNewFilePage2;
	
	@Test
	public void copyPersonalFolder() {
		ActionList objActionList = new ActionList(driver, "testFolder1");
		objActionList.copy("testFolder2");  //把testFolder1移动到testFolder2中去
		Assert.assertEquals(true, objActionList.confirmCopy("testFolder2"));
	}
	
	@Test
	public void movePersonalFolder(){
		ActionList objActionList = new ActionList(driver, "testFolder1");
		objActionList.move("testFolder2");  //把testFolder1移动到testFolder2中去
		Assert.assertEquals(true, objActionList.confirmMove("testFolder2"));
	}
	
	@BeforeMethod
	public void beforeMethod() {
		//new a folder
		objNewFilePage1 = new NewFolderPage(driver,"testFolder1");
		objNewFilePage1.newFolder();
		objNewFilePage1.choosePersonalFile();
		objNewFilePage1.createPersonalFile();
		objNewFilePage1.confirmCreate("testFolder1");
		//new another folder
		objNewFilePage2 = new NewFolderPage(driver,"testFolder2");
		objNewFilePage2.newFolder();
		objNewFilePage2.choosePersonalFile();
		objNewFilePage2.createPersonalFile();
		objNewFilePage2.confirmCreate("testFolder2");
	}
	
	@AfterMethod
	public void afterMethod() {
		//delete the folder the test new
		//testFolder1要先判断是否存在，存在才删除，在move的testcase中它不存在8
		if (isFileExist("testFolder1")) {
			ActionList objActionList1 = new ActionList(driver, "testFolder1");
			objActionList1.delete();
			Assert.assertEquals(true, objActionList1.confirmDelete());
		}
		ActionList objActionList2 = new ActionList(driver, "testFolder2");
		objActionList2.delete();
		Assert.assertEquals(true, objActionList2.confirmDelete());
	}
	
	@BeforeTest
	public void beforeTest() {
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
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public boolean isFileExist(String name) {
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

}
