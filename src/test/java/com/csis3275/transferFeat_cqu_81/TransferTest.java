package com.csis3275.transferFeat_cqu_81;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class TransferTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void transfer() {
		driver.get("http://localhost:8080/login");
		driver.manage().window().setSize(new Dimension(847, 832));
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("user@fake.com");
		driver.findElement(By.id("password")).click();
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
		driver.findElement(By.cssSelector("form")).click();
		driver.findElement(By.cssSelector(".btn-primary")).click();
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
		driver.findElement(By.id("amount")).sendKeys("25");
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
		driver.findElement(By.id("amount")).sendKeys("23");
		driver.findElement(By.cssSelector(".btn-primary")).click();
	}
}
