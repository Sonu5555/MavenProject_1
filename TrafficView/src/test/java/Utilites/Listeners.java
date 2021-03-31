package Utilites;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import traffic.tests.TC_01;
import traffic.tests.TC_02;
import traffic.tests.TC_03;


public class Listeners implements ITestListener{

	ExtentReports extent = ExtentReportNG.extentReportGenerator();
	ExtentTest test;
	TC_01 t;
	TC_02 t2;
	TC_03 t3;
	private WebDriver driver;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public Listeners()
	{
		this.t=new TC_01();
		this.t2=new TC_02();
		this.t3=new TC_03();
	}
	String mode;
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void onTestSuccess(ITestResult result) {
	
//		String expectedvalue="Expected Values - "+" Mode :"+t.mode+" "+" Plan :"+t.plan+" "+" Phase :"
//		+t.phase+" RTC time :"+t.rtctime;
		if(result.getMethod().getMethodName().equals("TC_01_VerifyControllerPrperty")) {
		String expectedvalue="Expected Values - "+" Mode : "+t.expectedMode+" "+" | Plan :"+t.expectedPlan+" "+" | Phase :"
				+t.expectedPhase+"| RTC time :"+t.expectedRtctime +"<br>Actual Values - "+" DB_Mode : "+t.DB_Mode+" | DB_Plan : "+t.DB_Plan+" | DB_Phase : "+t.DB_Phase;	
		System.out.println("Inside onTestSuccess"+expectedvalue);
//		String logtext = "Test Success : "+result.getMethod().getMethodName();

		Markup m= MarkupHelper.createLabel(expectedvalue, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
		}
		else if(result.getMethod().getMethodName().equals("TC_02_VerifyPOPPA")) {
			System.out.println("start 1");
			for(int i=0; i < t2.count; i++) {
				System.out.println("count "+t2.count);
				System.out.println("count list_DBmode "+t2.list_DBmode.size());
				System.out.println("count list_afterPOPPA_mode"+t2.list_afterPOPPA_mode.size());
				for(String k : t2.list_DBmode) {
					System.out.println("Inside listener DBMODE :"+t2.list_DBmode);
				}
				for(String k : t2.list_afterPOPPA_mode) {
					System.out.println("Inside listener list_afterPOPPA_mode :"+t2.list_DBmode);
				}
				if(t2.list_DBmode.get(i).equals(t2.list_afterPOPPA_mode.get(i))) {
			String comapredValue="Poppa Command : "+t2.list_POPPACOMMANDS.get(i)
			+"<br>DB mode : "+t2.list_DBmode.get(i)+" | "+"UI Mode : "+t2.list_afterPOPPA_mode.get(i)
			+"<br>RTC Time :"+t2.list_rtcTime.get(i)
			+"<br> Skipped PoppaCmd = "+t2.list_skipPOPPAcommands;
			String logtext = "Test Success : "+result.getMethod().getMethodName();
			System.out.println("start 2");
			Markup m= MarkupHelper.createLabel(comapredValue, ExtentColor.GREEN);
			extentTest.get().log(Status.PASS, m);
			}
				else {
					
					System.out.println("start 3");
					String comapredValue="Poppa Command : "+t2.list_POPPACOMMANDS.get(i)
					+"<br>DB mode : "+t2.list_DBmode.get(i)+" | "+"UI Mode : "+t2.list_afterPOPPA_mode.get(i)
					+"<br>RTC Time :"+t2.list_rtcTime.get(i)
					+"<br> Skipped PoppaCmd = "+t2.list_skipPOPPAcommands;
					
					String logtext = "Test Success : "+result.getMethod().getMethodName();
					System.out.println("start 4");
					Markup m= MarkupHelper.createLabel(comapredValue, ExtentColor.RED);
					extentTest.get().log(Status.FAIL, m);
				}
			}
		
		}
		else if(result.getMethod().getMethodName().equals("TC_03_VerifyReport")) {
			
			for(int i =0 ; i<t3.count ;i++) {
			String expectedvalue=t3.list_ReportName.get(i)+" "+"Success";
			Markup m= MarkupHelper.createLabel(expectedvalue, ExtentColor.GREEN);
			extentTest.get().log(Status.PASS, m);
			}
		}
		else {
			System.out.println("TEST SUCCESS CLASS ITEST LISTENER");
		}
	}
	@Override
	public void onTestFailure(ITestResult result) {
	
		if(result.getMethod().getMethodName().equals("TC_01_VerifyControllerPrperty")) {
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=Red>" + 
		"Exception Occured Click "+"to see details:"+"</font></b></summary>" +
				exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
		String expectedvalue="Expected Values - "+" Mode : "+t.expectedMode+" "+" | Plan :"+t.expectedPlan+" "+" | Phase :"
				+t.expectedPhase+"| RTC time :"+t.expectedRtctime +"<br>Actual Values - "+" DB_Mode : "+t.DB_Mode+" | DB_Plan : "+t.DB_Plan+" | DB_Phase : "+t.DB_Phase;	
		System.out.println("Inside onTestSuccess"+expectedvalue);		
//		String logtexts = "Method name : "+result.getMethod().getMethodName()+" || "+Arrays.toString(result.getParameters());
		Markup m= MarkupHelper.createLabel(expectedvalue, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
		}
		else if(result.getMethod().getMethodName().equals("TC_02_VerifyPOPPA")) {
			
			for(int i=0; i < t2.count; i++) {
				if(t2.list_DBmode.get(i)!= t2.list_afterPOPPA_mode.get(i)) {
			String logtext = "Test FAIL : "+result.getMethod().getMethodName();
			String comapredValue="Poppa Command : "+t2.list_POPPACOMMANDS.get(i)
			+"<br>DB mode : "+t2.list_DBmode.get(i)+" | "+"UI Mode : "+t2.list_afterPOPPA_mode.get(i)
			+"<br>RTC Time :"+t2.list_rtcTime.get(i)
			+"<br> Skipped PoppaCmd = "+t2.list_skipPOPPAcommands;
			Markup m= MarkupHelper.createLabel(comapredValue, ExtentColor.RED);
			extentTest.get().log(Status.FAIL, m);
			}
				else {
					String comapredValue="Poppa Command : "+t2.list_POPPACOMMANDS.get(i)
					+"<br>DB mode : "+t2.list_DBmode.get(i)+" | "+"UI Mode : "+t2.list_afterPOPPA_mode.get(i)
					+"<br>RTC Time :"+t2.list_rtcTime.get(i)
					+"<br> Skipped PoppaCmd = "+t2.list_skipPOPPAcommands;
					Markup m= MarkupHelper.createLabel(comapredValue, ExtentColor.GREEN);
					extentTest.get().log(Status.PASS, m);
				}
			}
		}
		else if(result.getMethod().getMethodName().equals("TC_03_VerifyReport")) {
			
			for(int i =0 ; i<t3.count ;i++) {
			String expectedvalue=t3.list_ReportName.get(i)+" "+"Success";
			Markup m= MarkupHelper.createLabel(expectedvalue, ExtentColor.RED);
			extentTest.get().log(Status.FAIL, m);
			}
		}
		else {
			System.out.println("TEST FAIL CLASS ITEST LISTENER");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
	
	}
}
