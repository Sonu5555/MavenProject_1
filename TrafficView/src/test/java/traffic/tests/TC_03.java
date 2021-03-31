package traffic.tests;

import org.testng.annotations.Test;

import BrowserFactory.WebDriverFactory;
import Modules.SignIn;
import Reports.commonUtils;
import Reports.controllerStatusReport;
import Reports.cycleTimingReport;
import Reports.reportSwitchClass;
import Utilites.fileExistAfterDownload;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TC_03 {
	private ThreadLocal<WebDriver> webdriver = new ThreadLocal<WebDriver>();
	private ThreadLocal<JavascriptExecutor> jsexec = new ThreadLocal<JavascriptExecutor>();
	private ThreadLocal<SignIn> signin = new ThreadLocal<SignIn>();
	private ThreadLocal<commonUtils> cReportUtils = new ThreadLocal<commonUtils>();
	private ThreadLocal<controllerStatusReport> csr = new ThreadLocal<controllerStatusReport>();
	private ThreadLocal<fileExistAfterDownload> fileExist = new ThreadLocal<fileExistAfterDownload>();
	private ThreadLocal<reportSwitchClass> rpt = new ThreadLocal<reportSwitchClass>();
	private ThreadLocal<cycleTimingReport> ctr = new ThreadLocal<cycleTimingReport>();

	
	public static  List<String> list_ReportName;
	public static int count;
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		WebDriverFactory webDriverFactory = new WebDriverFactory();
		webDriverFactory.SetDriver();
		webdriver.set(webDriverFactory.getDriver());
		jsexec.set(webDriverFactory.get_JavascriptExecutor());
		signin.set(webDriverFactory.get_JSignIn_WithURL());
		cReportUtils.set(webDriverFactory.get_commonUtils());
		csr.set(webDriverFactory.get_controllerStatusReport());
		fileExist.set(webDriverFactory.get_fileExistAfterDownload());
		rpt.set(webDriverFactory.get_reportSwitchClasst());
		ctr.set(webDriverFactory.get_cycleTimingReport());
	}

	String path = System.getProperty("user.dir") + "\\excel\\te.xlsx";

	@DataProvider(name = "excelData_Report")
	public Object[][] readExcelUserReport() throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("UserReport");
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
		String path = System.getProperty("user.dir") + "\\excel\\te.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Report");

		Iterator<Row> rowIterators = sheet.iterator();
		rowIterators.next();

		ArrayList<String> list = new ArrayList<String>();
		while (rowIterators.hasNext()) {
			list.add(rowIterators.next().getCell(colno).getStringCellValue());
		}
		System.out.println("List :::" + list);
		return list;
	}

	@Test(dataProvider = "excelData_Report", priority = 0)
	public void TC_03_VerifyReport(HashMap<String, String> map) throws Exception {
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

		// Report Click
		list_ReportName=new ArrayList<>();
		
		ArrayList<String> reportName=readexceldata(0);
		ArrayList<String> controller=readexceldata(1);
		ArrayList<String> itemType=readexceldata(2);
		
		for(int i=0 ;i <reportName.size(); i++) {
			
		cReportUtils.get().click_Reports();
		String rptName=cReportUtils.get().select_Report(reportName.get(i));
		rpt.get().reportSwitch(reportName.get(i),controller.get(i),itemType.get(i));
		list_ReportName.add(reportName.get(i));

		
		}
		count=reportName.size();
		
		csr.get().softassertion.assertAll();
		System.out.println("Assert 1");
		ctr.get().softassertion.assertAll();
		System.out.println("Assert 2");
		fileExist.get().softassertion.assertAll();
		System.out.println("Assert 3");
		webdriver.get().quit();
	
		

	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
