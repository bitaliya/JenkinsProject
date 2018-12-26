package com.qa.testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropDownTesting {
	@Test
	public void testDropDown() {
		System.setProperty("webdriver.chrome.driver","C:\\browser\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get("https://www.depositaccounts.com");
		//Actions action = new Actions(driver);
		driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'CD Rates')]")).click();
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(),'6 Month CD']"))));
		//driver.findElement(By.xpath("//a[contains(text(),'6 Month CD']")).click();
		Select dropDown=new Select(driver.findElement(By.xpath("//select[@id='ctl00_ctl00_ctl00_MainContent_MainContent_DropdownNav']")));
		//wait.until(ExpectedConditions.visibilityOf());
		//dropDown.selectByVisibleText("3 Year CD");
		String titleName = driver.getTitle();
		Assert.assertEquals(titleName, "Compare CD Rates for December 2018 | 1 Year CD Rates | DepositAccounts");
		System.out.println(titleName);
		List<WebElement> list =dropDown.getOptions();
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
		System.out.println(list.get(i).getText());	
		}
		dropDown.selectByIndex(0);
	    driver.quit();
	}
}
		