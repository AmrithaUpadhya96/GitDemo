package seleniumframework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.tools.FileObject;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseTestComponent.BaseTest;
import PageObjectdesigns.Purchasepage;
import PageObjectdesigns.landingpage;


public class Errorlogin extends BaseTest{

//	@Test(groups= {"login feature"}, dataProvider="errorlogindata")
//	public void errorLogin(String username,String pwd) throws Exception
//	{
//		//landingpage landingpage=launchApplication();
//		landingpage.login(username,pwd);
//		String errormsg=landingpage.errorLogin();
//		Assert.assertEquals("Incorrect email or password.",errormsg);
//		
//	} valid test
	
	@Test(groups= {"login feature"}, dataProvider="errorlogindata2")
	public void errorLogin2(HashMap<String,String> hm) throws Exception
	{
		//landingpage landingpage=launchApplication();
		landingpage.login(hm.get("username"),hm.get("password"));
		String errormsg=landingpage.errorLogin();
		
		Assert.assertEquals(errormsg,"Incorrect email or password.");
		
	}
	
	@DataProvider(name="errorlogindata")
	public Object[][] errorlogindata()
	{
		//normal
		return new Object[][] {{"amritha.abdn@gmail.com","Pizzoooasta@10"},{"amritha.upadhya@gmail.com","Pizzoooasta@10"}};
	}
	
	
	@DataProvider(name="errorlogindata1")
	public Object[][] errorlogindata1()
	{
		
		//using hashmap
		HashMap<String,String> hm1= new HashMap<String, String>();
		hm1.put("username", "aooo.abdn@gmail.com");
		hm1.put("password", "Pizzoooasta@10");
		
		
		HashMap<String,String> hm2= new HashMap<String, String>();
		hm2.put("username", "amritha.upadhya@gmail.com");
		hm2.put("password", "Pikacho");
		
		return new Object[][] {{hm1},{hm2}};
	}
	
	@DataProvider(name="errorlogindata2")
	public Object[][] errorlogindata2() throws IOException
	{
		
		//using json data reader util in BaseTest
		List<HashMap<String,String>> data=Getdata(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Errorlogindata.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}
