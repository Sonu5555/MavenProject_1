package Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class commonUtils {

	WebDriver driver;
	WebDriverWait wait;

	public commonUtils(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}

	public void click_Reports() throws Exception {
		try {
			Thread.sleep(10000);
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/i//ancestor::a[text()=' Reports ']")))
					.click();
		} catch (ElementClickInterceptedException e) {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/i//ancestor::a[text()=' Reports ']")))
					.click();
		}
	}

	public String select_Report(String reportName) throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Report name in commonUtils : "+reportName);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(
//				By.xpath("//ul[@class='dropdown-menu ']/li/a[text()='Controller Status Report']"))).click();
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@class='dropdown-menu ']/li/a[text()='"+reportName+"']")));
		
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		Thread.sleep(1000);
		element.click();
		
		return reportName;
	}

	public void clickAndEnter_controller(String controller) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Select controller']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div//input[@type='text' and @class='form-control'])[1]")))
				.sendKeys(controller);

		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public void click_fromDate() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("from_date"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//td[@data-date='9' and @data-month='8' and @data-year='2020'])[1]"))).click();
	}

	public void click_toDate() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("to_date"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//td[@data-date='10' and @data-month='8' and @data-year='2020'])[2]"))).click();
	}

	public void click_today() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("today"))).click();

	}

	public void click_view() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("view"))).click();
	}

	public void click_OK_ForValidDateSelection() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']"))).click();
	}

	public void click_pdf() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='pdf']"))).click();
	}

	public void click_excel() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='xls']"))).click();
	}

	public void select_item(String value,String type) throws Exception {

		System.out.println("Value of Select item : "+value);
		System.out.println("Type of Select item : "+type);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Select Item']"))).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
//				"//span[text()='Select Item']//parent::button//parent::div/div/ul/li/a/span[text()='Controller']")))
//				.click();
		if(type.contains("Zone")) {
			select_Zone(value);
		}
		else if (type.equals("Controller")){
			select_Controller(value);
		}
		else {
			System.out.println("Incorrect or No controller/Zone specified : "+type);
			System.out.println("Value : "+value);
		}
	}
	
	public void select_Controller(String contoller) throws Exception {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='btn-group bootstrap-select open']//span[@class='text'][contains(text(),'Controller')]"))).click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Select controller')]"))).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div//input[@type='text' and @class='form-control'])[2]")))
				.sendKeys(contoller);
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void select_Zone(String zone) throws Exception {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='text' and text()='Zone']"))).click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Select Zone')]"))).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div//input[@type='text' and @class='form-control'])[3]")))
				.sendKeys(zone);
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public String verify_serialNumberOneExisting() {
		String slnoNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//th[@aria-label='SL.No']//ancestor::tr//ancestor::thead//following-sibling::tbody/tr/td[text()='1']")))
				.getText();

		return slnoNumber;
	}
	
	public boolean verify_cycleTimeReportLoaded() {
		boolean b=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//canvas[@class='jqplot-grid-canvas']"))).isDisplayed();
System.out.println("boolean value :"+b);
		return b;
	}

	public void patrilReportGenerationMessage() {
		String partialMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[text()='Partial report getting generated with possible no of records. You may generate again for the remaining period in another file. Do you want to proceed? ']")))
				.getText();
	}

	public void click_nextFile() {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@data-bb-handler=\"confirm\" and text()=' Next File']")))
				.click();
	}

	public void click_cancel() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-bb-handler=\"cancel\"]")))
				.click();

	}

}
