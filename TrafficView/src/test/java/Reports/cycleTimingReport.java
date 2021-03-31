package Reports;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import Utilites.fileExistAfterDownload;

public class cycleTimingReport {
	WebDriver driver;
	WebDriverWait wait;
	
	public cycleTimingReport(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}
	
	
	public static SoftAssert softassertion = new SoftAssert();
	
	public void verify_cycleTimingReport(String value,String type) throws Exception {
		
		commonUtils c = new commonUtils(driver);
		fileExistAfterDownload file = new fileExistAfterDownload(driver);
		
		c.select_item(value, type);
		c.click_fromDate();
		c.click_toDate();
		
		Thread.sleep(3000);
		c.click_view();
		Thread.sleep(2000);
		boolean m=c.verify_cycleTimeReportLoaded();
		if ( m==true) {
			System.out.println("Data Exist :");
			softassertion.assertTrue(true);
		} else {
			System.out.println("Data does not Exist :");
			softassertion.assertTrue(false);
		}

		try {
			c.click_pdf();
		} catch (NoSuchElementException ne) {
			System.out.println("Click PDF no such element exception ");
		}
		try {
			if (wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//strong[text()='Memory usage is high,please try again later']"))).isDisplayed()) {

				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='close']"))).click();
				System.out.println("Memory issue, pop closed");
			} else {
				System.out.println("No Memory Issue");
			}
		} catch (ElementNotInteractableException e) {
			System.out.println("ElementNotInteractableException occured");
		} catch (Exception e1) {
			System.out.println("Exception occured");
		}
		try {
		c.click_cancel();
		}catch(NoSuchElementException e) {
			System.out.println("PDF Cancel pop up dint show, so continuing with file checking");
		}catch (Exception e) {
			System.out.println("PDF Exception Cancel pop up dint show, so continuing with file checking");
		}
		
		// download sleep
		Thread.sleep(2000);
		file.fileExist(".pdf");
		Thread.sleep(20000);
		System.out.println("1");
		file.deleteExistingFile();
		System.out.println("2");
		Thread.sleep(2000);

		c.click_excel();
		try {
			c.click_cancel();
			}catch(NoSuchElementException e) {
				System.out.println("EXCEL Cancel pop up dint show, so continuing with file checking");
			}catch (Exception e) {
				System.out.println("EXCEL Exception Cancel pop up dint show, so continuing with file checking");
			}
		// download sleep
		Thread.sleep(30000);
		file.fileExist(".xls");
		Thread.sleep(2000);
		System.out.println("3");
		file.deleteExistingFile();
		System.out.println("4");
		System.out.println("test");
	}
}
