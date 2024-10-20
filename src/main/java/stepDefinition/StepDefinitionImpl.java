package stepDefinition;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.CartPage;
import pageObject.CheckoutPage;
import pageObject.ConfirmationPage;
import pageObject.LandingPage;
import pageObject.ProductCatalog;
import testComponent.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	
	public ProductCatalog productCatalog;
	
	public ConfirmationPage confirmationPage;
	
	
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		landingPage = lunchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		
		 productCatalog = landingPage.loginPage(username, password);

		
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException{
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
		
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
		
		
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_confirmationPage(String string) {
		

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String string) throws InterruptedException {

		//Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
