package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import util.ExtentReporter;

public class MyListeners implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.getExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" test execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS,result.getName()+" test got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.log(Status.FAIL,result.getName()+" test got failed");
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		TakesScreenshot tsDriver = (TakesScreenshot)driver;
		File srcScreenshot = tsDriver.getScreenshotAs(OutputType.FILE);
		String screenshotFilePath = null;
		try {
			screenshotFilePath = System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
			FileHandler.copy(srcScreenshot,new File(screenshotFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		extentTest.addScreenCaptureFromPath(screenshotFilePath);
		
		extentTest.log(Status.INFO,result.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP,result.getName()+" test got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\reports\\TNExtentReport.html");
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
