package Utilities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends ReusableComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> ProductNames;
	
	public boolean verifyOrderDisplay(String productName) {

		boolean match = ProductNames.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return match;
	}

}
