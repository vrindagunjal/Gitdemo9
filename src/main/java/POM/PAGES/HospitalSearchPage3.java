package POM.PAGES;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HospitalSearchPage3 {

	public static WebElement srcHosp, clrBtn, hospName, span, hosp24by7name, rate;

	public static List<WebElement> hospNames;
	@FindBy(id="elementId") WebElement element;
	public static WebElement getsrchHospTxt() {
		srcHosp = POM.CONFIGURATION.Driver.driver.findElement(By.className("c-omni-searchbox--small"));
		System.out.println("This is a new commit");
		return srcHosp;

	}

	public static WebElement getClearBtn() {
		clrBtn = POM.CONFIGURATION.Driver.driver.findElement(By.className("icon-ic_cross_solid"));
		return clrBtn;

	}

	public static WebElement getHospdropdownNames(String data) {
		String xpath = "//div[text()=" + "'" + data + "'" + "]";
		hospName = POM.CONFIGURATION.Driver.driver.findElement(By.xpath(xpath));
		return hospName;

	}

	public static List<WebElement> getHospNames() {
		hospNames = POM.CONFIGURATION.Driver.driver.findElements(By.xpath("//div[@class='c-estb-card']"));
		return hospNames;

	}

	public static WebElement getSpan(WebElement hospital) {

		span = hospital.findElement(By.xpath(".//div[2]/div[1]/div/span[2]"));
		return span;
	}

	public static WebElement getHospName(WebElement hospital24) {
		hosp24by7name = hospital24.findElement(By.xpath(".//a"));
		return hosp24by7name;

	}

	public static WebElement getRate(WebElement hospital) {
		POM.CONFIGURATION.Driver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));
		//System.out.println("Inside GetRate function");
		try {
			
			//System.out.println("name of hosp ---> "+hospital.getText());
			String hosp=hospital.findElement(By.xpath(".//div[3]/div[1]/div[1]/span[1]")).getText();
			
			if (!hosp.isEmpty()&& !hosp.isBlank()) {
				try {
					rate=hospital.findElement(By.xpath(".//div[3]/div[1]/div[1]/span[1]"));
					
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
			}

			else {
				try {
					//System.out.println("no rating");
					rate=null;
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		return rate;
	}

	/// html/body/div/div/div[3]/div/div[1]/div[11]/div[3]/div[1]/div/span[1]

}
