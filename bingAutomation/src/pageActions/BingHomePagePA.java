package pageActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import pageObjects.BingHomePagePO;
import pageObjects.LoginPagePO;

public class BingHomePagePA {
	
	public static void search(WebDriver driver, String search) {
		BingHomePagePO.searchField(driver).sendKeys(search);
		BingHomePagePO.searchField(driver).sendKeys(Keys.ENTER);
//		BingHomePagePO.searchBtn(driver).click();
	}
	
	public static void signIn(WebDriver driver, String id, String pw) {
		System.out.println("Signing in with " + id + ":" + pw);
		BingHomePagePO.signInLabel(driver).click();
		
//		BingHomePagePO.signInOut(driver).click();
		
		LoginPagePO.userId(driver).sendKeys(id);
		LoginPagePO.signInBtn(driver).click();
		try { Thread.sleep(5000); } catch (Exception e) {}
		LoginPagePO.userPw(driver).sendKeys(pw);		
		LoginPagePO.signInBtn(driver).click();
		// 4/3/2016: had to add this sleep so that login would actually occur
		try { Thread.sleep(5000); } catch (Exception e) {}
	}
	
	public static void signOut(WebDriver driver) {
		BingHomePagePO.alreadySignedInLabel(driver).click();
		BingHomePagePO.signInOut(driver).click();
	}
	
	public static boolean isSignedIn(WebDriver driver) {
		WebElement element = BingHomePagePO.signInLabel(driver);
		if (element.getText().equals("Sign in")) {
			return false;
		} else {
			return true;
		}
	}
	
}
