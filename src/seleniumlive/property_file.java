package seleniumlive;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class property_file {
	
	WebDriver driver;
	Properties con;
	
@BeforeTest
 public void setup() throws Throwable
{
	
	con =new Properties();
	con.load(new FileInputStream("OR.properties"));
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(con.getProperty("Url"));
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
}

	@Test
	public void verifyLogin() {
		driver.findElement(By.xpath(con.getProperty("Objreset"))).click();
		driver.findElement(By.xpath(con.getProperty("Objuser"))).sendKeys("admin");
		driver.findElement(By.xpath(con.getProperty("Objpass"))).sendKeys("master");
		driver.findElement(By.xpath(con.getProperty("Objloginbtn"))).click();
		
		String Expected_Title="Dashboard « Stock Accounting";
		String Actual_Title= driver.getTitle();
		if(Expected_Title.equalsIgnoreCase(Actual_Title))
		{
			Reporter.log("Login Success::"+Expected_Title+"  "+Actual_Title ,true);
		}else
		
		{
			Reporter.log("Login Fail::"+Expected_Title+""+Actual_Title,true);
	}
	}
	
	@AfterTest
	public void tearDown() {
		
		driver.quit();
		
	}
		
	
	
	

	

}
