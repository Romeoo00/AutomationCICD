package testComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageObject.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	
	public LandingPage landingPage;
	
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
		properties.load(fileInputStream);
		String browserName =  properties.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			

		    driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			
		}else {
			
			return null;
		}
		return driver;
	}
	
	
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File file = new File( "/Users/abdrahamanekeita/eclipse-workspace/SelenuimFrameworkDesign/reports/ " + testCaseName + ".png");
		FileUtils.copyFile(sourceFile, file);
		return ( "/Users/abdrahamanekeita/eclipse-workspace/SelenuimFrameworkDesign/reports/ " + testCaseName + ".png");
	}
	
	
	@BeforeMethod
	public LandingPage lunchApplication() throws IOException {
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		
	}
	
	
	

}
