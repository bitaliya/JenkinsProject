package com.qa.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MouseHoverExample {
	WebDriver driver;
	@Test
	public void mouseHoverExampleTesting() throws IOException{
		
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream("C:\\Users\\BP\\eclipse-workspace\\mavenGithubJenkinsProject\\src\\main\\java\\com\\qa\\config\\config.properties");
	prop.load(fis);
	String browserName = prop.getProperty("browser");
	System.out.println(browserName);
	if(browserName.equals("chrome")) {	
	System.setProperty("webdriver.chrome.driver","C:\\browser\\chromedriver.exe");
	driver = new ChromeDriver();
	}else if(browserName.equals("FF")) {
	System.setProperty("webdriver.gecko.driver", "C:\\browser\\geckodriver.exe");
	driver = new FirefoxDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	driver.get(prop.getProperty("url"));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	//TakesScreenshot tc = (TakesScreenshot)driver;
	//File sc =tc.getScreenshotAs(OutputType.FILE);
	//FileUtils.copyFile(sc, new File(".\\screenshot.png"));
    Actions action=new Actions(driver);
	WebElement we=driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]"));
	action.moveToElement(we).build().perform();
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Find a Gift')]"))));
	driver.findElement(By.xpath("//span[contains(text(),'Find a Gift')]")).click();
	//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Computers, Tablets, & PC Products')]")))).click();	
	//driver.findElement(By.xpath("//span[contains(text(),'Computers, Tablets, & PC Products')]")).click();
	TakesScreenshot src= ((TakesScreenshot)driver);
	File ssfile= src.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(ssfile, new File("./screenshot/sc.png"));
	String pageTitle= driver.getTitle();
	System.out.println(pageTitle);
	Assert.assertEquals(pageTitle,"Gifts for Everyone | Amazon.com Gift Finder");
	driver.quit();
	}
}
