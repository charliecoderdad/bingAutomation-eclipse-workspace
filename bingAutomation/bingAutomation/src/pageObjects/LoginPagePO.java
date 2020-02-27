package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPagePO {
	
	public static WebElement signInBtn(WebDriver driver) {
		return driver.findElement(By.id("idSIButton9"));
	}

	public static WebElement userId(WebDriver driver) {
		return driver.findElement(By.id("i0116"));
	}
	
	public static WebElement userPw(WebDriver driver) {
		return driver.findElement(By.id("i0118"));
	}
	
}
