package BrowserFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import Modules.ListView;
import Modules.PoppaCommands;
import Modules.SignIn;
import Modules.TrafficParameterSettings;
import Reports.commonUtils;
import Reports.controllerStatusReport;
import Reports.cycleTimingReport;
import Reports.reportSwitchClass;
import Utilites.ExcelUtility;
import Utilites.Listeners;
import Utilites.fileExistAfterDownload;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory extends Listeners {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private SignIn signin;
	private ListView ls;
	private TrafficParameterSettings tp;
	private PoppaCommands pc;
	private ExcelUtility ex;
	private commonUtils cReportUtils;
	private controllerStatusReport csr;
	private cycleTimingReport ctr;
	private fileExistAfterDownload fileExsit;
	private reportSwitchClass rpt;

	public void SetDriver() throws Exception
	{

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		String path=System.getProperty("user.dir")+"\\excel\\te.xlsx";
//		String path=System.getProperty("user.dir")+"\\excel\\te.xlsx";
//		System.setProperty("webdriver.chrome.driver", "‪E:\\\\SELENIUM\\\\Selenium IDE\\\\Driver\\\\chrome\\\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\\\chrome\\\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.merge(cap);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		options.setExperimentalOption("prefs", prefs);
		
		String downloadFilepath = System.getProperty("user.dir")+"\\filedownload";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	    chromePrefs.put("profile.default_content_settings.popups", 0);
	    chromePrefs.put("download.default_directory", downloadFilepath);
	    options.setExperimentalOption("prefs", chromePrefs);
		
		WebDriverManager.chromedriver().setup();
//		driver=new ChromeDriver();
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 30);
		 js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.manage().window().maximize();
		driver.get("https://qa.socxo.net/login");
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("sonu@socxo.com");
		}catch(Throwable e)
		{
		
			Assert.assertTrue(false,"NO VPN Connected");
		}
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userId"))).sendKeys("admin");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("Socxo@1234");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']"))).click();
		Thread.sleep(3000);
		signin=new SignIn(driver);
		ls=new ListView(driver);
		tp=new TrafficParameterSettings(driver);
		pc=new PoppaCommands(driver);
		ex=new ExcelUtility();
		cReportUtils=new commonUtils(driver);
		csr=new controllerStatusReport(driver);
		ctr=new cycleTimingReport(driver);
		fileExsit=new fileExistAfterDownload(driver);
		rpt=new reportSwitchClass(driver);
	}

	public WebDriver getDriver()
	{
		return driver;
	}
	public JavascriptExecutor get_JavascriptExecutor()
	{
		return js;
	}
	public SignIn get_JSignIn_WithURL()
	{
		return signin;
	}
	public ListView get_ListView()
	{
		return ls;
	}
	public TrafficParameterSettings get_TrafficParameterSettings()
	{
		return tp;
	}
	public PoppaCommands get_PoppaCommands()
	{
		return pc;
	}
	public ExcelUtility get_ExcelUtility()
	{
		return ex;
	}
	public commonUtils get_commonUtils() 
	{
		return cReportUtils;
	}
	
	public fileExistAfterDownload get_fileExistAfterDownload() 
	{
		
		return fileExsit;
	}
	
	public controllerStatusReport get_controllerStatusReport() 
	{
		
		return csr;
	}
	
	public reportSwitchClass get_reportSwitchClasst() 
	{
		
		return rpt;
	}
	public cycleTimingReport get_cycleTimingReport() 
	{
		
		return ctr;
	}
}

