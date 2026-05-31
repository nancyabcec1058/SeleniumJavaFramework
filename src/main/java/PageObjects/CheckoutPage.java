package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ReusableComponents;

public class CheckoutPage extends ReusableComponents {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder*='Select Country']")
	WebElement country;
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectIndia;
	@FindBy(css = "[class*='btnn action__submit']")
	WebElement submit;
	By results=By.cssSelector("[class*='ta-results']");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitforElementtoAppear(results);
        selectIndia.click();
		
	}
	public ConfirmationPage submitOrder() throws InterruptedException
	{
		
		((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", submit);
		//submit.click(); updated
		return new ConfirmationPage(driver);
	}

}
