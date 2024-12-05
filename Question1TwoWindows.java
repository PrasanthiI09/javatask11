package javatask11;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Question1TwoWindows {

static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		instantiatebrowser();
		NewWindow();
		}
	public static void instantiatebrowser() throws InterruptedException {
		//Open Chrome Browser. Below is the only way the driver is opening in my Laptop.
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\prabh\\eclipse-workspace\\GuviMavenProject\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		//Open Guvi URL
		driver.get("https://the-internet.herokuapp.com/windows");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	public static WebElement explicitWaitForElement(By byElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement myElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
		return myElement;
	}

	
	public static void NewWindow()
	{
		
		explicitWaitForElement(By.xpath("//a[contains(@href,'/windows/new')]")).click();
		
		
		String FirstWindow = driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		for(String windowhandle : allwindows)
		{
			if(!windowhandle.equalsIgnoreCase(FirstWindow))
			{
			
				driver.switchTo().window(windowhandle);
				String titleofsecondwindow = driver.getTitle();
				System.out.println("Title of New Window is ---- "+titleofsecondwindow);
				WebElement myElement = driver.findElement(By.tagName("h3"));
				String text = myElement.getText();
				System.out.println("Text Displayed on New Window is --- "+text);
				driver.close();
			}   
			
		}
		driver.switchTo().window(FirstWindow);
		String titleOfFirstWindow = driver.getTitle();
		System.out.println("First Window is Active and Title Of First Window is :: " + titleOfFirstWindow);
		driver.close();
	}
}
