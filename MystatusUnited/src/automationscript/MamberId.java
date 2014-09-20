package automationscript;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import objectrepositroy.Mamberidlocater;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;





import bean.MamberData;
import dblayer.Exceldatamanager;
import utilites.BussenesCompernat;
import utilites.MystatusUnitedAbstract;
import utilites.MystatusUniteddriver;
import utilites.PropertyHandler;
/*@Authore:Jitu
 *@version:1.0
 *@Descrption:mainly this class validate the status of for 2015.
 *  
 * */
public class MamberId extends MystatusUnitedAbstract {
	//Load loger class
	private static Logger Log = Logger.getLogger(MamberId.class.getName());
	//create object of BussenesCompernat to acess commen method
	BussenesCompernat util=new BussenesCompernat();
	//load Mamberidlocater object 
	private Mamberidlocater mamberidlocater=null;
	//create one string for validation propuse
	private String message1=null;
	private  String message2=null;
	//create object of exceldatamanager
	Exceldatamanager exceldatamanager=new Exceldatamanager();
	@BeforeClass
	public void openUrlTest(){
		//load the xml file.
		DOMConfigurator.configure("..\\MystatusUnited\\src\\dblayer\\log4j.xml");
		Log.info("oepn firefox browser");
		//call firefox browser
		util.getfirefox();
		Log.info("url scuces fully loded:"+PropertyHandler.getProperty("URL"));
		//get usrl from properties file use of property hadeler
		MystatusUniteddriver.Driver.get(PropertyHandler.getProperty("URL"));
		//wait some time 
		MystatusUniteddriver.Driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
		//maximise the screen.
		MystatusUniteddriver.Driver.manage().window().maximize();
		//copy excel data.input excel data sheet to output data sheet
		Log.info("copay complete");
		exceldatamanager.copyData();
	}
	@BeforeMethod
	public void loadobjectTest(){
		//loade mamberidlocater
		Log.info("load locater");
		mamberidlocater= loadObject(new Mamberidlocater());
	}
	@Test
	public void MamberId_TC1() throws InvalidFormatException, IOException{
		//write the titele of webpage
		Log.info("Title of projce:"+MystatusUniteddriver.Driver.getTitle());
		List<MamberData> MamberDataList = exceldatamanager.getMamberDataList(0);
		System.out.println(MamberDataList.size());
		for (int i = 0; i < MamberDataList.size(); i++)
		{
			try
			{	
				mamberidlocater.getMemberid().clear();			
				mamberidlocater.getMemberid().sendKeys(MamberDataList.get(i).getAcountId());
				mamberidlocater.getPassword().clear();
				mamberidlocater.getPassword().sendKeys(MamberDataList.get(i).getPassWord());
				mamberidlocater.getRunDat().clear();
				mamberidlocater.getRunDat().sendKeys(MamberDataList.get(i).getRundate());
				mamberidlocater.getSubmitButton().click();
				MystatusUniteddriver.Driver.manage().timeouts().implicitlyWait(0, TimeUnit.MINUTES);
				try
				{
					message1=MystatusUniteddriver.Driver.findElement(By.xpath("//h2[@class='title-sub-opener']")).getText();
					message2=MystatusUniteddriver.Driver.findElement(By.xpath("//span[@class='bold-userlevel']/a")).getText();
					exceldatamanager.setExcelStringData(0,i+1,5,message1+message2);
				}
				catch(Exception e)
				{
					exceldatamanager.setExcelStringData(0,i+1,5,"sucesses fully message not presnt");
					Log.info("message:"+message1+message2);
				}
				MystatusUniteddriver.Driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
				MystatusUniteddriver.Driver.navigate().back();
			}
			catch(Exception e)
			{
				Log.info("exception in main class");
				exceldatamanager.setExcelStringData(0,i+1,5,"exception found");
			}
		}
	}
	@AfterMethod
	public void takeScreenhoot(){
		util.getScreenShot("newarrival.jpg");
	}
	@AfterClass
	public void takeClose(){
	MystatusUniteddriver.Driver.quit();
	}
}
