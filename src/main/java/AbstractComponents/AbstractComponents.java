package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectdesigns.CartPage;
import PageObjectdesigns.Orderhistorypage;

public class AbstractComponents {
    WebDriver driver;
    WebDriverWait wait;
  		
  		@FindBy(css="[routerlink*='cart']")
  		WebElement cartHeaderbutton;
  		
  		@FindBy(css="[routerlink*='myorders']")
  		WebElement orderHeaderbutton;
  		  			
  			@FindBy(xpath="//button[text()=' Sign Out ']")
  			WebElement signoutbutton;
  			
	public AbstractComponents(WebDriver driver) 
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

    public void waitforvisibilityOfElementLocated(By locator)
    {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public void waitforvisibilityOfElementLocated(List<WebElement> elements)
    {
    	wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
    
    public void waitforvisibilityOfElement(WebElement element)
    {
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
	
    public void waitForInvisibilityOfElementLocated(WebElement webelement)
    {
    	wait.until(ExpectedConditions.invisibilityOf(webelement));
    }
    
    public void waitForElementToBeClickable(WebElement webelement)
    {
    	wait.until(ExpectedConditions.elementToBeClickable(webelement));
    }
    
    public CartPage goToCartPage()
    {
    	cartHeaderbutton.click();
    	CartPage cartpage= new 	CartPage(driver);
    	return cartpage;
    }
    
    public Orderhistorypage goToOrderPage()
    {
    	orderHeaderbutton.click();
    	Orderhistorypage Orderhistorypage= new 	Orderhistorypage(driver);
    	return Orderhistorypage;
    }
    
    public void signOut()
    {
    	signoutbutton.click();
    }
}
