package data;

import java.security.PublicKey;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extentReports;

	
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir") + "/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setDocumentTitle("Test Result");
		
		
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Tester" , "Abdrahamane Keita");
		
		return extentReports;
		
	}
	
	
	
	

}
