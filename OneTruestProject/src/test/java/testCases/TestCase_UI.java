package testCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.BookingPage;
import pageObjects.HomePage;
import pageObjects.ItineraryPage;
import resources.Base;

public class TestCase_UI extends Base{

	
	@BeforeMethod
	public void launchURL() throws IOException
	{
		driver = invokeBrowser();
		driver.get(prop.getProperty("url"));
	}
	
	@Test(dataProvider = "getData")
	public void validateUITestCases(String source, String destination) throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		hp.selectRoundTrip();
		hp.getSourceDestination(source, destination);
		hp.getDates();
		hp.clickSearch();
		BookingPage bp = new BookingPage(driver);
		bp.clickBook();
		bp.validateFlightDetails();
		ItineraryPage ip = new ItineraryPage(driver);
		ip.getEmailDetails();
		ip.getTravellerDetails();
		ip.fareValidation();
	}
	
	@AfterMethod
	public void endTest()
	{
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] obj = new Object[1][2];
		obj[0][0] = "Bangalore";
		obj[0][1] = "New Delhi";
		return obj;
	}
}
