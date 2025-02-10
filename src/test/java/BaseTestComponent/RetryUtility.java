package BaseTestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryUtility implements IRetryAnalyzer {
	int count=0;
	int maxretrycount=1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxretrycount)
		{
			count++;
			return true;
		}
		return false;
	}

}
