package Modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignIn {
	WebDriver driver;
	WebDriverWait wait;
	
	By username=By.id("username");
	By password=By.id("password");
	By Click_Login=By.id("login");
//	public SignIn_WithURL(ThreadLocal<WebDriver> webdriver) {
//		// TODO Auto-generated constructor stub
//		this.driver = (WebDriver) webdriver;
//		this.wait = new WebDriverWait((WebDriver) webdriver, 30);
//	}
	public SignIn(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}

	public void WithURL(Object object, Object object2) throws Exception
	{

		String Uname = object.toString();
		String pass = object2.toString();

		System.out.println("test orint");
		wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(Uname);
		wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pass);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click_Login)).click();
		System.out.println("test orint33");
		try {
			String r=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//label[@id='zoneLabel']"))).getText();
			
			Assert.assertTrue(true);
			
		}catch(Throwable  e)
		{
			Assert.assertTrue(false);
		}		
}

}

