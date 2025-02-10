package BaseTestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjectdesigns.landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
 
	 public WebDriver driver;
	 public landingpage landingpage;
	// ThreadLocal<WebDriver> driverThread=new ThreadLocal<WebDriver>();
	
	public WebDriver driverInitializor() throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\globalproperties.properties");
		prop.load(fis);
		prop.setProperty("URL", "https://rahulshettyacademy.com/client/");
		//jenkins username/pwd:AmrithaUpadhya/HiRamaYeh#123
		//java -jar jenkins.war --enable-future-java -httpPort=8085
		//String BrowserName=prop.getProperty("Browser"); which can only get value from globalproperties file but to get system level properities, we can get it from system.property()
		String BrowserName=System.getProperty("Browser") != null ? System.getProperty("Browser") : prop.getProperty("Browser");
		
		if(BrowserName.contains("Chrome"))
		{
		   ChromeOptions options= new ChromeOptions();
		   WebDriverManager.chromedriver().setup();
		     if(BrowserName.contains("headless"))
		     {
			 options.addArguments("headless");
		     }
		   driver = new ChromeDriver(options);
		}
		else if(BrowserName.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			
		}
		else if(BrowserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\Users\\User\\Documents\\Selenium\\msedgedriver.exe");
			 driver = new EdgeDriver(); 
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public landingpage launchApplication() throws Exception
	{
		driver = driverInitializor();
		//driverThread.set(driver);
		landingpage= new landingpage(driver);//send unique thread driver instance
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication()
	{
		driver.close();
		//driverThread.remove();
	}
	
	public List<HashMap<String, String>> Getdata(String filename) throws IOException
	{
		//convert json to String content
		String Stringjsoncontent=FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
	    // convert string to hashmap list using jackson binder
	 ObjectMapper map=new ObjectMapper();
	 List<HashMap<String,String>> data = map.readValue(Stringjsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
	 
	 return data;
	 
	}
	
	
	public String getScreenshot(String testcasename,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"\\FailedImgs\\"+testcasename+".png");
		FileUtils.copyFile(source, target);
		return System.getProperty("user.dir")+"\\FailedImgs\\"+testcasename+".png";
	}
	
	
	public ExtentReports extentreportgeneration()
	{
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(new File(System.getProperty("user.dir")+"\\TestReport\\Errorloginfeature.html"));
		reporter.config().setDocumentTitle("TestResultDocument");
		reporter.config().setReportName("TestResultReport");
		reporter.config().setTheme(Theme.STANDARD);
		
		ExtentReports extentreport=new ExtentReports();
		extentreport.attachReporter(reporter);
		 // Optional system information
		extentreport.setSystemInfo("OS", "Windows 10");
		extentreport.setSystemInfo("Environment", "Testing");
		extentreport.setSystemInfo("User", System.getProperty("user.name"));
		return extentreport;
	}
	
}
