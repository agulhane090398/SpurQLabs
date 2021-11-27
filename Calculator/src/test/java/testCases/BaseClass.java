package testCases;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;
import com.automation.utilities.ReadConfig;

public class BaseClass {

	ReadConfig rc = new ReadConfig();

	public static WebDriver driver;
	public String baseURL = rc.getApplicationURL();
	public String oper = rc.getOperation();
	public String result = rc.getRes();
	public String num1 = rc.getnum1();
	public String num2 = rc.getnum2();
	public static Logger logger;
	public static String repName;
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws InterruptedException {
		logger = Logger.getLogger("Calculator");
		//configuring the log4j file
		PropertyConfigurator.configure("Log4j.properties");

		//parameter is matched as per requirement
		if (br.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", rc.getChromepath());
			driver = new ChromeDriver();
		}
		
		else if (br.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", rc.getFirefoxpath());
			driver = new FirefoxDriver();
		}
		
		//open URL
		driver.get(baseURL);
		//Maximize the window
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		//all tabs are closed after completion of test
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		//All SCREENSHOTS are captured and stored in Screenshots folder
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report-" + timeStamp + ".html";	//Name of REPORT
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		
											//relative path of folder
		File trg=new File(System.getProperty("user.dir")+"/Screenshots/"+" "+repName+".png");
		FileUtils.copyFile(src, trg);
		System.out.println("Screenshot taken");
		
	}
}
