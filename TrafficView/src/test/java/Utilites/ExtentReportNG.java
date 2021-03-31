package Utilites;

import java.util.Locale;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNG {
private static ExtentReports extent;
	
	public static ExtentReports extentReportGenerator() 
	{
//		String filename=getReportName();
//		String directory=System.getProperty("user.dir")+"/reports/";
//		new File(directory).mkdirs();
//		String path=directory + filename;
	String path=System.getProperty("user.dir")+"\\reports\\index.html";
//	ExtentSparkReporter reporter=new ExtentSparkReporter(path);
//		reporter.config().setEncoding("utf-8");
//		reporter.config().setReportName("Automation Test Results");
//		reporter.config().setDocumentTitle("Automation Reports");
//		reporter.config().setTheme(Theme.STANDARD);
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);	
		htmlReporter.setAppendExisting(true);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		Locale.setDefault(Locale.ENGLISH);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Tester", "Sonu");
		
		
		
		return extent;
	}
	
//private static String getReportName() {
//		
//		Date d = new Date();
////		String filename = "AutomationReport_" + d.toString().replace(":", "_").replace(" ", "")+".html";
//		String filename = "AutomationReport_" + d.toString().replace(":", "_").replace(" ", "")+".html";
//		return filename;
//	
//	}
}
