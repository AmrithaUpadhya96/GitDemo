package PageObjectdesigns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents {

	WebDriver driver;
	
	public checkOutPage(WebDriver driver)
	{  
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement Countryfield;
	
	By countrylist= By.cssSelector(".ta-results");
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	
//	@FindBy(css=".actions a")
//	WebElement placeOrderbutton;
	
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement placeOrderbutton;
	
	@FindBy(css=".hero-primary")
	WebElement confirmMsg;
	

	
	public Confirmationpage SelectCountryandplaceorder(String Country)
	{
		Actions a = new Actions(driver);
		//enter the country name
				a.sendKeys(Countryfield,Country).build().perform();
				waitforvisibilityOfElementLocated(countrylist);
				SelectCountry.click();
				a.click(placeOrderbutton).build().perform();
		//click on place order
				Confirmationpage confirmationpage= new Confirmationpage(driver);
				return confirmationpage;
				
	}
	
	public Confirmationpage placeOrder()
	{           waitForElementToBeClickable(placeOrderbutton);
                placeOrderbutton.click();
		        Confirmationpage confirmationpage= new Confirmationpage(driver);
				return confirmationpage;
	}
	
	
	
}
