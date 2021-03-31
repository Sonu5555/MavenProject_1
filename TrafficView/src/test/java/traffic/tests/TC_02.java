package traffic.tests;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ObjectArrays;

import BrowserFactory.WebDriverFactory;
import Modules.ListView;
import Modules.PoppaCommands;
import Modules.SignIn;
import Utilites.ExcelUtility;
import Utilites.ExcelUtilityList;
import Utilites.ExcelUtilityList_row;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.Put;
import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TC_02 {
	private ThreadLocal<WebDriver> webdriver = new ThreadLocal<WebDriver>();
	private ThreadLocal<JavascriptExecutor> jsexec = new ThreadLocal<JavascriptExecutor>();
	private ThreadLocal<SignIn> signin = new ThreadLocal<SignIn>();
	private ThreadLocal<ListView> ls = new ThreadLocal<ListView>();
	private ThreadLocal<PoppaCommands> pc=new ThreadLocal<PoppaCommands>();
	private ThreadLocal<ExcelUtility> ex=new ThreadLocal<ExcelUtility>();
	int g;
	String cycletime_afterValue;
	List<String> List_Key;
	Map<String, String> cycletime_afterAutoValue;
	List list;
	public static  List<String> list_DBmode;
	public static  List<String> list_afterPOPPA_mode;
	public static  List<String> list_rtcTime;
	public static List<String> list_dbModeReports;
	public static List<String> list_skipPOPPAcommands;
	public static List<String> list_afterPOPPA_modeReports;
	public static List<String> list_POPPACOMMANDS;
	String modeDBPOPPA;
	String modeafterPOPPA;
	String returnRTCtime;
	public static int count;
	@BeforeMethod
	public void setUpBrowser() throws Exception {
		WebDriverFactory webDriverFactory = new WebDriverFactory();
		webDriverFactory.SetDriver();
		webdriver.set(webDriverFactory.getDriver());
		jsexec.set(webDriverFactory.get_JavascriptExecutor());
		signin.set(webDriverFactory.get_JSignIn_WithURL());
		ls.set(webDriverFactory.get_ListView());
		pc.set(webDriverFactory.get_PoppaCommands());
		ex.set(webDriverFactory.get_ExcelUtility());
	}
	
	String path=System.getProperty("user.dir")+"\\excel\\te.xlsx";
	@DataProvider(name = "excelData_Controller")
	public Object[][] readExcelPoppaUser() throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("PoppaUser");
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


	public ArrayList<String> readexceldata(int colno) throws IOException {
		String path=System.getProperty("user.dir")+"\\excel\\te.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Poppa");
		
		Iterator<Row> rowIterators=sheet.iterator();
		rowIterators.next();
		
		ArrayList<String> list=new ArrayList<String>();
		while(rowIterators.hasNext()) {
			list.add(rowIterators.next().getCell(colno).getStringCellValue());
		}
		System.out.println("List :::"+list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Test(dataProvider = "excelData_Controller",priority=0)
	public void TC_02_VerifyPOPPA(HashMap<String, String> map) throws Exception{
		jsexec.get().executeScript("window.open();");
		System.out.println("inside js");
		Thread.sleep(2000);
		ArrayList<String> al = new ArrayList<String>(webdriver.get().getWindowHandles());
		webdriver.get().switchTo().window(al.get(1));
		Thread.sleep(2000);
		
		try {
			webdriver.get().get("https://172.19.71.146:8085/TraffView/");
		} catch (Throwable e) {
			Assert.assertTrue(false);
		}

		// SIGN IN
		signin.get().WithURL(map.get("Username"), map.get("Password"));
		
		ArrayList<String> Contoroller=readexceldata(0);
		ArrayList<String> Zone=readexceldata(1);
		ArrayList<String> POPPACMD=readexceldata(2);
		ArrayList<String> timePeriod=readexceldata(3);
		
		//Click ListView
		
		list_DBmode=new ArrayList<>();
		list_afterPOPPA_mode=new ArrayList<>();
		list_afterPOPPA_modeReports=new ArrayList<>();
		list_dbModeReports=new ArrayList<>();
		list_rtcTime=new ArrayList<>();
		list_skipPOPPAcommands=new ArrayList<>();
		list_POPPACOMMANDS=new ArrayList<>();
		System.out.println("Controller size :"+Contoroller.size());
		pc.get().Click_ListView();
		for(int i=0;i<Contoroller.size();i++) 
		
		{
			System.out.println("Time Period Assigned for controller"+Contoroller.get(i)+" is "+
					timePeriod.get(i));
	//		String strMinutes=timePeriod.get(i);
			int minutes = Integer.parseInt(timePeriod.get(i));
			long millis = minutes * 60 * 1000;
		    long result=millis + System.currentTimeMillis();
		System.out.println("Contoroller : "+Contoroller.get(i));
		System.out.println("Zone : "+Zone.get(i));
		System.out.println("POPPACMD : "+POPPACMD.get(i));
		int storeCount=0;
		do {
			storeCount=storeCount+1;
			System.out.println("Loop count : "+storeCount);
				// zone view and controller view
				pc.get().Get_ControllerPrpoerties_POPPA(Contoroller.get(i),Zone.get(i));
				// POPPA COMMANDS
				System.out.println("POPPA"+POPPACMD.get(i));	
				pc.get().click_JunctionView(Contoroller.get(i));
				pc.get().click_lampsOff(POPPACMD.get(i),Zone.get(i),Contoroller.get(i));
				if(pc.get().skipFlag()==0) {
				modeDBPOPPA=pc.get().Verify_Mode(Contoroller.get(i),pc.get().rtcTime());
				list_DBmode.add(modeDBPOPPA);
				modeafterPOPPA=pc.get().returnMode();
				list_afterPOPPA_mode.add(modeDBPOPPA);
				list_rtcTime.add(pc.get().rtcTime());
				System.out.println("Mode Poppa is : "+modeDBPOPPA);
				list_POPPACOMMANDS.add(POPPACMD.get(i));

				}
				else {
					list_skipPOPPAcommands.add(POPPACMD.get(i));
				}
			}while(System.currentTimeMillis() < result);
		}		
//		list_DBmode.add(0, "CableLess");
//		list_DBmode.add(1, "VA");
		for(String y: list_DBmode) {
			System.out.println("DB Mode : "+y);
		}
		
		for(String z: list_afterPOPPA_mode) {
			System.out.println("After POPPa Mode : "+z);
		}
		
		for(String r: list_rtcTime) {
			System.out.println("RTC Time : "+r);
		}
		count=list_afterPOPPA_mode.size();
		System.out.println("Count for Listeners list_afterPOPPA_mode :"+list_afterPOPPA_mode.size());
		System.out.println("Count for Listeners list_DBmode:"+list_DBmode.size());
		System.out.println("Count for Listeners list_rtcTime:"+list_rtcTime.size());
		
		for(int i = 0; i < count ; i++ ) {
			
			if(list_DBmode.get(i).equals(list_afterPOPPA_mode.get(i))) {
				
				System.out.println(list_DBmode.get(i)+": Plans are matching : "+list_afterPOPPA_mode.get(i));
			}
			else {
				pc.get().softassertion.assertAll();
			}
		}
		webdriver.get().quit();
		}	

	
	
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

}
