package javatask11;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Question2NestedFrames {
static WebDriver driver;

	public static void main(String[] args) throws InterruptedException
	{
		instantiatebrowser();
		nestedFrames();
		driver.close();
	}
	
	public static void instantiatebrowser() throws InterruptedException
	{
		//Open Chrome Browser. Below is the only way the driver is opening in my Laptop.
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\prabh\\eclipse-workspace\\GuviMavenProject\\chromedriver\\chromedriver.exe");
				driver = new ChromeDriver();
				//Open Guvi URL
				driver.get("https://the-internet.herokuapp.com/nested_frames");
				driver.manage().window().maximize();
				Thread.sleep(2000);
	}
	
	
	//Expilicitwaitmethod
	public static WebElement explicitWaitForElement(By byElement)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement myElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
		return myElement;
	}
	
	
	//NestedFrames Method
	public static void nestedFrames()
	{
		
		//Switch to Top Frame and verifying number of frames is 3.
		driver.switchTo().frame(explicitWaitForElement(By.xpath("//frame[@name='frame-top']")));
		List<WebElement> frames = driver.findElements(By.tagName("frame"));
		  
		  if(frames.size()==3)
		  {
			  System.out.println("Top frame has 3 frames");
		  }
		  
		//Switch to LEFT Frame  
		driver.switchTo().frame(explicitWaitForElement(By.xpath("//frame[@name='frame-left']")));
		WebElement myElement = driver.findElement(By.xpath("//body[contains(text(),'    LEFT')]"));
		String text = myElement.getText();
		System.out.println("Text in the Left Frame is -- "+text);
		driver.switchTo().parentFrame();
		//driver.switchTo().frame(explicitWaitForElement(By.xpath("//frame[@name ='frame-top']")));
		System.out.println("Switched to the Top Frame");
		
		//Switch to MIDDLE Frame from Top Frame
		driver.switchTo().frame(explicitWaitForElement(By.xpath("//frame[@name='frame-middle']")));
		myElement = driver.findElement(By.xpath("//div[contains(text(),'MIDDLE')]"));
		text = myElement.getText();
		System.out.println("Text in the Middle Frame is -- "+text);
		driver.switchTo().parentFrame();
		System.out.println("Switched to the Top Frame");
		
		//Switch to RIGHT Frame from TOP Frame
		driver.switchTo().frame(explicitWaitForElement(By.xpath("//frame[@name='frame-right']")));
		myElement = driver.findElement(By.xpath("//body[contains(text(),'    RIGHT')]"));
		text = myElement.getText();
		System.out.println("Text in the Right Frame is -- "+text);
		driver.switchTo().parentFrame();
		System.out.println("Switched again to Top Frame");
		
		//Switch to Main Content from TOP Frame
		driver.switchTo().defaultContent();
		System.out.println("Switching to default content to navigate to BOTTOM Frame");
		
		//Switch to BOTTOM Frame
		driver.switchTo().frame(explicitWaitForElement(By.xpath("//frame[@name='frame-bottom']")));
		System.out.println("In BOTTOM Frame");
		myElement=explicitWaitForElement(By.xpath("//body[contains(text(),'BOTTOM')]"));
		text = myElement.getText();
		System.out.println("Text in the Bottom Frame is -- "+text);
		
		//Switch to Default content to switch to TOP Frame again
		driver.switchTo().defaultContent();
		System.out.println("Switching to default content to navigate to TOP Frame");
		driver.switchTo().frame(explicitWaitForElement(By.xpath("//frame[@name='frame-top']")));
		System.out.println("Switched back to the Top Frame from Main Content");
		driver.switchTo().defaultContent();
		text = driver.getTitle();
		System.out.println("Title of Frames is -- "+text);
		
	}
	
}
