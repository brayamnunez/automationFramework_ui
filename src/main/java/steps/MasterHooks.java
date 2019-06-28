package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import pageObjects.BasePage;
import utils.DriverFactory;

public class MasterHooks extends DriverFactory{

	@Before()
	public void setUp() throws Exception {
		driver = getDriver();
		loadUrl(null);
	}
	
	@After
	public void tearDownAndScreenshotOnFailure(Scenario scenario){
		try {
			if(driver != null && scenario.isFailed()){
				//scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
				BasePage.captureScreenshot();
				closeBrowserAndClearCookies();
			}
				
			if(driver != null){
				closeBrowserAndClearCookies();
			}
		} catch (Exception e) {			
			System.out.println("Methods failed: tearDownAndScreenshotOnFailure, Exception: " + e.getMessage());			
		}
	}
	
	private void closeBrowserAndClearCookies(){
		driver.manage().deleteAllCookies();
		driver.quit();
		driver = null;
	}
}
