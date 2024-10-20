package testComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import data.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extentReports = ExtentReporterNG.getReportObject(); 
	ExtentTest test;
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();
	@Override
	public void onTestStart(ITestResult result) {
		
	  test =	extentReports.createTest(result.getMethod().getMethodName());
	  threadLocal.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		threadLocal.get().log(Status.PASS, "Test Passed");
	
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		

		threadLocal.get().fail(result.getThrowable());

		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String filePath = null;

		
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		threadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}



	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
