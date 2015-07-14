package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LogInPage {
	private WebDriver driver;
	
	public LogInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setEmail(String email) {
		try {
			driver.findElement(By.id("email")).sendKeys(email);
		} catch (NoSuchElementException e) {
			System.out.println(email.toString());
		}
	}
	
	public void setPassword(String password) {
		try {
			driver.findElement(By.id("password")).sendKeys(password);;
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
		}
	}
	
	public void clickLogIn() {
		try {
			driver.findElement(By.cssSelector("button.pure-button.button-primary")).click();
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
		}
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
}
