package objectrepositroy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Mamberidlocater {
	//new arrivals module.
	@FindBy(id="memberid")
	private WebElement memberid;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="datepicker")
	private WebElement runDat;
	@FindBy(id="mberIdBtn")
	private WebElement submitButton;
	@FindBy(id="viewLiveDataButton")
	private WebElement viewLiveDataButton;
	//message validation text
	@FindBy(xpath="//section[@id='firstRow']/h2")
	private WebElement valideMessage;
	
	public WebElement getValideMessage() {
		return valideMessage;
	}
	public WebElement getMemberid() {
		return memberid;
	}
	public WebElement getPassword() {
		return password;
	}
	public WebElement getRunDat() {
		return runDat;
	}
	public WebElement getSubmitButton() {
		return submitButton;
	}
	public WebElement getViewLiveDataButton() {
		return viewLiveDataButton;
	}
	
	

}
