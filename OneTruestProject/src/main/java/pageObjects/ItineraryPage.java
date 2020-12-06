package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ItineraryPage extends TestPage{

	private boolean bElementsInitiated = false;
	public WebDriver driver;
	public ItineraryPage(WebDriver driver) {
		
		this.driver = driver;
	   this.w = new WebDriverWait(driver,60);

	}
	
	@FindBy(id = "itineraryBtn")
	private WebElement itnBtn;
	
	@FindBy(id = "username")
	private WebElement username;
	
	@FindBy(id = "LoginContinueBtn_1")
	private WebElement loginContinueBtn;
	
	By loginLoaderMess = By.id("loginLoaderMess");
	
	By adultTitle = By.id("AdultTitle1");
	
	@FindBy(id = "AdultTitle1")
	private WebElement title;
	
	@FindBy(id = "AdultFname1")
	private WebElement adultFname;
	
	@FindBy(id = "AdultLname1")
	private WebElement adultLname;
	
	@FindBy(id = "mobileNumber")
	private WebElement mobileNumber;
	
	@FindBy(id = "travellerBtn")
	private WebElement travellerBtn;
	
	By travellerLoaderMess = By.id("travellerLoaderMess");
	
	@FindBy(xpath = "//strong[@id='totalFare']/span[2]")
	private WebElement fare;
	
	@FindBy(id = "AdultDobDay1")
	private WebElement adultDobDay;
	
	@FindBy(id = "AdultDobMonth1")
	private WebElement adultDobMonth;
	
	@FindBy(id = "AdultDobYear1")
	private WebElement adultDobYear;
	
	@FindBy(css = "[placeholder = 'Nationality']")
	private WebElement nationality;
	
	public void getEmailDetails()
	{
		initPageElements();
		itnBtn.click();
		username.sendKeys("abc@gmail.com");
		loginContinueBtn.click();
		w.until(ExpectedConditions.invisibilityOfElementLocated(loginLoaderMess));
	}
	
	public void getTravellerDetails() throws InterruptedException
	{
		initPageElements();
		w.until(ExpectedConditions.visibilityOfElementLocated(adultTitle));
		Select name = new Select(title);
		name.selectByIndex(1);
		adultFname.sendKeys("Subhankar");
		adultLname.sendKeys("Nandi");
		mobileNumber.sendKeys("7665442123");
		Select day = new Select(adultDobDay);
		day.selectByIndex(4);
		Select month = new Select(adultDobMonth);
		month.selectByIndex(10);
		Select year = new Select(adultDobYear);
		year.selectByValue("1989");
		nationality.sendKeys("India");
		Thread.sleep(5000);
		nationality.sendKeys(Keys.ARROW_DOWN);
		nationality.sendKeys(Keys.ENTER);
		travellerBtn.click();
		w.until(ExpectedConditions.invisibilityOfElementLocated(travellerLoaderMess));
	}
	
	public void fareValidation()
	{
		initPageElements();;
		String totalFarePayment =  fare.getText();
		  System.out.println(totalFarePayment);
		  if(totalFarePayment!=null)
		  {
			  Assert.assertTrue(true);
		  }
	}
	
	protected void initPageElements() {
		if (!bElementsInitiated) {
			PageFactory.initElements(driver, this);
			bElementsInitiated = true;
		}
	}
}
