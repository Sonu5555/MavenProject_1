package Modules;

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
import org.testng.asserts.SoftAssert;

public class PoppaCommands {
	WebDriver driver;
	WebDriverWait wait;
	 String modeAfterPoppa;
	 String RTC_time;
	By click_AUTO=By.xpath("//div[@id='popaButton2' and  text()='AUTO']");
	By click_LAMPS_OFF=By.xpath("//div[@id='popaButton0' and  text()='LAMPS OFF']");
	By click_ALLRED=By.xpath("//div[@id='popaButton3' and  text()='ALLRED']");
	By click_FLASH=By.xpath("//div[@id='popaButton1' and  text()='FLASH']");
	By click_MANUAL=By.xpath("//div[@id='popaButton7_L' and  text()='MANUAL']");
	By click_STEP=By.xpath("//div[@id='popaButton9_L' and  text()='STEP']");
	By click_CloseButtonImage=By.xpath("//div[@id='popaModal']//div[@class='modal-header bg-green-active color-palette']//img");
	By click_POPPA_Viewer=By.xpath("//div[@id='idPopaButtonShow']//span/i");
	By click_HURRYCALL=By.xpath("//div[@id='popaButton8_L' and  text()='HURRYCALL']");
	By click_OkToContinue=By.xpath("//div/button[text()='OK']");
	//JUNCTION VIEW DYNAMIC
	By Click_JunctionView=By.xpath("(//div[@data-zone='TVM'])[1]//span[text()='Newcontroller (9604)']//ancestor::tr//td[13]");
	
	//from listview_CLASS
	By Click_Mapview=By.xpath("//li//a[text()='MapView']");
	By Click_ListView = By.xpath("//div[@id='mapDiv']//span");
	By Click_ListView_ExpandArrow = By.xpath("//span[@title='More details']");
	By Click_ListView_LessDeatilsArrow = By.xpath("//span[@title='Less details']");
	By Click_JunctionSearch_textbox = By.xpath("//input[@placeholder='siteId / Junction name']");
	By Click_Close_Button = By.xpath("//a[@data-original-title='close list view']");
	By Click_SiteID_Textbox = By.xpath("//input[@placeholder='siteId / Junction name']");
	int skipflagReturn;
	public PoppaCommands(WebDriver driver) {
		//this.test=test;
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}
	public void KeyPressUP() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.UP).build().perform();
	}
	public void KeyPressDown() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
	}
	
	public static SoftAssert softassertion = new SoftAssert();
	
	public void Click_ListView() throws Exception
	{
		Thread.sleep(14000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_ListView)).click();
//		}catch (ElementClickInterceptedException e) {
//			wait.until(ExpectedConditions.visibilityOfElementLocated(Click_Mapview)).click();
//			Thread.sleep(10000);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(Click_ListView)).click();
//		}
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_Close_Button)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_ListView)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_ListView_ExpandArrow)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[1]"))).click();
		Thread.sleep(2000);
	}
	public void Get_ControllerPrpoerties_POPPA(Object object, Object object2) throws Exception {
		String ControllerName = object.toString();
		String zone = object2.toString();
		System.out.println("Inside Get_ControllerPrpoerties :"+ControllerName+" "+zone);
		Thread.sleep(10000);
//		try {
//		WebElement controller_isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@data-zone='"+zone+"'])[1]//span[text()='"+ControllerName+"']")));
		
//		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'"+zone+"')]"));
		WebElement element = driver.findElement(By.xpath("//span//span[contains(text(),'"+zone+"')]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		element.click();
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		System.out.println("Javascriptexectt_1");
		try {
		WebElement controller_element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@data-zone='"+zone+"'])[1]//span[text()='"+ControllerName+"']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", controller_element);
		Thread.sleep(1000);
		System.out.println("Javascriptexectt_2");
		}catch(TimeoutException e2) {
			System.out.println("Javascriptexectt_3");
			KeyPressDown();
			System.out.println("Javascriptexectt_4");
			System.out.println("Exception Timeout..Tryin to Scroll down to Zone");
			WebElement element1 = driver.findElement(By.xpath("//span//span[contains(text(),'"+zone+"')]"));
			Actions action1 = new Actions(driver);
			action1.moveToElement(element1).build().perform();
			System.out.println("Javascriptexectt_5");
			WebElement controller_element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@data-zone='"+zone+"'])[1]//span[text()='"+ControllerName+"']")));
			System.out.println("Javascriptexectt_6");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", controller_element);
			Thread.sleep(1000);
			System.out.println("Javascriptexectt_7");
		}
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
			WebElement element1 = driver.findElement(By.xpath("//span//span[contains(text(),'"+zone+"')]"));

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
	}
	public void click_JunctionView(String controller)
	{
		System.out.println("Inside click_JunctionView_1");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.
				xpath("(//div[@data-zone='TVM'])[1]//span[text()="
				+ "'"+controller+"']//ancestor::tr//td[13]"))).click();
		System.out.println("Inside click_JunctionView_2");
		wait.until(ExpectedConditions.visibilityOfElementLocated(click_POPPA_Viewer)).click();	
		System.out.println("Inside click_JunctionView_3");
	}
	public String click_lampsOff(String poppa_command,String zone,String controller) throws Exception {
	int skip_flag=0;
	skipflagReturn=skip_flag;
if(poppa_command.equals("MANUAL")) {
	try {
	System.out.println("Inside MANUAL MODE : "+poppa_command);
	System.out.println("Inside MANUAL MODE : "+zone);
	System.out.println("Inside MANUAL MODE : "+controller);
	Thread.sleep(1000);
	System.out.println("0.0");
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.
//			xpath("(//div[@data-zone='TVM'])[1]//span[text()="
//			+ "'Newcontroller (9604)']//ancestor::tr//td[13]"))).click();
	System.out.println("0");
	Thread.sleep(1000);
//	wait.until(ExpectedConditions.visibilityOfElementLocated(click_POPPA_Viewer)).click();
	Thread.sleep(3000);
	System.out.println("1");
	try {
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+poppa_command+"']"))).click();
	}catch(TimeoutException e3) {
		System.out.println("Insdie exception 1");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(click_POPPA_Viewer)).click();	
		Thread.sleep(1000);
		System.out.println("Insdie exception 2");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+poppa_command+"']"))).click();
		System.out.println("Insdie exception 3");
	}
	Thread.sleep(1000);
	System.out.println("2");
	wait.until(ExpectedConditions.visibilityOfElementLocated(click_OkToContinue)).click();
	Thread.sleep(1000);
	System.out.println("3");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='STEP']"))).click();
	Thread.sleep(1000);
	System.out.println("4");
	wait.until(ExpectedConditions.visibilityOfElementLocated(click_OkToContinue)).click();
	Thread.sleep(2000);
	System.out.println("5");
	wait.until(ExpectedConditions.visibilityOfElementLocated(click_CloseButtonImage)).click();
	Thread.sleep(2000);
	System.out.println("6");
	try {
	String cycleTime_value=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[5]"))).getText();
	System.out.println("Cycle Time value is :"+cycleTime_value);
	}catch (TimeoutException e) {
		WebElement element1 = driver.findElement(By.xpath("//span//span[contains(text(),'"+zone+"')]"));

		Actions action1 = new Actions(driver);
		action1.moveToElement(element1).build().perform();
		Thread.sleep(1000);
		// element.click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		String status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[1]"))).getText();
		System.out.println("catch status" + status);
		Thread.sleep(2000);
		String cycleTime_value=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[5]"))).getText();
		System.out.println("Cycle Time value INSIDE CATCH 1 :"+cycleTime_value);
	}
	System.out.println("7");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@data-zone='"+zone+"'])[1]//span[text()='"+controller+"']//ancestor::tr//td[13]"))).click();
	Thread.sleep(1000);
	System.out.println("8");
	wait.until(ExpectedConditions.visibilityOfElementLocated(click_POPPA_Viewer)).click();
	Thread.sleep(1000);
	System.out.println("9");
	wait.until(ExpectedConditions.visibilityOfElementLocated(click_AUTO)).click();
	System.out.println("AUTO CLICKED");
	Thread.sleep(1000);
	System.out.println("10");
	wait.until(ExpectedConditions.visibilityOfElementLocated(click_OkToContinue)).click();
	Thread.sleep(2000);
	System.out.println("close junction view");
	wait.until(ExpectedConditions.visibilityOfElementLocated(click_CloseButtonImage)).click();
	Thread.sleep(12000);
//---------------------------
	String cycleTime_afterAutoValue=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[5]"))).getText();
	System.out.println("9.1");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[5]"))).click();
	System.out.println("10");
	RTC_time=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[7]"))).getText();
	System.out.println("Cycle after Auto click Time value is :"+cycleTime_afterAutoValue);
	modeAfterPoppa=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[2]"))).getText();
	System.out.println("Mode after POPPA 3 "+modeAfterPoppa);
	System.out.println("11");
//---------------------------------	
	System.out.println("11");	
	Thread.sleep(5000);
	WebElement element = driver.findElement(By.xpath("//span//span[contains(text(),'"+zone+"')]"));
	System.out.println("12");
	Actions action = new Actions(driver);
	action.moveToElement(element).build().perform();
	Thread.sleep(1000);
	element.click();
	System.out.println("13");
	Thread.sleep(1500);
	KeyPressUP();
	System.out.println("up_1");
	KeyPressUP();
	System.out.println("up_2");
	KeyPressUP();
	System.out.println("up_3");
	KeyPressUP();
	System.out.println("up_4");
	Thread.sleep(1000);
	System.out.println("14");
	WebElement element_up = driver.findElement(By.xpath("//span//span[contains(text(),'Bhandup Zone')]"));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element_up);
	Actions action_UP = new Actions(driver);
	action_UP.moveToElement(element).build().perform();
	}catch(ElementClickInterceptedException e) {
		System.out.println("Time Period Over");
		System.out.println("close junction view 2");
		wait.until(ExpectedConditions.visibilityOfElementLocated(click_CloseButtonImage)).click();
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//span//span[contains(text(),'"+zone+"')]"));
		System.out.println("catch 1");
		action.moveToElement(element).build().perform();
		Thread.sleep(1000);
		element.click();
		System.out.println("catch 2");
		Thread.sleep(1500);
		KeyPressUP();
		System.out.println("catch 3_up_1");
		KeyPressUP();
		System.out.println("catch 4_up_2");
		KeyPressUP();
		System.out.println("catch 5_up_3");
		KeyPressUP();
		System.out.println("catch 6_up_4");
		Thread.sleep(1000);
		System.out.println("catch 7");
		WebElement element_up = driver.findElement(By.xpath("//span//span[contains(text(),'Bhandup Zone')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element_up);
		Actions action_UP = new Actions(driver);
		action_UP.moveToElement(element).build().perform();
	}
	System.out.println("15");
}else {
try {
		System.out.println("Inside clicklampsoff() : "+poppa_command);
		System.out.println("Inside clicklampsoff() : "+zone);
		System.out.println("Inside clicklampsoff() : "+controller);
		Thread.sleep(1000);
		System.out.println("0.0");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.
//				xpath("(//div[@data-zone='TVM'])[1]//span[text()="
//				+ "'Newcontroller (9604)']//ancestor::tr//td[13]"))).click();
		System.out.println("0");
		Thread.sleep(1000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(click_POPPA_Viewer)).click();
		Thread.sleep(3000);
		System.out.println("1");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+poppa_command+"']"))).click();
		Thread.sleep(1000);
		System.out.println("2");
		wait.until(ExpectedConditions.visibilityOfElementLocated(click_OkToContinue)).click();
		System.out.println("3");
		wait.until(ExpectedConditions.visibilityOfElementLocated(click_CloseButtonImage)).click();
		Thread.sleep(2000);
		System.out.println("4");
		try {
		String cycleTime_value=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[5]"))).getText();
		System.out.println("Cycle Time value TRY 2 is :"+cycleTime_value);
		Thread.sleep(12000);
		modeAfterPoppa=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[2]"))).getText();
		System.out.println("Mode after POPPA 1 "+modeAfterPoppa);
		}catch (TimeoutException e1) {
			WebElement element1 = driver.findElement(By.xpath("//span//span[contains(text(),'"+zone+"')]"));

			Actions action1 = new Actions(driver);
			action1.moveToElement(element1).build().perform();
			Thread.sleep(1000);
			// element.click();
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
			String status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[1]"))).getText();
			System.out.println("catch status 2" + status);
			String cycleTime_value=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[5]"))).getText();
			System.out.println("Cycle Time value INSIDE CATCH 2 :"+cycleTime_value);
			modeAfterPoppa=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[2]"))).getText();
			System.out.println("Mode after POPPA 2 "+modeAfterPoppa);
		}
		System.out.println("5");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@data-zone='TVM'])[1]//span[text()='"+controller+"']//ancestor::tr//td[13]"))).click();
		Thread.sleep(1000);
		System.out.println("6");
		wait.until(ExpectedConditions.visibilityOfElementLocated(click_POPPA_Viewer)).click();
		System.out.println("7");
		wait.until(ExpectedConditions.visibilityOfElementLocated(click_AUTO)).click();
		System.out.println("AUTO CLICKED");
		Thread.sleep(1000);
		System.out.println("8");
		wait.until(ExpectedConditions.visibilityOfElementLocated(click_OkToContinue)).click();
		Thread.sleep(2000);
		System.out.println("close junction view");
		wait.until(ExpectedConditions.visibilityOfElementLocated(click_CloseButtonImage)).click();
		Thread.sleep(12000);
		System.out.println("9");
		String cycleTime_afterAutoValue=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[5]"))).getText();
		System.out.println("9.1");
		RTC_time=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[7]"))).getText();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[5]"))).click();
		System.out.println("10");
		System.out.println("Cycle after Auto click Time value is :"+cycleTime_afterAutoValue);
		modeAfterPoppa=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+controller+"'])[1]//parent::span//parent::td//following-sibling::td[2]"))).getText();
		System.out.println("Mode after POPPA 3 "+modeAfterPoppa);
		System.out.println("11");
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//span//span[contains(text(),'"+zone+"')]"));
		System.out.println("12");
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		Thread.sleep(1000);
		element.click();
		System.out.println("13");
		Thread.sleep(1500);
		KeyPressUP();
		System.out.println("up_1");
		KeyPressUP();
		System.out.println("up_2");
		KeyPressUP();
		System.out.println("up_3");
		KeyPressUP();
		System.out.println("up_4");
		Thread.sleep(1000);
		System.out.println("14");
		WebElement element_up = driver.findElement(By.xpath("//span//span[contains(text(),'Bhandup Zone')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element_up);
		Actions action_UP = new Actions(driver);
		action_UP.moveToElement(element).build().perform();
		
		System.out.println("15");
		System.out.println("Skip flag value"+skip_flag);
		return cycleTime_afterAutoValue;
		
}catch(TimeoutException e) {
	System.out.println("Skip flag value inside catch"+skip_flag);
	System.out.println("UI issue with Plus Button");
	WebElement element = driver.findElement(By.xpath("//span//span[contains(text(),'"+zone+"')]"));
	System.out.println("16");
	Actions action = new Actions(driver);
	action.moveToElement(element).build().perform();
	Thread.sleep(1000);
	element.click();
	System.out.println("17");
	
	WebElement element_up = driver.findElement(By.xpath("//span//span[contains(text(),'Bhandup Zone')]"));
	System.out.println("18");
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element_up);
	Actions action_UP = new Actions(driver);
	action_UP.moveToElement(element).build().perform();
	System.out.println("19");
	skip_flag=1;
	skipflagReturn=skip_flag;
}

return controller;
	}
return controller;
	}	
	
	
	public  String getStringBetweenTwoChars(String input, String startChar, String endChar) {
	    try {
	        int start = input.indexOf(startChar);
	        if (start != -1) {
	            int end = input.indexOf(endChar, start + startChar.length());
	            if (end != -1) {
	                return input.substring(start + startChar.length(), end);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return input; 
	}
	public String Verify_Mode(String controller,String rtcTime) throws Exception {
		
		String get_ControllerCode=controller;
		String startChar = "(";
		String endChar   = ")";
		String output_Code = getStringBetweenTwoChars(get_ControllerCode, startChar, endChar);
		System.out.println("Code Converted : "+output_Code);
		configurationTime conn = new configurationTime();
		conn.connect(output_Code);
		
		String switchTime=conn.getSwitchTime(rtcTime);
		System.out.println("Switch time is "+switchTime);
		conn.selectMode();
		String switchMode=conn.getMode();
		System.out.println("Mode in String Verify_Mode : "+switchMode);
		if(switchMode.equals("1")) {
			
			switchMode="-";
		}
		else if(switchMode.equals("6")) {
			
			switchMode="VA";
		}
		else if(switchMode.equals("4")) {
			
			switchMode="CableLess";
		}
		else {
			switchMode=conn.getMode();
		}
		
		if(modeAfterPoppa.equals(switchMode)) {
			System.out.println("Mode is matching after POPPA exection ");
			System.out.println("if :"+modeAfterPoppa +" "+switchMode);
		}
		else {
			System.out.println("Mode is NOT matching after POPPA exection ");
			System.out.println("if :"+modeAfterPoppa +" "+switchMode);
			softassertion.assertTrue(false);
		}
		return switchMode;
	}
	
	public String returnMode() {
		
		
		return modeAfterPoppa;
	}
	public String rtcTime() {
		
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
		 
		System.out.println("RTC time after POPPA Command"+firstfiveChars);
		
		return firstfiveChars;
		
	}
	public int skipFlag() {
		
		System.out.println("Skip flag return"+skipflagReturn);
		return skipflagReturn;
	}
}
