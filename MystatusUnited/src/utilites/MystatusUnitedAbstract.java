package utilites;
import org.openqa.selenium.support.PageFactory;
// abstract class to initialize the page factory classes
public abstract class MystatusUnitedAbstract {
//generic method to initialize the page factory class 
@SuppressWarnings("unchecked")
public <T> T loadObject(T t){
	return (T) PageFactory.initElements(MystatusUniteddriver.Driver, t.getClass());
}
}
