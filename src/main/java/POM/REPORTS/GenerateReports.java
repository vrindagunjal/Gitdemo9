package POM.REPORTS;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenerateReports implements IReporter {
	private ExtentReports extent;
	//public ExtentTest logger;
	String storyName = this.getClass().getSimpleName();

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(outputDirectory + File.separator + storyName + ".html", true);
		//htmlReporter.config().setAutoCreateRelativePathMedia(true);
		// Iterate over every suite assigned for execution
		for (ISuite suite : suites) {

			Map<String, ISuiteResult> suiteResults = suite.getResults();

			for (ISuiteResult sr : suiteResults.values()) {

				ITestContext tc = sr.getTestContext();

				System.out.println("Test tag name: " + tc.getName() + "Test start time: " + tc.getStartDate()
						+ "Test end time: " + tc.getEndDate() + "Test report output dir: " + tc.getOutputDirectory());

				Collection<ITestNGMethod> failedMethods = tc.getFailedTests().getAllMethods();
				if (failedMethods.size() != 0) {
					System.out.println("Total failed methods: " + failedMethods.size() + " and those are: ");

					for (ITestNGMethod itm : failedMethods) {
						System.out.println(
								"Method description: " + itm.getDescription() + " Name: " + itm.getMethodName());
				
					}
				}
				System.out.println("Passed tests for suite is :" + tc.getPassedTests().getAllResults().size());
				System.out.println("Failed tests for suite is :" + tc.getFailedTests().getAllResults().size());
				System.out.println("Skipped tests for suite is:" + tc.getSkippedTests().getAllResults().size());

				buildTestNodes(tc.getPassedTests(), LogStatus.PASS);
				buildTestNodes(tc.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(tc.getSkippedTests(), LogStatus.SKIP);
			}
		}
		extent.flush();
		extent.close();
		System.out.println("Path - " + outputDirectory);

	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
					System.out.println("inside throwable");
					String screenshotPath = "";
					try {
						screenshotPath = POM.COMMON.common.captureScreenshot(result);
						System.out.println(screenshotPath);
						test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//test.addScreenCapture(screenshotPath);
					//((Object) test).fail("Test Case failed check screenshot below"+test.addScreenCapture(path));)
					
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
