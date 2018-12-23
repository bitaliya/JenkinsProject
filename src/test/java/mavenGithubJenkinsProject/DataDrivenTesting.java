package mavenGithubJenkinsProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTesting {
	@Test(dataProvider="testdata")
	public void testData(String amount, String rate, String term){
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\BP\\Downloads\\chromedriver_win32\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
   // JavascriptExecutor js = (JavascriptExecutor) driver;
	driver.manage().window().maximize(); 
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    driver.get("https://www.carpaymentcalculator.net");
    //js.executeScript("window.scrollBy(0,600)");
    driver.findElement(By.xpath(".//input[@value='30000']")).clear();
    driver.findElement(By.xpath(".//input[@value='30000']")).sendKeys(amount);
    driver.findElement(By.xpath(".//div[@id='tab1']//input[@value='4.5']")).clear();
    driver.findElement(By.xpath(".//div[@id='tab1']//input[@value='4.5']")).sendKeys(rate);
    driver.findElement(By.xpath(".//div[@id='tab1']//input[@value='6']")).clear();
    driver.findElement(By.xpath(".//div[@id='tab1']//input[@value='6']")).sendKeys(term);
    //Thread.sleep(6000);
    /*System.out.println(driver.getTitle());
    List<WebElement> allLinks= driver.findElements(By.tagName("a"));
    int a=allLinks.size();
    System.out.println(a);
    Iterator<WebElement> itr = allLinks.iterator();
    while(itr.hasNext()) {
    System.out.println("Link Number "+ i +" : " +itr.next().getText());	
    i++;
    }*/
    driver.quit();
	}
	
	@DataProvider(name="testdata")
	public Object[][] data1() throws IOException{
	/*Object[][] data= new Object[2][3];
	data[0][0]= "20000";
	data[0][1]= "4";
	data[0][2]= "7";
	data[1][0]= "25000";
	data[1][1]= "5";
	data[1][2]= "8";
	System.out.println(data);
	return data;*/
		XSSFWorkbook wb = null;
		
		FileInputStream fis=new FileInputStream(new File("C:\\Users\\BP\\Desktop\\loantest.xlsx"));
		wb = new XSSFWorkbook(fis);
		XSSFSheet ws=wb.getSheetAt(0);
		int rows=ws.getLastRowNum();
		int cols=ws.getRow(0).getLastCellNum();
		//System.out.println(rows);
		//System.out.println(cols);
		Object[][] data= new Object[rows][cols];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++) {
				data[i][j]= ws.getRow(i+1).getCell(j).toString();
				//System.out.println(data[i][j]);
			}
		
		}
		wb.close();
		return data;
	
	}


}
