package Modules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListView {
	WebDriver driver;
	WebDriverWait wait;
//	ExtentReports reports;
//	ExtentTest test;
	List<String> list;
	List<String> list_phase;
	String plan,phase;
	String Modee;
	String planner;
	String RTC_time;
	By Click_Mapview=By.xpath("//li//a[text()='MapView']");
	By Click_ListView = By.xpath("//div[@id='mapDiv']//span");
	By Click_ListView_ExpandArrow = By.xpath("//span[@title='More details']");
	By Click_ListView_LessDeatilsArrow = By.xpath("//span[@title='Less details']");
	By Click_JunctionSearch_textbox = By.xpath("//input[@placeholder='siteId / Junction name']");
	By Click_Close_Button = By.xpath("//a[@data-original-title='close list view']");
	By Click_SiteID_Textbox = By.xpath("//input[@placeholder='siteId / Junction name']");

	// PASS CONTROLLER NAME
	By Select_ZoneName = By.xpath("//span[contains(text(),'TVM')]");
	//By Select_Controller = By.xpath("(//div[@data-zone='TVM'])[1]//span[text()='7914 (7914)']");
	By Select_Controller = By.xpath("(//div[@data-zone='TVM'])[1]//span[text()='Newcontroller (9604)']");

	By Get_JunctionController_Status = By
			.xpath("(//span[text()='Newcontroller (9604)'])[1]//parent::span//parent::td//following-sibling::td[1]");
	By Get_JunctionController_Mode = By
			.xpath("(//span[text()='Newcontroller (9604)'])[1]//parent::span//parent::td//following-sibling::td[2]");
	By Get_JunctionController_Plan = By
			.xpath("(//span[text()='Newcontroller (9604)'])[1]//parent::span//parent::td//following-sibling::td[3]");
	By Get_JunctionController_Phase = By
			.xpath("(//span[text()='Newcontroller (9604)'])[1]//parent::span//parent::td//following-sibling::td[4]");
	By Get_JunctionController_CycleTime = By
			.xpath("(//span[text()='Newcontroller (9604)'])[1]//parent::span//parent::td//following-sibling::td[5]");

	public ListView(WebDriver driver) {
		//this.test=test;
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}
	public void KeyPressUP() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.UP).build().perform();
	}
	public void Get_ControllerPrpoerties(Object object, Object object2) throws Exception {
		String ControllerName = object.toString();
		String zone = object2.toString();
		System.out.println("Inside Get_ControllerPrpoerties :"+ControllerName+" "+zone);
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_ListView)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_Close_Button)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_ListView)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_ListView_ExpandArrow)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[1]"))).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'"+zone+"')]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		Thread.sleep(1000);
		element.click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		WebElement controller_element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@data-zone='"+zone+"'])[1]//span[text()='"+ControllerName+"']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", controller_element);
		Thread.sleep(1000);
		try {
			String status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+ControllerName+"'])[1]//parent::span//parent::td//following-sibling::td[1]"))).getText();
			System.out.println("try" + status);
	//		test.log(LogStatus.PASS, "Test in Try");
		} catch (TimeoutException e) {
			WebElement close_button_element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(Click_SiteID_Textbox));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", close_button_element);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Click_ListView_LessDeatilsArrow)).click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Click_ListView_ExpandArrow)).click();
			Thread.sleep(2000);
			WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'"+zone+"')]"));

			Actions action1 = new Actions(driver);
			action1.moveToElement(element1).build().perform();
			Thread.sleep(1000);
			// element.click();
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			String status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+ControllerName+"'])[1]//parent::span//parent::td//following-sibling::td[1]"))).getText();
			System.out.println("catch" + status);
		//	test.log(LogStatus.PASS, "Test in Catch");
		}
		
		
		
		//GET MODE
		Modee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+ControllerName+"'])[1]//parent::span//parent::td//following-sibling::td[2]"))).getText();
		System.out.println(Modee);
		
		//GET RTC TIME
		RTC_time=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+ControllerName+"'])[1]//parent::span//parent::td//following-sibling::td[7]"))).getText();
		System.out.println("rtc time "+RTC_time);
		//GET PLAN //GET PHASE
		list = new ArrayList<String>();
		list_phase = new ArrayList<String>();
		long t = System.currentTimeMillis();
		long end = t + 1;
		while (System.currentTimeMillis() < end) {
			plan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+ControllerName+"'])[1]//parent::span//parent::td//following-sibling::td[3]"))).getText();
			phase= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+ControllerName+"'])[1]//parent::span//parent::td//following-sibling::td[4]"))).getText();
			list.add(plan);
			list_phase.add(phase);
		}
		planner=list.get(0);
		System.out.println("planner is"+planner);
		Collection<String> hs_plan = new HashSet<String>(list);
		System.out.println("\n\nUnique elements in Plan:\n");
		
		for (String u : hs_plan) {
			System.out.println(u);
		}
		Collection<String> hs_phase = new HashSet<String>(list_phase);
		System.out.println("\n\nUnique elements in Phase:\n");
		for (String u : hs_phase) {
			System.out.println(u);
		}
		
		
	}
	public String WeekDay()
	{	
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week abbreviated
		System.out.println(simpleDateformat.format(now));
		String weekDay=simpleDateformat.format(now);
		return weekDay;
	}
	public String RTC_time()
	{
		String t=RTC_time;
		String[] f=t.split(" ",2);
		for (String a : f) 
            System.out.println(a); 
		String k=f[1];
		System.out.println("spliited : "+k);
		String firstfiveChars = "";     //substring containing first 4 characters
		 
		if (k.length() > 5) 
		{
			firstfiveChars = k.substring(0, 5);
		} 
		else
		{
			firstfiveChars = k;
		}
		 
		System.out.println("firstFourChars"+firstfiveChars);
		
		return firstfiveChars;
	}
	public String Mode()
	{
		if(Modee.equals("-"))
		{
			Modee="Flash";
		return Modee;
		}
		else
//			Modee="Adaptive";
			return Modee;
	}
	public List<String> Phase()
	{
		return list_phase;
		
	}
	public String Plan()
	{
		return plan;
	}


}
