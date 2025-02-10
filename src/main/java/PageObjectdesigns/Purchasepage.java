package PageObjectdesigns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class Purchasepage extends AbstractComponents {

public WebDriver driver;
	

	public Purchasepage(WebDriver driver)
	{   
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//By.cssSelector(".mb-3")
	
@FindBy(xpath="//div[contains(@class,'mb-3')]")
List<WebElement> products;

By eachItemOnPage= By.xpath("//div[@class='card-body']//b");

By itemsOnPage=By.cssSelector(".mb-3");

By addToCartButton=By.cssSelector(".card-body button:last-of-type");

By toastMessage= By.cssSelector("#toast-container");

@FindBy(xpath="//button[contains(@class,'btn btn-custom')]")
List<WebElement> Homepage;

//wait.until(ExpectedConditions.invisibilityOf(driver .findElement(By.cssSelector("WebDriverWait wait"))));
@FindBy(css=".ng-animating")
WebElement animatingIcon;

public List<WebElement> getProductList()
{
	
	return products;
}

public WebElement selectProduct(String productName) throws InterruptedException
{
	Thread.sleep(2000);
	WebElement prod = getProductList().stream().filter(product -> product.findElement(eachItemOnPage).getText().equals(productName)).findFirst().orElse(null);
    return prod;
	//
}

public void addProductToCart(WebElement prod)
{
	prod.findElement(addToCartButton).click();
	waitforvisibilityOfElementLocated(toastMessage); 
	waitForInvisibilityOfElementLocated(animatingIcon);
}

public long LandonHomePage()
{
	waitforvisibilityOfElementLocated(Homepage);
	long itesmonpage=Homepage.stream().count();
	return itesmonpage;
}


}
