package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ReusableComponents;

public class ProductCatalogue extends ReusableComponents{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=By.cssSelector(".col-lg-4");
	By addToCart=By.xpath(".//button[2]");// .card-body button:last-of-type
	By addToCartMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitforElementtoAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName )
	{
		WebElement prod = products.stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
public void addProductToCart(String productName) throws InterruptedException
{
	WebElement prod=getProductByName(productName);
	prod.findElement(addToCart).click();
	waitforElementtoAppear(addToCartMessage);
	waitforElementtoDisappear(spinner);

}
	}


