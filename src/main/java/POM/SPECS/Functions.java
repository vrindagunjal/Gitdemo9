package POM.SPECS;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Functions {
	@Test
	public static void func_Hosp24by7(String[] data) {
		// WebDriverWait wait1= new
		// WebDriverWait(POM.CONFIGURATION.Driver.driver,Duration.ofSeconds(20000));
		// wait1.until(ExpectedConditions.visibilityOfAllElements(POM.PAGES.HospitalSearchPage.getHospNames()));
		POM.CONFIGURATION.Driver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		// Get list of all hospitals
		List<WebElement> hospitals = POM.PAGES.HospitalSearchPage.getHospNames();
		System.out.println("\n");
		System.out.println("*********************************");
		System.out.println("**** Hospitals open 24 by 7 *****");
		System.out.println("*********************************");
		// Loop through each hospital for condition 1) open 24*7

		for (WebElement hospital : hospitals) {

			String time = POM.PAGES.HospitalSearchPage.getSpan(hospital).getText();
			if (time.equalsIgnoreCase(data[1])) {
			// Adding the hospital to new list
				String hosp24by7name = POM.PAGES.HospitalSearchPage.getHospName(hospital).getText();
				System.out.println(hosp24by7name);

			}

		}

	}

	@Test
	public static void func_highRatingHosp(String[] data) throws InterruptedException {

		POM.CONFIGURATION.Driver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		// TODO Auto-generated method stub
		System.out.println("\n");
		System.out.println("**************************************************");
		System.out.println("**** Hospitals having rating higher than 3.5 *****");
		System.out.println("**************************************************");
		List<WebElement> hospitals = POM.PAGES.HospitalSearchPage.getHospNames();
		double baseRate = Double.parseDouble(data[3]);

		// Loop through each hospital for condition 3) Ratings higher than 3.5
		for (WebElement hospital : hospitals) {

			if (POM.PAGES.HospitalSearchPage.getRate(hospital) != null) {
				String rate = POM.PAGES.HospitalSearchPage.getRate(hospital).getText();
				if (!rate.isBlank() && !rate.isEmpty()) {
					double drate = Double.parseDouble(rate);

					if (drate > baseRate) {

						System.out.println(POM.PAGES.HospitalSearchPage.getHospName(hospital).getText());
						System.out.println("Rate: " + drate);
						System.out.println("*****************************");
					}
				}
			}
		}
	}

	@Test
	public static void parkingFacility(String[] data) {
		POM.CONFIGURATION.Driver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));
		System.out.println("\n");
		System.out.println("********************************************");
		System.out.println("**** Hospitals having Parking Facility *****");
		System.out.println("********************************************");
		List<WebElement> hospitals = POM.PAGES.HospitalSearchPage.getHospNames();
		// Loop through each hospital for condition 2) Hospitals having parking facility

		for (WebElement hospital : hospitals) {
			String name = POM.PAGES.HospitalSearchPage.getHospName(hospital).getText();
			// Click on each hospital to check parking facility
			POM.PAGES.HospitalSearchPage.getHospName(hospital).click();

			Set<String> handles = POM.CONFIGURATION.Driver.driver.getWindowHandles();
			Iterator<String> it = handles.iterator();
			String parentWin = it.next();
			String childWin = "";
			while (it.hasNext()) {
				childWin = it.next();
			}

			POM.CONFIGURATION.Driver.driver.switchTo().window(childWin);

			if (POM.CONFIGURATION.Driver.driver.getPageSource().contains(data[2])) {
				
				System.out.println(name);

				POM.CONFIGURATION.Driver.driver.switchTo().window(parentWin);

			} else {
				POM.CONFIGURATION.Driver.driver.switchTo().window(parentWin);

			}

		}

	}


}
