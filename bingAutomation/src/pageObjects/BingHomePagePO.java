package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BingHomePagePO {
	
	public static WebElement searchBtn(WebDriver driver) {
		return driver.findElement(By.id("sb_form_go"));
	}
	
	public static WebElement searchField(WebDriver driver) {
		return driver.findElement(By.id("sb_form_q"));
	}	
	
	public static WebElement signInLabel(WebDriver driver) {
		return driver.findElement(By.id("id_s"));
	}
	
	public static WebElement alreadySignedInLabel(WebDriver driver) {
		return driver.findElement(By.id("id_n"));
	}
	
	public static WebElement signInOut(WebDriver driver) {
		return driver.findElement(By.className("id_link_text"));
	}
	
}
