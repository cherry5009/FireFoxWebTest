package pages;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionList {
	private WebDriver driver;
	private String fileName;
	
	public ActionList(WebDriver driver,String name){
		this.driver = driver;
		this.fileName = name;
	}
	
	public void initActionList(){
		
//		By locator = By.cssSelector("div[title=\""+fileName+"\"]");
//		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(locator));
//		WebElement element = driver.findElement(locator);
//		builder.moveToElement(element).contextClick(element).build().perform();
		WebElement element = new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d){
				return d.findElement(By.cssSelector("div[title=\""+fileName+"\"]"));
			}
		});
		Actions builder = new Actions(driver);
		try {
			builder.moveToElement(element).contextClick(element).build().perform();
		} catch (StaleElementReferenceException e) {
			WebElement elementRepeat = new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver d){
					return d.findElement(By.cssSelector("div[title=\""+fileName+"\"]"));
				}
			});
			builder.moveToElement(elementRepeat).contextClick(elementRepeat).build().perform();
		}
		
	}
	
	public void clickDelete() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i.icon.icon-menu-delete"))).click();
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pure-button.button-primary.confirm"))).click();
	}
	
	public boolean confirmDelete() {
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[title=\""+fileName+"\"]")));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	public void clickMoveAndCopy() {
		String currentWindowHandle = driver.getWindowHandle();
		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i.icon.icon-menu-move-copy"))).click();
//		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		new WebDriverWait(driver, 3).until(new ExpectedCondition<WebDriver>() {
			@Override
			public WebDriver apply(WebDriver d){
				return d.switchTo().window(d.getWindowHandles().iterator().next());
			}
		});
		Iterator<String> windowHandlesIter = driver.getWindowHandles().iterator();
		while(windowHandlesIter.hasNext()){
			String temp = windowHandlesIter.next();
			if (currentWindowHandle.equalsIgnoreCase(temp)) {
				continue;
			}
			driver.switchTo().window(temp);
		}
	}
}
