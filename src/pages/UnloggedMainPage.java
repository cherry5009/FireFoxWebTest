package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UnloggedMainPage {
	private WebDriver driver;
	
	public UnloggedMainPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void clickLogIn() {
		try {
			driver.findElement(By.linkText("登录")).click();;
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
		}
	}

}
