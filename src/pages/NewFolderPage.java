package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewFolderPage {
	private WebDriver driver;
	private String fileName;
	
	public NewFolderPage(WebDriver driver,String fileName){
		this.driver = driver;
		this.fileName = fileName;
	}
	
	public void newFolder(){
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.id("create_new")));
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("create_new"))).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.act-new-file"))).click();
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		driver.findElement(By.id("new_item_name")).sendKeys(fileName);
	}
	
	//没有考虑重名的情况
	public void createPersonalFile(){
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li[data-radio-value='create_folder']"))).click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-primary.submit"))).click();
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
