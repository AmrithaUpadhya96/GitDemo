package seleniumframework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTestComponent.*;
import PageObjectdesigns.CartPage;
import PageObjectdesigns.Confirmationpage;
import PageObjectdesigns.Orderhistorypage;
import PageObjectdesigns.Purchasepage;
import PageObjectdesigns.checkOutPage;
import PageObjectdesigns.landingpage;

public class Purchaseordersuccess extends BaseTest {
	String productName = "ZARA";
//	@Test(groups= {"login feature"},retryAnalyzer=RetryUtility.class)
//	public void successfulLogin() throws Exception
//	{
//		//landingpage landingpage=launchApplication(); before method is called
//		Purchasepage Purchasepage = landingpage.login("amritha.upadhya@gmail.com","PizzaPasta@10");
//		long itemsvisibileonpage=Purchasepage.LandonHomePage();
//		if(itemsvisibileonpage>=1)
//		{
//			Assert.assertTrue(true);
//		}
//	}
	
	
	@Test(groups= {"e-Functional"}, retryAnalyzer = RetryUtility.class)
	public void Ordersubmit() throws Exception
	{
	    	Purchasepage Purchasepage = landingpage.login("amritha.upadhya@gmail.com","PizzaPasta@10");
            
			WebElement prod = Purchasepage.selectProduct(productName);
			Purchasepage.addProductToCart(prod);
			
			
			CartPage cartpage= Purchasepage.goToCartPage();
			Boolean match=cartpage.verifyProductsInCart(productName);
            Assert.assertTrue(match);
            
            checkOutPage checkOutPage=cartpage.checkOutProduct();
            Confirmationpage Confirmationpage =  checkOutPage.SelectCountryandplaceorder("India");
            String confirmMessage=Confirmationpage.confirmOrder();
            Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
            
			String orderNumber=Confirmationpage.getOrderNumber();
			Orderhistorypage Orderhistorypage=Confirmationpage.goToOrderHistory();
			long orderfound=Orderhistorypage.findYourOrder(orderNumber);
		    Assert.assertTrue(orderfound==1);
		    Orderhistorypage.signOut();
	      
	}
	

    @Test(groups= {"e-Functional"})
    public void productVerifyFail() throws Exception
    {
		Purchasepage Purchasepage = landingpage.login("amritha.upadhya@gmail.com","PizzaPasta@10");
        
		WebElement prod = Purchasepage.selectProduct(productName);
		Purchasepage.addProductToCart(prod);
		
		
		CartPage cartpage= Purchasepage.goToCartPage();
		Boolean match=cartpage.verifyProductsInCart("invalidProductName");
        Assert.assertFalse(match);
    }
    
    @Test(groups= {"e-Functional"}, dependsOnMethods= {"Ordersubmit"})
    public void verifyOrderPageForProduct()
    {
    	Purchasepage Purchasepage = landingpage.login("amritha.upadhya@gmail.com","PizzaPasta@10");
    	Orderhistorypage Orderhistorypage=Purchasepage.goToOrderPage();
    	Boolean match=Orderhistorypage.verifyProductsInOrdersPage(productName);
    	Assert.assertTrue(match);
    	
    }

}
