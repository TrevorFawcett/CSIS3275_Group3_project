package com.csis3275.transferFeat_cqu_81;



import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class FormTest {
	
private static FirefoxDriver driver;
	
	@BeforeAll
	public static void setUp()	{
		FirefoxOptions options = new FirefoxOptions();
		//options.setImplicitWaitTimeout(Duration.ofSeconds(10));
		
		
		driver = new FirefoxDriver(options);
		
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	@AfterAll
	public static void tearDown()	{
		//Quit the browser
		driver.quit();
	}

  @Test
  public void form() {
    driver.get("http://localhost:8080");
    driver.manage().window().setSize(new Dimension(550, 693));
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys("user@fake.com");
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.cssSelector(".inputBox:nth-child(4) > input")).click();
    driver.findElement(By.id("fromAccount")).click();
    {
      WebElement dropdown = driver.findElement(By.id("fromAccount"));
      dropdown.findElement(By.xpath("//option[. = 'Checking Account']")).click();
    }
    driver.findElement(By.cssSelector("#fromAccount > option:nth-child(2)")).click();
    driver.findElement(By.id("toAccount")).click();
    {
      WebElement dropdown = driver.findElement(By.id("toAccount"));
      dropdown.findElement(By.xpath("//option[. = 'Checking Account']")).click();
    }
    driver.findElement(By.cssSelector("#toAccount > option:nth-child(2)")).click();
    driver.findElement(By.id("amount")).click();
    driver.findElement(By.id("amount")).sendKeys("1");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("form")).click();
    driver.findElement(By.id("fromAccount")).click();
    {
      WebElement dropdown = driver.findElement(By.id("fromAccount"));
      dropdown.findElement(By.xpath("//option[. = 'Cashback Card']")).click();
    }
    driver.findElement(By.cssSelector("#fromAccount > option:nth-child(4)")).click();
    driver.findElement(By.id("toAccount")).click();
    {
      WebElement dropdown = driver.findElement(By.id("toAccount"));
      dropdown.findElement(By.xpath("//option[. = 'Rewards Card']")).click();
    }
    driver.findElement(By.cssSelector("#toAccount > option:nth-child(5)")).click();
    driver.findElement(By.id("amount")).click();
    driver.findElement(By.id("amount")).sendKeys("23");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.id("fromAccount")).click();
    {
      WebElement dropdown = driver.findElement(By.id("fromAccount"));
      dropdown.findElement(By.xpath("//option[. = 'Checking Account']")).click();
    }
    driver.findElement(By.cssSelector("#fromAccount > option:nth-child(2)")).click();
    driver.findElement(By.id("toAccount")).click();
    {
      WebElement dropdown = driver.findElement(By.id("toAccount"));
      dropdown.findElement(By.xpath("//option[. = 'Rewards Card']")).click();
    }
    driver.findElement(By.cssSelector("#toAccount > option:nth-child(5)")).click();
    driver.findElement(By.id("amount")).click();
    driver.findElement(By.id("amount")).sendKeys("75");
    driver.findElement(By.cssSelector(".btn-primary")).click();
  }
}
