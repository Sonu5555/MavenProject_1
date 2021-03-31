package Reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class reportSwitchClass {

	WebDriver driver;
	WebDriverWait wait;

	public reportSwitchClass(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}

	public void reportSwitch(String rpt,String contoller,String itemType) throws Exception {

		commonUtils c = new commonUtils(driver);

		System.out.println("Report name in reportSwitchClass : " + rpt);

		switch (rpt) {
		case "Controller Status Report":
			System.out.println("Controller Status Report");
			controllerStatusReport csr=new controllerStatusReport(driver);
			csr.verify_controllerStatusReport(contoller);
			break;
			
		case "Cycle Timing Report":
			System.out.println("Cycle Timing Report");
			cycleTimingReport ctr=new cycleTimingReport(driver);
			ctr.verify_cycleTimingReport(contoller,itemType);
			break;
			
		}

	}
}
