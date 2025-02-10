package seleniumframework.Tests;

import java.time.Duration;

import java.util.List;
//import java.util.stream.Collectors;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.stream.Stream;

//import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class EcommerceAppStandalone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "QWERTY";
		
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("amritha.upadhya@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("PizzaPasta@10");

		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); //products visibility

		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		WebElement prod = products.stream().filter(product ->

		product.findElement(By.xpath("//div[@class='card-body']//b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// ng-animating

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		
		//click on cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
//capture all elements present in the cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
// look for the product added just now in present in the list
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);
//click on chck out button on cart page
		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
//enter the country name
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//wait for the visibility of the list of value when country is entered
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//India is in 2nd position in the list
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//click on place order
		a.click(driver.findElement(By.cssSelector(".actions a"))).build().perform();
//get green msg for success
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//verify thank you message on the page
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//grab the order number
		String ordernumber= driver.findElement(By.xpath("//tr[3]/td/label[1]")).getText().split(" ")[1];
		//click on order page link present on the same page
		driver.findElement(By.xpath("//tr[2]/td/label[1]")).click();
		
		Thread.sleep(2000);
//grab all orders from order page
		List<WebElement> OrderspageOrder= driver.findElements(By.xpath("//tbody/tr/th"));
		// find your latest order
		long orderfound=OrderspageOrder.stream().filter(order->order.getText().contains(ordernumber)).count();
		
		Assert.assertTrue(orderfound==1);
	   //sign out
		driver.findElement(By.xpath("//button[text()=' Sign Out ']")).click();
		
	    driver.close();
	
	}

}









