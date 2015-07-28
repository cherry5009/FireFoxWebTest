package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CopyList {
	private WebDriver driver;
	
	public CopyList(WebDriver driver){
		this.driver = driver;
	}
	
	//从根目录向下复制一级目录
	public void copyOneLevelIn(String folderName){
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.level1[title=\""+folderName+"\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-secondary.copy"))).click();
	}
	
	//confirm
	public boolean confirmCopyOneLevelIn(String name,String folderName){
		try {
//			try {
//				new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
//				return false;
//			} catch (TimeoutException e) {
//				//Do thing, continue
//			}//这个try catch是为了防止出现以下错误：复制失败，原来的文件不见了，但因为页面来不及刷新，下面这句话仍旧不会跳出超时异常，从而会被错误判断为复制成功
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+folderName+"\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
//			System.out.println("true");   //for debug
			return true;
		} catch (TimeoutException e) {
//			System.out.println("false");  //for debug
			return false;
		}
	}
	
	//复制到根目录
	public void copyToRoot(){
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.level0[title=\"全部文件\"]"))).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-secondary.copy"))).click();
	}
	
	//confirm
	public boolean confirmCopyToRoot(String name){
		try {
//			try {
//				new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
//				return false;
//			} catch (TimeoutException e) {
//				// Do nothing, continue
//			}
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	//从根目录向下复制两级目录
	public void copyTwoLevelIn(String folderName1,String folderName2){
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.level1[title=\""+folderName1+"\"]")));   //确认folderName1文件夹已出现
		WebElement folder1 = driver.findElement(By.cssSelector("a.level1[title=\""+folderName1+"\"]"));
		String idName = folder1.getAttribute("id");
		idName = idName.substring(0, idName.length()-1);  //去掉后面的a,For example,得到的是fileTree_1_2_a，去掉a
		idName = idName+"switch";  //加上switch，转变成三角箭头的id
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.id(idName))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.level2[title=\""+folderName2+"\"]"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-secondary.copy"))).click();
	}
	
	//confirm
	public boolean confirmCopyTwoLevelIn(String name,String folderName1,String folderName2){
		try {
//			try {
//				new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
//				return false;
//			} catch (TimeoutException e) {
//				// Do nothing, continue
//			}
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+folderName1+"\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+folderName2+"\"]"))).click();
			new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+name+"\"]")));
//			System.out.println("true");   //for debug
			return true;
		} catch (TimeoutException e) {
//			System.out.println("false");   //for debug
			return false;
		}
	}
		
}
