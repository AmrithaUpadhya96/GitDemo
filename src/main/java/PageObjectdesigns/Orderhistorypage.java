package PageObjectdesigns;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class Orderhistorypage extends AbstractComponents {

	WebDriver driver;
	public Orderhistorypage(WebDriver driver)
	{
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> OrderedProducts;
	
	@FindBy(xpath="//tbody/tr/th")
	List<WebElement> allOrderslist;
	
	public long findYourOrder(String ordernumber)
	{
		long orderfound=allOrderslist.stream().filter(order->order.getText().contains(ordernumber)).count();
		return orderfound;
	}
	
	public Boolean verifyProductsInOrdersPage(String productName)
	{
		Boolean match = OrderedProducts.stream()
				.anyMatch(OrderedProduct -> OrderedProduct.getText().equalsIgnoreCase(productName));
        return match;
	}
	
}
