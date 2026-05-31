package stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LoginPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchasestepDefinition extends BaseTest {
	public LoginPage loginpage;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage;   
	public ConfirmationPage confirmationpage;
	public CheckoutPage checkoutpage;

	    @Given("I landed on ecommerce page")
	    public void i_landed_on_ecommerce_page() throws IOException {
	    	loginpage=launchBrowser();
	    }

	    @Given("^Login with (.+) and (.+)$")
	    public void login_with_username_and_password(String username, String password) {
	        productcatalogue = loginpage.loginApplication(username, password);
	    }

	    @When("^I add (.+) to cart$")
	    public void i_add_product_to_cart(String productName) throws InterruptedException {
	    	List<WebElement> products = productcatalogue.getProductList();
			productcatalogue.addProductToCart(productName);
	    }

	    @When("^checkout (.+) and submit the order$")
	    public void checkout_and_submit_the_order(String productName) throws InterruptedException {
	    	cartpage = productcatalogue.goToCart();

			boolean match = cartpage.verifyCartProductexist(productName);
			Assert.assertTrue(match);

			checkoutpage = cartpage.checkoutProduct();
			checkoutpage.selectCountry("India");

			confirmationpage = checkoutpage.submitOrder();
	    }

	    @Then("{string} message is displayed on confirmation page")
	    public void message_is_displayed_on_confirmation_page(String expectedMessage) {
	    	String confirmMessage = confirmationpage.verifyConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			driver.quit();
	        
	    }
	    @Then("{string} message is displayed on login page")
	    public void message_is_displayed_on_login_page(String expectedMessage) {

	        String actualMessage = loginpage.getErrorMessage();

	        Assert.assertEquals(actualMessage, expectedMessage);
	        driver.quit();
	    }
	}

