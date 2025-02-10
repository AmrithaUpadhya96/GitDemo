package PageObjectdesigns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class Confirmationpage extends AbstractComponents {

	WebDriver driver;
	public Confirmationpage(WebDriver driver)
	{
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="hero-primary")
	WebElement ConfirmationMessage;
	
	@FindBy(xpath="//tr[3]/td/label[1]")
	WebElement orderNumber;
	
	@FindBy(xpath="//tr[2]/td/label[1]")
	WebElement orderHistoryLink;
	
	public String confirmOrder()
	{
		return ConfirmationMessage.getText();
		
	}
	
	
	public String getOrderNumber()
	{
		return orderNumber.getText().split(" ")[1];	
	}
	
	public Orderhistorypage goToOrderHistory() throws InterruptedException
	{
		orderHistoryLink.click();
		Orderhistorypage Orderhistorypage= new Orderhistorypage(driver);
		Thread.sleep(1000);
		return Orderhistorypage;
	}
	
}

