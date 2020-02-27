package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class Helpers {

	// Read the properties file
	public static Properties loadPropertiesFromFile(String fileName) {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(fileName)) {
			// load a properties file
			prop.load(input);

			// get the property value and print it out
//			System.out.println("Database - " + prop.getProperty("database"));
//			System.out.println("Username - " + prop.getProperty("username"));
//			System.out.println("Password - " + prop.getProperty("password"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}
	
	// Reads the searchStrings.txt
	public static ArrayList<String> getStrings(String fileName) {
	    ArrayList<String> searches = new ArrayList<String>();
	    try {
			BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
			String line;
		    while ((line = inputStream.readLine()) != null) {
		        searches.add(line);
		    }
		    inputStream.close();
	    } catch (Exception e) {
	    	System.out.println("Error reading file " + fileName);
	    	System.exit(1);
	    }
	    return searches;
	}
	
	public static String getSearchString(ArrayList<String> list) {
		Random rand = new Random();
		int randNum = rand.nextInt(list.size());
		return list.get(randNum);
	}
	
	
	public static int getDelayTimeInSeconds(int min, int max) {
	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

}
