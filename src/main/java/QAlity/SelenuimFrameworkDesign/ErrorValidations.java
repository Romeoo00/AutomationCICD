package QAlity.SelenuimFrameworkDesign;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
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
import pageObject.ProductCatalog;
import testComponent.BaseTest;
import testComponent.Retry;

public class ErrorValidations extends BaseTest {

		
		
		
		
	@Test(retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {
		

		String productName = "ZARA COAT 3";		
		ProductCatalog productCatalog = landingPage.loginPage("romeomiller@academy.", "Password@1234");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		
		
	}
	
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		

		String productName = "ZARA COAT 3";		
		ProductCatalog productCatalog = landingPage.loginPage("romeomiller@academy.com", "Password@1234");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
