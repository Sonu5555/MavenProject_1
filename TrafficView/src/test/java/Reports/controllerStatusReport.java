package Reports;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import Utilites.fileExistAfterDownload;

public class controllerStatusReport {

	WebDriver driver;
	WebDriverWait wait;

	public controllerStatusReport(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}

	public static SoftAssert softassertion = new SoftAssert();

	public void verify_controllerStatusReport(String contller) throws Exception {
		// String dataExistCSR;
		commonUtils c = new commonUtils(driver);
		fileExistAfterDownload file = new fileExistAfterDownload(driver);

		// c.select_Report();
		c.clickAndEnter_controller(contller);
		c.click_fromDate();
		c.click_toDate();
		Thread.sleep(3000);
		c.click_view();
		Thread.sleep(2000);
		if (c.verify_serialNumberOneExisting().equals("1")) {
			System.out.println("Data Exist :" + c.verify_serialNumberOneExisting());
			softassertion.assertTrue(true);
		} else {
			System.out.println("Data does not Exist :" + c.verify_serialNumberOneExisting());
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
		c.click_cancel();
		// download sleep
		Thread.sleep(2000);
		file.fileExist(".pdf");
		Thread.sleep(20000);
		System.out.println("1");
		file.deleteExistingFile();
		System.out.println("1");
		Thread.sleep(2000);

		c.click_excel();
		c.click_cancel();
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
