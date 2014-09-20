package utilites;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author:Jitu
 * @version 1.0
 * @created 16-June-2014 1:14:46 PM
 */
public class PropertyHandler {

	private static String fileName = "mystatus.properties";
	private static Properties properties;
	public PropertyHandler(){
	}

	public void finalize() throws Throwable {
	}
	/**
	 *
	 * @param key
	 */
	public static String getProperty(String key){
		try{
			InputStream is = PropertyHandler.class.getResourceAsStream("/"+fileName);
			if(is==null)
			{
				throw new FileNotFoundException("path mentioned is wrong");
			}
			properties=new Properties();
			properties.load(is);
		}catch(FileNotFoundException fnfe){
			// log.error("File Not Found Error",fnfe);	
		}catch(IOException ie){
			// log.error("IO Error",ie);
		}
		String value=properties.getProperty(key);
		return value;
	}	
}