
package POM.SPECS;


import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


@Test
public class Launch {
	public static void launchSite() throws IOException, InterruptedException {
		// Open Browser
		POM.COMMON.common.openBrowser();

		// Implicit wait time out declaration
		POM.CONFIGURATION.Driver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));

		// Open URL
		POM.CONFIGURATION.Driver.driver.get(POM.CONFIGURATION.Config.url);
		String Title = POM.CONFIGURATION.Driver.driver.getTitle();
		Assert.assertEquals(Title,
				"Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");

		POM.CONFIGURATION.Driver.driver.manage().window().maximize();
		// Read data from CSV
		String[] data = POM.COMMON.common.readCSV(POM.CONFIGURATION.Config.filePath);

		// Click on Search for Hospitals link
		POM.PAGES.HomePage.gethospLink().click();

		// Click on Search text box
		POM.PAGES.HospitalSearchPage.getsrchHospTxt().click();

		// Clear the text box
		POM.PAGES.HospitalSearchPage.getClearBtn().click();

		// Enter the location
		POM.PAGES.HospitalSearchPage.getsrchHospTxt().sendKeys(data[0]);

		// Explicitly wait for dynamic values
		WebDriverWait wait = new WebDriverWait(POM.CONFIGURATION.Driver.driver, Duration.ofSeconds(7000));
		wait.until(ExpectedConditions.visibilityOf(POM.PAGES.HospitalSearchPage.getHospdropdownNames(data[0])));

		// Select the dynamic value
		POM.PAGES.HospitalSearchPage.getHospdropdownNames(data[0]).click();

		wait.until(ExpectedConditions
				.visibilityOf(POM.CONFIGURATION.Driver.driver.findElement(By.xpath("//h1[@class='title']"))));
		String checkString = "Hospitals in " + data[0];
		Thread.sleep(7000);
		String nextTitle = POM.CONFIGURATION.Driver.driver.getTitle();
		System.out.println(nextTitle);
		assertTrue(nextTitle.contains("Best123 Hospitals in " + data[0]));

		assertTrue(POM.CONFIGURATION.Driver.driver.getPageSource().contains(checkString));

		try {
			// Scroll for more hospitals
			POM.COMMON.common.scrollBy(0, 3000);
			POM.SPECS.Functions.func_Hosp24by7(data);
			//POM.SPECS.Functions.parkingFacility(data);
			POM.CONFIGURATION.Driver.driver.navigate().refresh();
			POM.SPECS.Functions.func_highRatingHosp(data);
			//POM.COMMON.common.closeBrowser();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception occured " + e);
		}
	}

}
