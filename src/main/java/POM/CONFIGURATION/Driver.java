package POM.CONFIGURATION;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(POM.REPORTS.GenerateReports.class)
public class Driver {
	public static WebDriver driver;

	@Test
	
	public void launch() throws IOException, InterruptedException {

		POM.SPECS.Launch.launchSite();
	}

	
}
