package Modules;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class TrafficParameterSettings {

	WebDriver driver;
	WebDriverWait wait;
	// ExtentReports reports;
	// ExtentTest test;
	List<String> list_hour;
	List<String> list_min;
	List<String> list_phases;
	List<String> list_VA;
	List<String> list_ADAP;
	List<String> list_Flash;
	String phases;
	WebElement input_hours, input_min, Weekday_Mode, phases_web;
	String con_hr, con_min;
	List<WebElement> allLinks;
	List<WebElement> allphases;
	String mode, plan_time;
	int count, i, countPhases, p;
	WebElement get_VA_Plan_No, get_Cabless_Plan_No;
	String VA_Plan_No, Cabless_Plan_No, plan_NO;

	// TRAFFIC PARAMETER
	By Click_TrafficMangement = By.xpath("//a[text()=' Traffic Management ']");
	By Click_TrafficParameterSetting = By.xpath("//a[text()='Traffic Parameter Settings']");

	// TRAFFIC PARAMETER - CONTROLLER CLICK
	By Click_SelectController = By.xpath("(//span[text()='Select controller'])[1]");
	By Enter_ControllerName = By.xpath("(//div[@class='bs-searchbox']/input[@type='text'])[1]");

	// WEEKDAY PLAN DETAILS//WeekdayPlanAssignment
	By Click_WeekdayPlanDetails = By.xpath("//span[text()='Weekday Plan	Details']");
	By Click_WeekdayPlanAssignment = By.xpath("//a[text()=' Weekday plan Assignment ']");
	By Click_WeekDayPlanInfo = By.xpath("//a[text()=' Weekday plan Info']");
	By Click_WeekDayPlan_textfield = By.id("weekday_plans_limit");
	By Get_WeekdayPlan_Mode = By.xpath("//select[@id='weekday_plans_mode']");
	By Get_Weekday_VA_Plan_No = By.xpath("//select[@id='weekday_plans_va']");
	By Get_Weekday_Cableless_Plan_No = By.xpath("//select[@id='weekday_plans_caless']");
	// WEEKDAY PLAN DETAILS//WeekDayPlanInfo//
	// pass weekday using current day from calendar
	By Get_WeekDayAssignmentValue = By.xpath("//label[text()='Sunday']/parent::div//span[@role='textbox']");

	// pass the above result into the text value

	By Click_EditPlan = By.xpath("//div[@id='mCSB_2_container']/ul/li[text()='3']/i[2]");

	// GET SWITCH HOUR AND MINTUES
	By Get_Hour = By.xpath("(//input[@placeholder='Enter Hours'])[1]");
	By Get_minutes = By.xpath("(//input[@placeholder='Enter Minutes'])[1]");

	// CABLELESS PLAN DETAILS
	By Click_Cableless_Plan_Value = By.xpath("//span[text()='Cableless Plan Details']");
	By Click_Cableless_Plan_Info = By.xpath("//a[text()=' Cableless plan Info']");
	By Click_Cabeless_Phase_Call = By.xpath("//a[text()=' Cableless phase call ']");

	By Click_Cableless_PlanSearch_Phasecall = By.xpath("(//label[text()='Plan Search'])[1]");
	By Click_Cableless_PlanSearch_PlanInfo = By.xpath("(//label[text()='Plan Search'])[2]");

	public TrafficParameterSettings(WebDriver driver) {
		// this.test=test;
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);

	}

	public void KeyPressDown() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
	}

	public void KeyPressUP() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.UP).build().perform();
	}

	public boolean checkElementDisplayed(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed()) {
				flag = true;
			}
		} catch (Exception e) {
		}
		return flag;
	}

	public void SoftAssertion_False() {
		SoftAssert softassertion = new SoftAssert();
		softassertion.assertTrue(false);
		// softassertion.assertAll();
	}

	public static SoftAssert softassertion = new SoftAssert();

	public void Select_TrafficParameterSetting(Object controllername) throws Exception {
		String controller_name = controllername.toString();
		String result_ControllerID = StringUtils.substringBetween(controller_name, "(", ")");
		// System.out.println("result_ControllerID");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_TrafficMangement)).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_TrafficParameterSetting)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_SelectController)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Enter_ControllerName)).sendKeys(result_ControllerID);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Enter_ControllerName)).sendKeys(Keys.ENTER);

	}

	public void Get_WeekdayPlan(String weekDay, String rtctime) throws Exception {
		Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_WeekdayPlanDetails)).click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_WeekdayPlanAssignment)).click();

		String planvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//label[text()='" + weekDay + "']/parent::div//span[@role='textbox']"))).getText();
		System.out.println("Weekday plan value is " + planvalue);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_WeekDayPlanInfo)).click();
		Thread.sleep(2000);
		try {
			System.out.println("PROBLEM 1");
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))
					.click();
		} catch (TimeoutException e) {
			System.out.println("PROBLEM 2");
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=1]/i[2]"))).click();
			do {
				System.out.println("PROBLEM 3");
				KeyPressDown();
				Thread.sleep(500);
				KeyPressDown();
				Thread.sleep(500);
				KeyPressDown();
				Thread.sleep(500);
				KeyPressDown();
				Thread.sleep(500);

			} while (!checkElementDisplayed(wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))
					.click();
			System.out.println("PROBLEM 4");
		} catch (ElementClickInterceptedException e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=1]/i[2]"))).click();
			do {
				System.out.println("PROBLEM 5");
				KeyPressDown();
				Thread.sleep(500);
				KeyPressDown();
				Thread.sleep(500);
				KeyPressDown();
				Thread.sleep(500);
				KeyPressDown();
				Thread.sleep(500);

			} while (!checkElementDisplayed(wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))
					.click();
			System.out.println("PROBLEM 6");
		}catch (ElementNotInteractableException e) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=1]/i[2]"))).click();
				do {
					System.out.println("PROBLEM 7");
					KeyPressDown();
					Thread.sleep(500);
					KeyPressDown();
					Thread.sleep(500);
					KeyPressDown();
					Thread.sleep(500);
					KeyPressDown();
					Thread.sleep(500);

				} while (!checkElementDisplayed(wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))));
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))
						.click();
				System.out.println("PROBLEM 8");
		}

		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView;",
		// element3);
		// element3.click();

		// ADDING HOURS AND MINUTES TO THE LIST TO FIND OUT IN WHICH TIME PERIOD THE
		// VALUE IS RUNNING
		allLinks = driver.findElements(By.xpath("(//ul[@class='list switches_list'])[1]/li"));
		count = allLinks.size();
		list_hour = new ArrayList<String>();
		list_min = new ArrayList<String>();
		String d = null;
		System.out.println("Number of swithces " + count);

		String time = rtctime;
		DateFormat sdf = new SimpleDateFormat("HH:mm");
		Date datet = sdf.parse(time);
		String timea = sdf.format(datet);
		System.out.println("Time after rtc string time conversion: " + timea);

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.format(date);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//ul[@class='list switches_list'])[1]/li[1]/i[2]"))).click();
		for (int i = 1; i <= count; i++) {
			try {

				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//ul[@class='list switches_list'])[1]/li[" + i + "]/i[2]"))).click();
				Thread.sleep(2000);

				wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
				Thread.sleep(2000);
				input_hours = driver.findElement(By.xpath("(//input[@placeholder='Enter Hours'])[1]"));
				// System.out.println("hours"+input_hours.getAttribute("value"));
				con_hr = input_hours.getAttribute("value");
				wait.until(ExpectedConditions.visibilityOfElementLocated(Get_minutes)).click();
				Thread.sleep(500);
				input_min = driver.findElement(By.xpath("(//input[@placeholder='Enter Minutes'])[1]"));
				// System.out.println("Min"+input_min.getAttribute("value"));
				con_min = input_min.getAttribute("value");

				list_hour.add(con_hr + ":" + con_min);
			} catch (TimeoutException e) {

				do {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("(//ul[@class='list switches_list'])[1]/li[" + i + "" + -1 + "]/i[2]"))).click();
					KeyPressDown();

					Thread.sleep(500);
				} while (!checkElementDisplayed(wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//ul[@class='list switches_list'])[1]/li[" + i + "]/i[2]")))));

				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//ul[@class='list switches_list'])[1]/li[" + i + "]/i[2]"))).click();
				Thread.sleep(2000);

				wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
				Thread.sleep(500);
				input_hours = driver.findElement(By.xpath("(//input[@placeholder='Enter Hours'])[1]"));
				con_hr = input_hours.getAttribute("value");
				wait.until(ExpectedConditions.visibilityOfElementLocated(Get_minutes)).click();
				Thread.sleep(500);
				input_min = driver.findElement(By.xpath("(//input[@placeholder='Enter Minutes'])[1]"));
				con_min = input_min.getAttribute("value");

				list_hour.add(con_hr + ":" + con_min);
			}

		}
		for (String y : list_hour) {
			System.out.println("for loop arraylist" + y);
		}
		// FINDING OUT IN WHICH TIME PERIOD THE GET VALUE IS RUNNING, WE GOT THE ARRAY
		// LIST FROM
		// THE ABOVE SECTION
		// System.out.println("static date is"+date);

		String flag_lastswitch="0";
		for (i = 0; i < count - 1; i++) {

			if (dateFormat.parse(list_hour.get(i)).before(dateFormat.parse(timea))
					&& dateFormat.parse(list_hour.get(i + 1)).after(dateFormat.parse(timea)) && i < count - 1) {
				Thread.sleep(500);
				System.out.println("static n plus one switch date is" + date);
//-------------------------------------------------------------
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("(//ul[@class='list switches_list'])[1]/li[" + i + "+" + 1 + "]/i[2]"))).click();
				} catch (TimeoutException e) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(Click_WeekdayPlanAssignment)).click();

					planvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//label[text()='" + weekDay + "']/parent::div//span[@role='textbox']"))).getText();
					System.out.println("Weekday plan value is " + planvalue);
					wait.until(ExpectedConditions.visibilityOfElementLocated(Click_WeekDayPlanInfo)).click();

					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))
								.click();
					} catch (TimeoutException e11) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=1]/i[2]"))).click();
						do {
							KeyPressDown();
							Thread.sleep(500);
							KeyPressDown();
							Thread.sleep(500);
							KeyPressDown();
							Thread.sleep(500);
							KeyPressDown();
							Thread.sleep(500);

						} while (!checkElementDisplayed(wait.until(ExpectedConditions.visibilityOfElementLocated(By
								.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))));
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))
								.click();
					} catch (ElementClickInterceptedException e1) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=1]/i[2]"))).click();
						do {
							KeyPressDown();
							Thread.sleep(500);
							KeyPressDown();
							Thread.sleep(500);
							KeyPressDown();
							Thread.sleep(500);
							KeyPressDown();
							Thread.sleep(500);

						} while (!checkElementDisplayed(wait.until(ExpectedConditions.visibilityOfElementLocated(By
								.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))));
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@id='mCSB_2']//div[@id='mCSB_2_container']/ul/li[text()=" + planvalue + "]/i[2]")))
								.click();
					}
			}
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("(//ul[@class='list switches_list'])[1]/li[" + i + "+" + 1 + "]/i[2]"))).click();
//-------------------------------------------------------------				
				System.out.println("list count-1 " + list_hour.get(count - 1));
				System.out.println("list I+1" + list_hour.get(i + 1));
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//ul[@class='list switches_list'])[1]/li[" + i + "+" + 1 + "]/i[2]"))).click();
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[@class='list
				// switches_list'])[1]/li["+count+"]/i[2]"))).click();
				Weekday_Mode = wait.until(ExpectedConditions.visibilityOfElementLocated(Get_WeekdayPlan_Mode));
				mode = Weekday_Mode.getAttribute("value");
				flag_lastswitch="1";
				if (mode.equals("1")) {
					mode = "-";

				} else if (mode.equals("4")) {
					mode = "CableLess";
					wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
					Thread.sleep(500);
					KeyPressDown();
					get_Cabless_Plan_No = wait
							.until(ExpectedConditions.visibilityOfElementLocated(Get_Weekday_Cableless_Plan_No));
					plan_NO = get_Cabless_Plan_No.getAttribute("value");

					System.out.println("Cabeless plan no is" + plan_NO);
				} else if (mode.equals("6")) {
					mode = "VA";
					wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
					Thread.sleep(500);
					KeyPressDown();
					get_VA_Plan_No = wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Weekday_VA_Plan_No));
					plan_NO = get_VA_Plan_No.getAttribute("value");

					System.out.println("VA plan no is" + plan_NO);
				} else {
					System.out.println(
							"KINDLY ADD THE MODE UNDER (COMPARING Nth VALUE WITH N + 1 Value)SECTION Get_WeekdayPlan()");
				}
				plan_time = list_hour.get(i);
				System.out.println("correct :" + list_hour.get(i));
				System.out.println("mode is" + mode);

				}

			/// IF EXISTING ONLY SINGLE SWITCH
			else {
				if(dateFormat.parse(list_hour.get(0)).after(dateFormat.parse(timea)) && count == 1) {
				// System.out.println("static single switch date is"+date);
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//ul[@class='list switches_list'])[1]/li[" + i + "+" + 1 + "]/i[2]"))).click();
				Weekday_Mode = wait.until(ExpectedConditions.visibilityOfElementLocated(Get_WeekdayPlan_Mode));
				mode = Weekday_Mode.getAttribute("value");
				if (mode.equals("1")) {
					mode = "-";

				} else if (mode.equals("4")) {
					mode = "CableLess";
					wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
					Thread.sleep(500);
					KeyPressDown();
					get_Cabless_Plan_No = wait
							.until(ExpectedConditions.visibilityOfElementLocated(Get_Weekday_Cableless_Plan_No));
					plan_NO = get_Cabless_Plan_No.getAttribute("value");

					System.out.println("Cabeless plan no is" + plan_NO);
				} else if (mode.equals("6")) {
					mode = "VA";
					wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
					Thread.sleep(500);
					KeyPressDown();
					get_VA_Plan_No = wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Weekday_VA_Plan_No));
					plan_NO = get_VA_Plan_No.getAttribute("value");

					System.out.println("VA plan no is" + plan_NO);
				} else {
					System.out.println(
							"KINDLY ADD THE MODE UNDER (COMPARING Nth VALUE WITH N + 1 Value)SECTION Get_WeekdayPlan()");
				}
				plan_time = list_hour.get(i);
				System.out.println("correct :" + list_hour.get(i));
				System.out.println("mode is" + mode);

			
			} 
			//LAST SWITCH DATA
		}
			
		}

		if(flag_lastswitch.equals("1"))
			{
			System.out.println("Already fetched");
		}
		else
			{
				if (dateFormat.parse(list_hour.get(count - 1)).before(dateFormat.parse(timea))) {
				System.out.println("Mode in Last Switch start");
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//ul[@class='list switches_list'])[1]/li[" + count + "]/i[2]"))).click();
				Weekday_Mode = wait.until(ExpectedConditions.visibilityOfElementLocated(Get_WeekdayPlan_Mode));
				mode = Weekday_Mode.getAttribute("value");
				if (mode.equals("1")) {
					mode = "-";

				} else if (mode.equals("4")) {
					mode = "CableLess";
					wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
					Thread.sleep(500);
					KeyPressDown();
					get_Cabless_Plan_No = wait
							.until(ExpectedConditions.visibilityOfElementLocated(Get_Weekday_Cableless_Plan_No));
					plan_NO = get_Cabless_Plan_No.getAttribute("value");

					System.out.println("Cabeless plan no is" + plan_NO);
				} else if (mode.equals("6")) {
					mode = "VA";
					wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
					Thread.sleep(500);
					KeyPressDown();
					get_VA_Plan_No = wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Weekday_VA_Plan_No));
					plan_NO = get_VA_Plan_No.getAttribute("value");

					System.out.println("VA plan no is" + plan_NO);
				} else {
					System.out.println(
							"KINDLY ADD THE MODE UNDER (COMPARING Nth VALUE WITH N + 1 Value)SECTION Get_WeekdayPlan()");
				}
				plan_time = list_hour.get(i);
				System.out.println("correct :" + list_hour.get(i));
				System.out.println("mode is" + mode);

			}
			}

		//// IF LAST SWITCH VALUE IS LESS THAN GET VALUE(NOT TESTED)
		// else
		// if(dateFormat.parse(list_hour.get(count-1)).before(dateFormat.parse(timea)))
		// {
		// // System.out.println("static SWITCH date is"+date);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[@class='list
		// switches_list'])[1]/li["+count+"]/i[2]"))).click();
		// Weekday_Mode=wait.until(ExpectedConditions.visibilityOfElementLocated(Get_WeekdayPlan_Mode));
		// mode=Weekday_Mode.getAttribute("value");
		// if(mode.equals("1"))
		// {
		// mode="Flash";
		// }
		// else if(mode.equals("4"))
		// {
		// mode="CableLess";
		// wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
		// Thread.sleep(500);
		// KeyPressDown();
		// get_Cabless_Plan_No=wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Weekday_Cableless_Plan_No));
		// plan_NO=get_Cabless_Plan_No.getAttribute("value");
		// System.out.println("LAST Cabeless plan no is"+plan_NO);
		// }
		// else if(mode.equals("6"))
		// {
		// mode="VA";
		// wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Hour)).click();
		// Thread.sleep(500);
		// KeyPressDown();
		// get_VA_Plan_No=wait.until(ExpectedConditions.visibilityOfElementLocated(Get_Weekday_VA_Plan_No));
		// plan_NO=get_VA_Plan_No.getAttribute("value");
		//
		// System.out.println("LAST VA plan no is"+plan_NO);
		// }
		// else {
		// mode="Adaptive";
		// }
		// plan_time=list_hour.get(count-1);
		// System.out.println("Last"+list_hour.get(count-1));
		// System.out.println("mode is"+mode);
		//
		// }
		// }
		/// COMPARING Nth VALUE WITH N + 1 Value

	}

	public String Verify_mode(String mode_UI) {
		System.out.println("MANUAL MODE" + mode_UI);
		System.out.println("Inside verfiy mode ");
		if (mode.equals(mode_UI)) {
			System.out.println("Mode from UI is matching with :" + mode_UI + "\n");
			System.out.println("And Mode from DB :" + mode);
			// test.log(LogStatus.PASS, "Test case Verifying mode is Passed");
			System.out.println("Inside verfiy mode IF STAETMENT");
			return mode;
		} else {
			System.out.println("Incorrect Mode Displayed");
			System.out.println("Inside verfiy mode ELSE STAETMENT");
			// test.log(LogStatus.FAIL, "Test case Verifying mode is FAILED");

			softassertion.assertTrue(false);
			return mode;
		}

	}

	public List Verify_Phases(List phased, String mode, String pland, Object contollername) throws Exception {
		
		ArrayList<String> return_phase = new ArrayList<String>();
		System.out.println("Verify Phase data are : " + phased + " " + mode + " " + pland + " " + contollername);
		String controller_name = contollername.toString();
		String result_ControllerID = StringUtils.substringBetween(controller_name, "(", ")");
		System.out.println("result_ControllerID :" + result_ControllerID);
		System.out.println("1.pland :" + pland);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_TrafficMangement)).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_TrafficParameterSetting)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_SelectController)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Enter_ControllerName)).sendKeys(result_ControllerID);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Enter_ControllerName)).sendKeys(Keys.ENTER);
		if (mode.equals("CableLess")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Click_Cableless_Plan_Value)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Click_Cabeless_Phase_Call)).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Click_Cableless_PlanSearch_Phasecall))
					.sendKeys("" + pland + "");
			Thread.sleep(1000);
			allphases = driver.findElements(By.xpath("//tr/td[text()=" + pland + " and @class='sorting_1']"));
			driver.findElements(By.xpath("//tr/td[text()=" + pland + " and @class='sorting_1']/parent::tr/td[3]"));
			countPhases = allphases.size();
			// ArrayList<String> arrlist = new ArrayList<String>();
			list_phases = new ArrayList<String>();
			System.out.println("count is" + countPhases);
			String m = wait
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("(//tr/td[text()=" + pland + " and @class='sorting_1']/parent::tr/td[3])[1]")))
					.getText();
			System.out.println("m is " + m);
			String plande = wait
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("(//tr/td[text()=" + pland + " and @class='sorting_1']/parent::tr/td[1])[1]")))
					.getText();
			Thread.sleep(1000);
			for (int l = 1; l <= countPhases; l++) {
				// String
				// m1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tr/td[text()=37
				// and @class='sorting_1']/parent::tr/td[3])[+countPhases+]"))).getText();
				phases_web = driver.findElement(
						By.xpath("(//tr/td[text()=" + pland + " and @class='sorting_1']/parent::tr/td[3])[" + l + "]"));
				phases = phases_web.getText();
				System.out.println("p" + phases);
				list_phases.add(phases);
				System.out.println("2.for loop pland :" + pland);
			}
				ArrayList<String> al3 = new ArrayList<String>();
				for (String temp : list_phases)
					al3.add(phased.contains(temp) ? "Yes" : "No");
				System.out.println("al33 :"+al3);
				if (al3.contains("Yes") && plan_NO.equals(pland)) {
					System.out.println("compare plan in phaseverification"+plan_NO+" : "+pland);
					System.out.println("CabeLeless Phase exist in the DB is correct");
					// test.log(LogStatus.PASS, "CabeLeless Phase in the DB is correct ");
					ArrayList<String> ph = new ArrayList<String>();
					for (String temepha : list_phases)
						ph.add(temepha);
					System.out.println("return phases :" + ph);
					return_phase= ph;
				} else {
					System.out.println("compare plan in phaseverification ELSE "+plan_NO+" : "+pland);
					System.out.println("PHASE DOES NOT EXIST IN THE DB");
					// test.log(LogStatus.FAIL, "CabeLeless Phase Doesnt not exist in the DB ");
					softassertion.assertTrue(false);
					for (String temp : list_phases)
						al3.add(temp);
					System.out.println("return phases :" + al3);	
					return_phase=al3;
				}
				
			
		
		} else if (mode.equals("VA")) {

			System.out.println("VA PLAN");

			// test.log(LogStatus.PASS, "VA PHASE");
			list_VA = new ArrayList<String>();
			list_VA.add("VA PLAN");
			ArrayList<String> va = new ArrayList<String>();
			for (String pas_va : list_VA)
				va.add(pas_va);
			System.out.println("return VA :" + va);
			return_phase= va;

		} else if (mode.equals("Adaptive")) {
			System.out.println("Adavaptive Mode");
			// test.log(LogStatus.PASS, "Adavaptive Mode");
			list_ADAP = new ArrayList<String>();
			list_ADAP.add("Adavaptive Mode");
			ArrayList<String> ad = new ArrayList<String>();
			for (String pas_ad : list_ADAP)
				ad.add(pas_ad);
			System.out.println("return ADAP :" + ad);
			return_phase=ad;
		} else {
			System.out.println("Flash PHASE");
			list_Flash = new ArrayList<String>();
			list_Flash.add("Flash Mode");
			ArrayList<String> fl = new ArrayList<String>();
			for (String pas_fl : list_Flash)
				fl.add(pas_fl);
			System.out.println("return ADAP :" + fl);
			return_phase=fl;
			// test.log(LogStatus.PASS, "Flash PHASE");

		}
		return return_phase;
		
	}

	public String Verify_Plan(String plans, String modeFlash) {
		String return_plan;
		if (modeFlash.equals("Flash")) {
			System.out.println("No Plan for FLASH MODE");
			String flas = "No Plan for FLASH MODE";
			return_plan= flas;
			// test.log(LogStatus.PASS, "Flash Mode doesnt have Any Plan");
		} else {
			if (plan_NO.equals(plans)) {
				System.out.println("The plan in the UI is " + plans + ",which is same as in the DB " + plan_NO);
				// test.log(LogStatus.PASS, "Test case Verifying Plan is Passed");
				return_plan= plan_NO;
			} else {
				System.out.println("Incorrect Plan:" + plans + " plan :" + plan_NO);
				// test.log(LogStatus.FAIL, "Test case Verifying Plan is failed");
				return_plan= plan_NO;
				softassertion.assertTrue(false);
				
			}
		}
		return return_plan;
	}
	// public String DB_FecthMode() {
	// System.out.println("TP returning mode"+mode);
	// return mode;
	// }
	// public String DB_FecthPlan() {
	// System.out.println("TP returning mode" + plan_NO);
	// return plan_NO;
	// }
	// public List DB_FecthPhase() {
	//
	// ArrayList<String> al3= new ArrayList<String>();
	// for (String temp : list_phases)
	// al3.add(temp);
	// System.out.println("return phases :"+al3);
	// return (al3);
	// }

}
