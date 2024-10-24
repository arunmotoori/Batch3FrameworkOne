package util;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

public class ExtentReporter {
	
	public static ExtentReports getExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\reports\\TNExtentReport.html");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		ExtentSparkReporterConfig sparkConfig = sparkReporter.config();
		sparkConfig.setReportName("Tutorials Ninja Test Automation Results");
		sparkConfig.setDocumentTitle("TNER Results");
		
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("OS",System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		extentReport.setSystemInfo("Username",System.getProperty("user.name"));
		extentReport.setSystemInfo("Selenium WebDriver Version","4.24.0");
		
		return extentReport;
		
	}

}
