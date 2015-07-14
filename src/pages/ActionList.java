package pages;
//testhhsdhhdh
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionList {
	private WebDriver driver;
	private WebElement actionList;
	private Actions builder;
	private String fileName;
	
	public ActionList(WebDriver driver,String name){
		this.driver = driver;
		this.fileName=name;
		new WebDriverWait(this.driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+this.fileName+"\"]")));
		this.actionList = this.driver.findElement(By.cssSelector("div[title=\""+this.fileName+"\"]"));
		builder = new Actions(this.driver);
		builder.moveToElement(actionList).contextClick().build().perform();
	}
	
	public void delete() {
		driver.findElement(By.cssSelector("i.icon.icon-menu-delete")).click();
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-primary.confirm"))).click();
	}
	
	public boolean confirmDelete() {
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+this.fileName+"\"]")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void move(String folderName) {
		driver.findElement(By.cssSelector("i.icon.icon-menu-move-copy")).click();
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("fileTree_1_2_a"))).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-primary.move"))).click();
	}
	
	//确认名字为fileName的file或者folder已经移动到name为name2的folder中
	public boolean confirmMove(String folderName) {
		boolean flag1;
		boolean flag2;
		
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+folderName+"\"]")));
			driver.findElement(By.cssSelector("div[title=\""+folderName+"\"]")).click();  //进入目标文件夹
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+fileName+"\"]")));
			driver.findElement(By.cssSelector("a[title=\"亿方云\"]")).click();   //回到首页
			flag2 = true;
		} catch (TimeoutException e) {
			flag2 = false;
		}
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+fileName+"\"]")));
			flag1 = false;
		} catch (TimeoutException e) {
			flag1 = true;
		}
		return flag1&&flag2;
	}
	
	//把文件从testFolder中移动到根目录下
	public void moveOut() {
		driver.findElement(By.cssSelector("i.icon.icon-menu-move-copy")).click();
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("fileTree_1_1_a"))).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-primary.move"))).click();
	}
	
	public boolean confirmMoveOut(){
		boolean flag1,flag2;
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+fileName+"\"]")));
			flag2 = true;
		} catch (TimeoutException e) {
			flag2 = false;
		}
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\"testFolder\"]"))).click();
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+fileName+"\"]")));
			flag1 = false;
		} catch (TimeoutException e) {
			flag1 = true;
		}
		return flag1&&flag2;
	}
	
	//把文件从根目录复制到folderName的个人文件夹里
	public void copy(String folderName) {
		driver.findElement(By.cssSelector("i.icon.icon-menu-move-copy")).click();
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("fileTree_1_2_a"))).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-secondary.copy"))).click();
	}
	
	public boolean confirmCopy(String folderName) {
		boolean flag1,flag2;
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+fileName+"\"]")));
			flag1 = true;
		} catch (TimeoutException e) {
			flag1 = false;
		}
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+folderName+"\"]")));
			driver.findElement(By.cssSelector("div[title=\""+folderName+"\"]")).click();  //进入目标文件夹
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+fileName+"\"]")));
			driver.findElement(By.cssSelector("a[title=\"亿方云\"]")).click();   //回到首页
			flag2 = true;
		} catch (TimeoutException e) {
			flag2 = false;
		}
		return flag1&&flag2;
	}
	
	//把文件从testFolder里面复制到根目录下
	public void copyOut(){
		driver.findElement(By.cssSelector("i.icon.icon-menu-move-copy")).click();
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("fileTree_1_1_a"))).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-secondary.copy"))).click();
	}
	
	public boolean confirmCopyOut(){
		boolean flag1,flag2;
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+fileName+"\"]")));
			flag1 = true;
		} catch (TimeoutException e) {
			flag1 = false;
		}
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=\"亿方云\"]"))).click();
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title=\""+fileName+"\"]")));
			flag2 = true;
		} catch (TimeoutException e) {
			flag2 = false;
		}
		return flag1&&flag2;
	}
}
