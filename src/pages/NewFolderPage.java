package pages;

import java.util.NoSuchElementException;
//import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewFolderPage {
	private WebDriver driver;
	private String fileName;
	private Actions builder;
	
	public NewFolderPage(WebDriver driver,String fileName){
		this.driver = driver;
		this.fileName = fileName;
		this.builder = new Actions(this.driver);
	}
	
	public void newFolder(){
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("new_folder")));
		WebElement newFolderButton = driver.findElement(By.id("new_folder"));
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(newFolderButton));
		builder.moveToElement(newFolderButton).click().build().perform();
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		driver.findElement(By.id("new_item_name")).sendKeys(fileName);
	}
	
	public void choosePersonalFile(){
		try {
			driver.findElement(By.cssSelector("li[data-radio-value='create_folder']")).click();
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
		}
	}
	
	//需考虑重名的情况
	public void createPersonalFile(){
		try {
			driver.findElement(By.cssSelector("button.pure-button.button-primary.submit")).click();
//		Set<String> windowHandle = driver.getWindowHandles();
//		if (windowHandle.size()!=1) {
//			driver.switchTo().window(windowHandle.iterator().next());
//			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-primary.confirm"))).click();
//		}
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
		}
	}
	
	public boolean confirmCreate(String name) {
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[title="+name+"]")));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

}
