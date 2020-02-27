package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;	

import pageActions.BingHomePagePA;
import utils.Helpers;

public class BingAutomation {
	
	public static ArrayList<String> myStrings = null;
	public static String search = null;
	public static Properties props = Helpers.loadPropertiesFromFile("settings.properties");
	
	public static List<String> userids = Arrays.asList(props.getProperty("BING_ACCOUNT_ID").split("\\s*,\\s*"));
	public static List<String> pws = Arrays.asList(props.getProperty("BING_ACCOUNT_PW").split("\\s*,\\s*"));
	
	public static void main(String args[]) {
		System.out.println("Starting Bing Automation");
		
		if (userids.size() != pws.size()) {
			System.out.println("You have non-matching userids and passwords configured in your properties file.");
			System.out.println("Check your properties file and ensure you have a password for each user id specified");
			System.exit(1);
		}
		
		// Load search strings into array variable from the file
		myStrings = Helpers.getStrings(props.getProperty("SEARCH_STRINGS_FILE"));
		
		for (int i = 0; i <= (userids.size()-1); i++) {
			
			WebDriver driver = null;
			String os = System.getProperty("os.name").toLowerCase();
			System.out.println("OS is: " + os);			
			if (props.getProperty("BROWSER").equals("firefox")) {
				System.out.println("Using the Firefox browser");
				if (os.indexOf("win") >=0) {
					System.out.println("Running in Windows");
					System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver.exe");
				} else {
					System.out.println("Running in Linux");
					System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver");
				}
				driver = new FirefoxDriver();
			} else {
				System.out.println("Using the Chrome browser");
				if (os.indexOf("win") >=0) {					
					System.out.println("Running in Windows");
					System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
				} else {
					System.out.println("Running in Linux");
					System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver");
				}
				driver = new ChromeDriver();
			}
			
			driver.get(props.getProperty("BING_URL"));
			
			// We have to comment out maximize statement due to failure to run in xvfb-run
			//driver.manage().window().maximize();
		
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);		
			
			// Add this to work with Chrome.. lets try it.
			try { Thread.sleep(5000); } catch (Exception e) {}
			
			// Login
			BingHomePagePA.signIn(driver, userids.get(i), pws.get(i));
			
			int numOfSearches = Integer.parseInt(props.getProperty("NUMBER_OF_SEARCHES_TO_PERFORM"));
			int minDelay = Integer.parseInt(props.getProperty("MIN_DELAY_TIME"));
			int maxDelay = Integer.parseInt(props.getProperty("MAX_DELAY_TIME"));
			for (int q = 0; q < numOfSearches; q++) {
				// Go to Bing homepage
				driver.get(props.getProperty("BING_URL"));
				
				// Get search string
				search = Helpers.getSearchString(myStrings);
				
				System.out.println("Search #: " + (q+1) + "/" +numOfSearches+ " - Search String: " + search);
				
				// Get delay and wait between searches
				int delay = Helpers.getDelayTimeInSeconds(minDelay, maxDelay);
				System.out.println("Delaying " + delay + " seconds until search");
				try { Thread.sleep(delay*1000); } catch (Exception e) {}
				
				// Perform Search
				BingHomePagePA.search(driver, search);
				
			}
			System.out.println("Searching completed for " +  userids.get(i));
			driver.quit();
		}
	}
}
