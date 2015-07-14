package pages;

import org.openqa.selenium.WebDriver;

public class LoggedMainPage {
	private WebDriver driver;
	
	public LoggedMainPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}

}
