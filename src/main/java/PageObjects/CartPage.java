package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ReusableComponents;

public class CartPage extends ReusableComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button") // .cartSection h3
	WebElement checkoutButton;

	@FindBy(xpath = "//*[@class='cartSection']/h3")
	List<WebElement> cartProducts;

	public boolean verifyCartProductexist(String productName) {

		boolean cartProd = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return cartProd;
	}

	public CheckoutPage checkoutProduct() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}

}
