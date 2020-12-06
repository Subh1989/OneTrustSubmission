package pageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends TestPage{

	private boolean bElementsInitiated = false;
	public WebDriver driver;
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
	   this.w = new WebDriverWait(driver,60);

	}
	
	@FindBy(id = "RoundTrip")
	private WebElement roundTrip;
	
	@FindBy(id = "FromTag")
	private WebElement from;
	
	@FindBy(id = "ToTag")
	private WebElement to;
	
	@FindBy(id = "DepartDate")
	private WebElement departDate;
	
	@FindBy(css = "[data-handler='selectDay']")
	private List<WebElement> allDates;
	
	@FindBy(id = "ReturnDate")
	private WebElement retunDate;
	
	@FindBy(id = "SearchBtn")
	private WebElement searchButton;
	
	public void selectRoundTrip()
	{
		initPageElements();
		roundTrip.click();
	}
	
	public void getSourceDestination(String source, String destination) throws InterruptedException
	{
		initPageElements();
		from.sendKeys(source);
	    Thread.sleep(3000);
	    from.sendKeys(Keys.ARROW_DOWN);
	    from.sendKeys(Keys.ENTER);
	    to.sendKeys(destination);
	    Thread.sleep(3000);
	    to.sendKeys(Keys.ARROW_DOWN);
	    to.sendKeys(Keys.ENTER);
	}
	
	public void getDates()
	{
		initPageElements();
		departDate.click();
	    int dateCount = allDates.size();
	    for(int i=0; i<dateCount; i++)
	    {
	    	if(i==2)
	    	{
	    		allDates.get(i).click();
	    		break;
	    	}
	    }
	    
	    retunDate.click();
	    for(int i=0; i<dateCount; i++)
	    {
	    	if(i==5)
	    	{
	    		allDates.get(i).click();
	    		break;
	    	}
	    }
	}
	
	public void clickSearch()
	{
		initPageElements();
		searchButton.click();
	}
	
	
	protected void initPageElements() {
		if (!bElementsInitiated) {
			PageFactory.initElements(driver, this);
			bElementsInitiated = true;
		}
	}
}
