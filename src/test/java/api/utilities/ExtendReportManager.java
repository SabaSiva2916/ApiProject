package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports reports;
	public ExtentTest extentTest;

	String reportName;

	public void onFinish(ITestContext testContext) {

		reports.flush();
	}

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
		sparkReporter.config().setDocumentTitle("Rest Assured Automation Project");
		sparkReporter.config().setReportName("Pet Store Users API");
		sparkReporter.config().setTheme(Theme.DARK);

		reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
		reports.setSystemInfo("Application", "Pet Store Users API");
		reports.setSystemInfo("Operating System", System.getProperty("os.name"));
		reports.setSystemInfo("User Name", System.getProperty("user.name"));
		reports.setSystemInfo("Environment", "QA");
		reports.setSystemInfo("user", "Saba");

	}

	

	public void onTestFailure(ITestResult result) {

		extentTest = reports.createTest(result.getName());
		extentTest.createNode(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.FAIL, "Test failed");
		extentTest.log(Status.FAIL, result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result) {

		extentTest = reports.createTest(result.getName());
		extentTest.createNode(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.SKIP, "Test Skipped");
		extentTest.log(Status.SKIP, result.getThrowable().getMessage());

	}

	

	public void onTestSuccess(ITestResult result) {
		extentTest = reports.createTest(result.getName());
		extentTest.createNode(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.PASS, "Test Pass");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

}
