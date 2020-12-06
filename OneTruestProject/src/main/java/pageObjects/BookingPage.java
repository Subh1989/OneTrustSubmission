package pageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BookingPage extends TestPage{

	private boolean bElementsInitiated = false;
	public WebDriver driver;
	public BookingPage(WebDriver driver) {
		
		this.driver = driver;
	   this.w = new WebDriverWait(driver,60);

	}
	
	@FindBy(xpath = "//span[contains(text(),'Details')]")
	private WebElement details;
	
	@FindBy(xpath = "//div[@class='pt-2 ']/p[1]")
	private List<WebElement> flightLists;
	
	@FindBy(xpath = "//div[contains(@class,'flex-right')]/div/button")
	private WebElement book;
	
	@FindBy(css = "[class='flightNumber']")
	private List<WebElement> flightDetails;
	
	String departFlight;
	String returnFlight;
	
	public void clickBook()
	{
		initPageElements();
		details.click();
	    List<WebElement> list = flightLists;
	    departFlight = list.get(0).getText();
	    returnFlight = list.get(1).getText();
	    book.click();
	}
	
	public void validateFlightDetails()
	{
		initPageElements();
		Set<String> S =  driver.getWindowHandles();
		Iterator<String> it = S.iterator();
		String parentWindow = it.next();
		String childWindow = it.next();
	    driver.switchTo().window(childWindow);
		List<WebElement> list1 = flightDetails;
		String departFlightFinal = list1.get(0).getText();
		String returnFlightFinal = list1.get(1).getText();
		Assert.assertEquals(departFlight, departFlightFinal);
		Assert.assertEquals(returnFlight, returnFlightFinal);
	}
	
	
	protected void initPageElements() {
		if (!bElementsInitiated) {
			PageFactory.initElements(driver, this);
			bElementsInitiated = true;
		}
	}
}
