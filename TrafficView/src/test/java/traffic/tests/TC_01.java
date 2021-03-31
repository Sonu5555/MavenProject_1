package traffic.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BrowserFactory.WebDriverFactory;
import Modules.ListView;
import Modules.SignIn;
import Modules.TrafficParameterSettings;

public class TC_01 {
	WebDriverWait wait;
	private ThreadLocal<WebDriver> webdriver = new ThreadLocal<WebDriver>();
	private ThreadLocal<JavascriptExecutor> jsexec = new ThreadLocal<JavascriptExecutor>();
	private ThreadLocal<SignIn> signin = new ThreadLocal<SignIn>();
	private ThreadLocal<ListView> ls = new ThreadLocal<ListView>();
	private ThreadLocal<TrafficParameterSettings> tp=new ThreadLocal<TrafficParameterSettings>();

	public static String expectedMode;
	public static String expectedRtctime;
	public static String expectedPlan;
	public static List<String> expectedPhase;
	
	public static String DB_Mode;
	public static String DB_Plan;
	public static List<String> DB_Phase;
	
	@BeforeMethod
	public void setUpBrowser() throws Exception {
		WebDriverFactory webDriverFactory = new WebDriverFactory();
		webDriverFactory.SetDriver();
		webdriver.set(webDriverFactory.getDriver());
		jsexec.set(webDriverFactory.get_JavascriptExecutor());
		signin.set(webDriverFactory.get_JSignIn_WithURL());
		ls.set(webDriverFactory.get_ListView());
		tp.set(webDriverFactory.get_TrafficParameterSettings());
	}

//	String path=System.getProperty("user.dir")+"\\reports\\index.html";
	String path=System.getProperty("user.dir")+"\\excel\\te.xlsx";
//	String path = "C:\\Users\\ASUS\\Desktop\\te.xlsx";

	@DataProvider(name = "excelData_Controller")
	public Object[][] readExcelSignIn() throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Controller");
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> datamap = new HashMap<>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
			}
			obj[i][0] = datamap;
			System.out.println("datamap : " + datamap);
		
		}
		return obj;

	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "excelData_Controller")
	public void TC_01_VerifyControllerPrperty(HashMap<String, String> map) throws Exception {
		 String mode;
		String rtctime;
		 String plan;
		List<String> phase;
		System.out.println(" Threads : " + Thread.currentThread().getId());
		System.out.println("1" + map.get("Username") + "" + map.get("Password") + "" + map.get("ControllerName") + ""
				+ map.get("Zone"));

		
		Thread.sleep(2000);

//		jsexec.get().executeScript("window.open();");
//		System.out.println("inside js");
//		Thread.sleep(2000);
//		ArrayList<String> al = new ArrayList<String>(webdriver.get().getWindowHandles());
//		webdriver.get().switchTo().window(al.get(1));
//		Thread.sleep(2000);
//		try {
////			webdriver.get().get("https://172.19.71.146:8085/TraffView/");
//			webdriver.get().get("https://qa.socxo.net/login");
//		} catch (Throwable e) {
//			Assert.assertTrue(false);
//		}
//
//		// SIGN IN
//		signin.get().WithURL(map.get("Username"), map.get("Password"));
//
//		
//		// LIST VIEW
//
//		 ls.get().Get_ControllerPrpoerties(map.get("ControllerName"),map.get("Zone"));
//		 rtctime=ls.get().RTC_time();
//		 System.out.println("rct test from UI is :"+rtctime);
//		 String weekDay=ls.get().WeekDay();
//		 mode=ls.get().Mode();
//		 
//
////		 System.out.println("Mode in main page"+mode);
//		 phase=ls.get().Phase();
//		 System.out.println("Mainpage weekday test"+weekDay);
//		 plan=ls.get().Plan();
////		 System.out.println("mainpage phase: "+phase);
////		 System.out.println("mainpage PLAN: "+plan);
////		 System.out.println("Main page mode test"+mode);
////		 
//	
////		 
//		
//		 // TRAFFIC PARAMETER 
//		 
////		 System.out.println("mm1"+mode);
//		 tp.get().Select_TrafficParameterSetting(map.get("ControllerName"));
//		 System.out.println(" tp 1 ");
//		 tp.get().Get_WeekdayPlan(weekDay,rtctime);
//		 System.out.println(" tp 2 ");
////		 tp.get().Verify_mode(mode);
//		 DB_Mode=tp.get().Verify_mode(mode);
//		 System.out.println("DBMODE"+DB_Mode);
//		 System.out.println(" tp 3 ");
////		 tp.get().Verify_Phases(phase,mode,plan,map.get("ControllerName"));
//		 System.out.println(" tp 4 ");
//		 try {
//			 DB_Phase=tp.get().Verify_Phases(phase,mode,plan,map.get("ControllerName"));
//			 }catch (NullPointerException e) {
//				 DB_Phase=null;
//			}
////		 tp.get().Verify_Plan(plan,mode);
//		 System.out.println(" tp 5 ");
//		 DB_Plan=tp.get().Verify_Plan(plan,mode);
//		 System.out.println("dbplan : "+DB_Plan);
//		 
//		
//		 expectedRtctime=rtctime;
//		 expectedPlan=plan;
//		 expectedPhase=phase;
//		 expectedMode=mode;
		
//
//		 if(DB_Mode.equals(expectedMode) && DB_Plan.equals(expectedPlan))  {
//			 System.out.println();
//		 }
//		 else {
//			 tp.get().softassertion.assertAll(); 
//		 }
//		
//		 webdriver.get().quit();
//
//	}
		
		// SOCXO
		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@id='blurb_container'])[1]"))).click();
		webdriver.get().findElement(By.xpath("(//li[@id='blurb_container'])[1]")).click();
		System.out.println("Finished");
		
		webdriver.get().quit();
	}
	public void after_test() throws IOException  
	{  

		webdriver.get().quit();
	    }
}