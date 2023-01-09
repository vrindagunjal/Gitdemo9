package POM.COMMON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

public class common {

	public static void openBrowser() {
		if (POM.CONFIGURATION.Config.browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", POM.CONFIGURATION.Config.chromePath);
			POM.CONFIGURATION.Driver.driver = new ChromeDriver();
		}

		else if (POM.CONFIGURATION.Config.browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.driver", POM.CONFIGURATION.Config.firefoxPath);
			POM.CONFIGURATION.Driver.driver = new FirefoxDriver();
		}
	}

	public static String[] readCSV(String fpath) {
		File file = new File(fpath);
		FileReader fr;
		String[] data = {};
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			try {
				line = br.readLine();
				data = line.split(",");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;

	}

	public static void closeBrowser() {
		POM.CONFIGURATION.Driver.driver.quit();
	}

	public static void scrollBy(int i, int j) throws InterruptedException {
		POM.CONFIGURATION.Driver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));
		JavascriptExecutor js = (JavascriptExecutor) POM.CONFIGURATION.Driver.driver;		
		js.executeScript("window.scrollBy(" + i + "," + j + ")");
		/*
		 * Actions act = new Actions(POM.CONFIGURATION.Driver.driver);
		 * act.sendKeys(Keys.PAGE_DOWN).build().perform();
		 * act.sendKeys(Keys.PAGE_DOWN).build().perform();
		 */
	}
	
	public static String captureScreenshot(ITestResult result) {

		// Here will compare if test is failing then only it will enter into if
		// condition
		//String path ="./test-output/Screenshots/" + result.getName() + ".png";
		
		String path = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+result.getName()+".png";
		System.out.println("Result is " + result.getStatus());
		if (ITestResult.FAILURE == result.getStatus()) {
			System.out.println("Test Failed");
			try {
				// Create refernce of TakesScreenshot
				TakesScreenshot ts = (TakesScreenshot) POM.CONFIGURATION.Driver.driver;

				// Call method to capture screenshot
				File source = ts.getScreenshotAs(OutputType.FILE);

							// result.getName() will return name of test case so that screenshot name will
				// be same
				 
				File destination=new File(path);
				
				try {
					FileUtils.copyFile(source,destination );
					System.out.println("Screenshot taken");

				}

				catch (Exception e) {
					System.out.println("Exception while taking screenshot " + e.getMessage());
				}

			} catch (Exception e) {

			}
		}
		return path;
	}
	
}
