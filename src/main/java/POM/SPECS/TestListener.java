//TestListener.java


package POM.SPECS;


import org.testng.ITestListener;
import org.testng.ITestResult;



public class TestListener implements ITestListener{


	public void onTestFailure(ITestResult result) {
	 System.out.println("Test Failed");
	}
}
