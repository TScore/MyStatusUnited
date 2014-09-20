package utilites;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dblayer.Exceldatamanager;

/*@Authore:Tapana
 *@version:1.0
 *@Descrption:in this class cover all common method or respited method.
 * 
 * */
public class BussenesCompernat extends MystatusUnitedAbstract {
	Exceldatamanager exceldatamanager=new Exceldatamanager();
	public void getfirefox(){
		//open firefox driver
		MystatusUniteddriver.Driver=new FirefoxDriver();	
	}	
	//open browser in chrome
	public void getChrome(){
		//set the path of chrome
		System.setProperty("webdriver.chrome.driver",PropertyHandler.getProperty("ChromeDriver"));
		//open chrome driver
		MystatusUniteddriver.Driver=new ChromeDriver();
	}

	public void checkAlert() {
		try {
			WebDriverWait wait = new WebDriverWait(MystatusUniteddriver.Driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = MystatusUniteddriver.Driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			//exception handling
		}
	}

	public void waitForXpath(String xpath) {
		WebDriverWait wt = new WebDriverWait(MystatusUniteddriver.Driver,20);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	public void waitForIDPresent(String id) {
		WebDriverWait wt = new WebDriverWait(MystatusUniteddriver.Driver, 50);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	public void waitForName(String name){
		WebDriverWait wt=new WebDriverWait(MystatusUniteddriver.Driver,50);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
	}
	public void takeScreenShot(String testName) {	
		try {
			MystatusUniteddriver.Driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
			//new File("../CiscoMapsAutomation/ScreenShotFile/").mkdirs();		
			//Timestamp timestamp = new Timestamp(new Date().getTime());
			//String filename = "../CiscoMapsAutomation/ScreenShotFile/"+testName+timestamp+ ".jpg";	
			Timestamp timestamp = new Timestamp(new Date().getTime());
			//String filename = "../CiscoMapsAutomation/ScreenShotFile/"+testName+ ".jpg";
			String filename = "..\\Yatra\\reportlayer\\ScrenShoot\\"+testName+timestamp+".jpg";
			WebDriver augmentedDriver = new Augmenter().augment(MystatusUniteddriver.Driver);
			File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(filename), true);
		} catch (IOException e) {
			//Log.info("IO Exception in taking screen shot");
		} catch (Exception e) 
		{
			//Log.warn(e);
			//Log.info("exception in taking screen shot");
		}
	}

	public void getScreenShot(String imagenamee){
		File scrFile = ((TakesScreenshot)MystatusUniteddriver.Driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(scrFile, new File("..//BooHoo//reportlayer//ScrenShoot//"+imagenamee));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void getdroupDown(WebElement element){
		Select selectList = new Select(element);
		List<WebElement> options = selectList.getOptions();
	}
	public void getCalandar(String moth,String day){
	waitForXpath("//div[@id='ui-datepicker-div']/div/div");
	String mothname=MystatusUniteddriver.Driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/div")).getText();
	System.out.println("mothname:"+mothname);
	if(mothname.equalsIgnoreCase(moth))
	{
	MystatusUniteddriver.Driver.findElement(By.xpath("//a[text()='"+day+"']")).click();	
	}
	else
	{
		MystatusUniteddriver.Driver.findElement(By.xpath("//a[@title='Next']")).click();
	}
	}
}



