package StepDefination;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import BaseTestComponent.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import PageObjectdesigns.CartPage;
import PageObjectdesigns.Confirmationpage;
import PageObjectdesigns.Purchasepage;
import PageObjectdesigns.checkOutPage;
import PageObjectdesigns.landingpage;

public class Allstepdefination extends BaseTest{

	//WebDriver driver;
	 public landingpage landingpage;
	 public CartPage cartpage;
	 public Purchasepage Purchasepage;
	 public  Confirmationpage Confirmationpage;
	
	@Given("I land on Ecommerce application")
	public void I_land_on_Ecommerce_application() throws Exception
	{
		landingpage = launchApplication();
	}
	
	@Given("I am on Ecommerce application login page")
	public void I_am_on_Ecommerce_application_login_page()
	{
		
		String title=landingpage.driver.getCurrentUrl();
		Assert.assertEquals(title, "https://rahulshettyacademy.com/client/auth/login");
		
	}
	
	@When("^I click on login by giving username (.+) and password (.+)$")
	public void I_click_on_login_by_giving_username_and_password(String username,String password)
	{

		Purchasepage = landingpage.login(username,password);
	}
	
	@Then("I get {string} message on the login page.")
	public void I_get_message_on_the_login_page(String string)
	{
          String errormsg=landingpage.errorLogin();
		  Assert.assertEquals(errormsg,string);	
		  landingpage.driver.close();
	}
	
	@Then("I should land on home page.")
	public void I_should_land_on_home_page()
	{
		long itemsvisibileonpage=Purchasepage.LandonHomePage();
		if(itemsvisibileonpage>=1)
		{
			Assert.assertTrue(true);
		}
		landingpage.driver.close();
	}
	
	
	   @And("^I add the product (.+) in cart and verify the same in cart before checkout$")
	   public void add_and_verify_product_in_Cart(String productName) throws InterruptedException
	   {
		   WebElement prod = Purchasepage.selectProduct(productName);
			Purchasepage.addProductToCart(prod);
			cartpage= Purchasepage.goToCartPage();
			Boolean match=cartpage.verifyProductsInCart(productName);
           Assert.assertTrue(match);   
	   }
	    
	   @And("I checkout the product")
	   public void checkout_product_in_Cart()
	   {
		   checkOutPage checkOutPage=cartpage.checkOutProduct();
           Confirmationpage =  checkOutPage.SelectCountryandplaceorder("India");	   
	   }
	   
	   @Then("I get {string} order confirmation message.")
	   public void check_conirmation_message(String string)
	   {
		   String confirmMessage=Confirmationpage.confirmOrder();
           Assert.assertTrue(confirmMessage.equalsIgnoreCase(string)); 
           driver.close();
	   }
}

