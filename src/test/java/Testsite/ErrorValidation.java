package Testsite;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LoginPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import TestComponents.RetryAnalyzer;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=RetryAnalyzer.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productcatalogue = loginpage.loginApplication("nandii@mymail.com", "Abc&xyz123");
		Assert.assertEquals("Incorrect email or password.", loginpage.getErrorMessage());
		

	}
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productcatalogue = loginpage.loginApplication("mukti@myemail.com","Abc&xyz123");
		
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCart();
		
		boolean match = cartpage.verifyCartProductexist("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
		

	}

}
