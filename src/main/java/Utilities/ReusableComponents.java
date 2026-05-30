package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;

public class ReusableComponents {
	
	WebDriver driver;
	public ReusableComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	@FindBy(css="[routerlink$='myorders']")
	WebElement myOrders;
	
	public void  waitforElementtoAppear(By element)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
	wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public void  waitforWebElementtoAppear(WebElement element)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
	wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void  waitforElementtoDisappear(WebElement element) throws InterruptedException
	{
		Thread.sleep(1000);
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
	//wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCart()
	{
		cartButton.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	public OrderPage goToMyOrder()
	{
		myOrders.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}

}
