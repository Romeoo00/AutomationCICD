package pageObject;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//pageFactory
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalog loginPage(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
		
	}
	
	
	public String getErrorMessage() throws InterruptedException {
		waitForWebElementToAppear(errorMessage);
		Thread.sleep(3000);
		return errorMessage.getText();
	}
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}
	
	
	
	
	
	
	
	
	

}
