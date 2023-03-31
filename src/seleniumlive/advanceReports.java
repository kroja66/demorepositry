package seleniumlive;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;

public class advanceReports {
	
	WebDriver driver;
	ExtentReports reports;
	ExtentTest test;
	@BeforeTest
	public void generateReport() {
		
		reports=new ExtentReports("./ExtentReports/Demo.html");
	}
	@BeforeMethod
	public void setUp() {
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://google.com");
		
	}
	
	@Test
	public void testcase1() {
		test=reports.startTest("Pass Test");
		test.assignAuthor("roja");
		test.assignCategory("Functional");
		String Expected_Title="Google";
		String Actual_title=driver.getTitle();
		if(Expected_Title.equalsIgnoreCase(Actual_title)) {
			test.log(LogStatus.PASS, "Title name is matching:::"+Expected_Title+"  "+Actual_title);
		}
		else {
			test.log(LogStatus.FAIL, "Title name is not matching:::"+Expected_Title+"  "+Actual_title);
		}
		
	}
	
	@Test
	public void testcase2() {
	
		test=reports.startTest("Fail Test");
		test.assignAuthor("roja");
		test.assignCategory("Functional");
		String Expected_Title="Gmail";
		String Actual_title=driver.getTitle();
		if(Expected_Title.equalsIgnoreCase(Actual_title)) {
			test.log(LogStatus.PASS, "Title name is not matching:::"+Expected_Title+"  "+Actual_title);
		}
		else {
			test.log(LogStatus.FAIL, "Title name is matching:::"+Expected_Title+"  "+Actual_title);
		}
	}

	@AfterMethod
	public void tearDown() {
		reports.endTest(test);
		reports.flush();
		driver.quit();
}
}

