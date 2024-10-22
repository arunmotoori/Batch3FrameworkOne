package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName()+" test has started execution");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName()+" test got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Screenshot is taken");
		System.out.println(result.getName()+" test got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getName()+" test got skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Execution of all the TestNG Automation scripts has started");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Execution of all the TestNG Automation scripts has completed");
	}
	
}
