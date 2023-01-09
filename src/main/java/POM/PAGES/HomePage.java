package POM.PAGES;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {

	public static WebElement srcHospLink;
	
	
	public static WebElement gethospLink()
	{
		try {
			srcHospLink=POM.CONFIGURATION.Driver.driver.findElement(By.linkText("Search for hospitals"));
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return srcHospLink;
		
	}
	
	
	
}
