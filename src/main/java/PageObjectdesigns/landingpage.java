package PageObjectdesigns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class landingpage extends AbstractComponents{

 public	WebDriver driver;
	

	public landingpage(WebDriver driver)
	{   super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//driver .findElement(By.id("userEmail")).sendKeys("amritha.upadhya@gmail.com");

	//driver .findElement(By.id("userPassword")).sendKeys("PizzaPasta@10");

	//driver .findElement(By.id("login")).click();
		
@FindBy(id="userEmail")
WebElement userenamil;

@FindBy(id="userPassword")
WebElement userPassword;

@FindBy(id="login")
WebElement loginbutton;

@FindBy(xpath="//div[contains(@class,'toast-message')]")
WebElement errorMessage;

public Purchasepage login(String username,String pwd)
{
	userenamil.sendKeys(username);
	userPassword.sendKeys(pwd);
	loginbutton.click();
	 Purchasepage Purchasepage = new Purchasepage(driver);
	 return Purchasepage;
}
	
public String errorLogin()
{
	waitforvisibilityOfElement(errorMessage);
	return errorMessage.getText();
}

public void goTo()
{
driver.get("https://rahulshettyacademy.com/client");	
}	
}
