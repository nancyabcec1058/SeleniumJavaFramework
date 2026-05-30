package Testsite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LoginPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import Utilities.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchaseOrder" })
	public void submitOrder(HashMap<String, String> data) throws IOException, InterruptedException {
		// public void submitOrder(String mail,String pswd,String productName) for without hashmap
		ProductCatalogue productcatalogue = loginpage.loginApplication(data.get("mail"), data.get("pswd"));

		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(data.get("productName"));
		CartPage cartpage = productcatalogue.goToCart();

		boolean match = cartpage.verifyCartProductexist(data.get("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutpage = cartpage.checkoutProduct();
		checkoutpage.selectCountry("India");

		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMessage = confirmationpage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void checkOrderHistory() {
		ProductCatalogue productcatalogue = loginpage.loginApplication("nandini@mymail.com", "Abc&xyz123");
		OrderPage orderpage = productcatalogue.goToMyOrder();

		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	}

	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\TestData\\PurchaseOrder.json");
				return new Object[][] { { data.get(0) }, { data.get(1) } };
		
		
		
	/*	Map<String, String> map = new HashMap<>();
		map.put("mail", "mukti@myemail.com");
		map.put("pswd", "Abc&xyz123");
		map.put("productName", "ZARA COAT 3");

		Map<String, String> map1 = new HashMap<>();
		map1.put("mail", "nandini@mymail.com");
		map1.put("pswd", "Abc&xyz123");
		map1.put("productName", "ADIDAS ORIGINAL");
		return new Object[][] { { map }, { map1 } };*/
		// return new Object[][] {{"mukti@myemail.com","Abc&xyz123","ZARA COAT 3"},
		// {"nandini@mymail.com", "Abc&xyz123","ADIDAS ORIGINAL"}};
	}

}
