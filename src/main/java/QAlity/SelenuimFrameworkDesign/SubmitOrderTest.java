package QAlity.SelenuimFrameworkDesign;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.DataReadear;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.CartPage;
import pageObject.CheckoutPage;
import pageObject.ConfirmationPage;
import pageObject.LandingPage;
import pageObject.OrderPage;
import pageObject.ProductCatalog;
import testComponent.BaseTest;

public class SubmitOrderTest extends BaseTest {

	
	String productName = "ZARA COAT 3";		

		
		
		
		
	@Test(dataProvider = "getData" , groups = {"Purchase"})
	public void submitTest(HashMap<String, String> input) throws InterruptedException, IOException {
		

		ProductCatalog productCatalog = landingPage.loginPage(input.get("email"), input.get("password"));
		
		
		//ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	
	@Test(dependsOnMethods = {"submitTest"})
	public void OrderHistoryTest() {
		
		ProductCatalog productCatalog = landingPage.loginPage("romeomiller@academy.com", "Password@1234");
		OrderPage orderPage = productCatalog.goToOrderPage();
		Assert.assertTrue( orderPage.VerifyOrderDisplay(productName));

		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		DataReadear dataReadear = new DataReadear();
		List<HashMap<String, String>> data = dataReadear.getJasonDataToMap("/Users/abdrahamanekeita/eclipse-workspace/SelenuimFrameworkDesign/src/main/java/data/PurchaseOrder");
		
		return new Object[][] {{data.get(0)}, {data.get(1)} }; 
		
	}
		
}
		
//		HashMap<String, String> map = new HashMap<>();
//				map.put("email", "romeomiller@academy.com");
//				map.put("password", "Password@1234");
//				map.put("product", "ZARA COAT 3");
//				
//				
//				
//		HashMap<String, String> map1 = new HashMap<>();
//		        map1.put("email", "romeomiller@academy.com");
//		        map1.put("password", "Password@1234");
//		        map1.put("product", "ADIDAS ORIGINAL");
//							
//				return new Object[][]  { {map}, {map1} };
//			}
//;		
//		return new Object[][]  { {map}, 
//			{"romeomiller@academy.com", "Password@1234", "ADIDAS ORIGINAL"} };

	
	
	
	
