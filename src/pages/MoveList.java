package pages;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoveList {
	private WebDriver driver;
	
	public MoveList(WebDriver driver){
		this.driver = driver;
	}
	
	//从根目录向下移动一级目录
	public void moveOneLevelIn(String folderName){
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.level1[title=\""+folderName+"\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-primary.move"))).click();
	}
	
	//confirm
	public boolean confirmMoveOneLevelIn(String name,String folderName){
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			//进入目标文件夹
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+folderName+"\"]"))).click();
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	//移动到根目录
	public void moveToRoot(){
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.level0[title=\"全部文件\"]"))).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-primary.move"))).click();
	}
	
	//confirm
	public boolean confirmMoveToRoot(String name){
		try {
			new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	//从根目录向下移动两级目录
	public void moveTwoLevelIn(String folderName1,String folderName2){
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.level1[title=\""+folderName1+"\"]")));   //确认folderName1文件夹已出现
		WebElement folder1 = driver.findElement(By.cssSelector("a.level1[title=\""+folderName1+"\"]"));
		String idName = folder1.getAttribute("id");
		idName = idName.substring(0, idName.length()-1);  //去掉后面的a,For example,得到的是fileTree_1_2_a，去掉a
		idName = idName+"switch";  //加上switch，转变成三角箭头的id
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.id(idName))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.level2[title=\""+folderName2+"\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-primary.move"))).click();
	}
	
	//confirm
	public boolean confirmMoveTwoLevelIn(String name,String folderName1,String folderName2){
		try {
			new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+folderName1+"\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+folderName2+"\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
}
