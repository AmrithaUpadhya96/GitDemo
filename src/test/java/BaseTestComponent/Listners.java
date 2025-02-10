package BaseTestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listners extends BaseTest implements ITestListener{

	ExtentReports extentreport;
	ExtentTest test;
	WebDriver driver;
    ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);// methods will not work if you leave this statement hence commented
//		extentreport = extentreportgeneration();
		test=extentreport.createTest(result.getMethod().getMethodName());
		extentTestThread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		extentTestThread.get().log(Status.PASS, "this method is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailure(result);
	      String filepath = null;
		
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTestThread.get().fail(result.getThrowable());
		//get screenshot and add in the report
		try {
			 filepath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTestThread.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		extentreport.flush();
		System.out.println("Test Suite execution completed");
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);// methods will not work if you leave this statement
		System.out.println("Test Suite started");
		extentreport = extentreportgeneration();
     //	test=extentreport.createTest(result.getMethod().getMethodName());
		
	}
	
	

}
