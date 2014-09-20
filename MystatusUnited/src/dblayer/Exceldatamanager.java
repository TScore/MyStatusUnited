package dblayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import bean.MamberData;



import utilites.PropertyHandler;
/*@Authore:jitu
 *@Version:1.0
 *ExcelDataExctrater for data extracted from excel sheet and it
 *only for data retrieve from excel data.
 * 
 * 
 * */
public class Exceldatamanager {
	//Load  the logger file.
	private static Logger Log = Logger.getLogger(Exceldatamanager.class.getName());
	//create Hssfsheet method for collation  of sheet.
	public void copyData(){
		try {
			FileInputStream file = new FileInputStream(new File(PropertyHandler.getProperty("InPutDataFile")));
			//Get the workbook instance for XLS file 
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:

						break;
					case Cell.CELL_TYPE_NUMERIC:

						break;
					case Cell.CELL_TYPE_STRING:

						break;
					}
				}
			}
			file.close();
			FileOutputStream out =new FileOutputStream(new File(PropertyHandler.getProperty("OutPutDataFile")));
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			Log.info("exception in reading  data from list");
		} catch (IOException e) {
			Log.info("exception in reading  data from list");
		}
	}
	//set the data in excel sheet
	public void setExcelStringData(int sheetNo, int rowNum , int columeNum , String data) throws InvalidFormatException, IOException{
		
		FileInputStream fis = new FileInputStream(PropertyHandler.getProperty("OutPutDataFile"));
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheetAt(sheetNo);
		Row r = sh.getRow(rowNum);
		Cell c = r.createCell(columeNum);
		c.setCellType(Cell.CELL_TYPE_STRING);
		c.setCellValue(data);
		//set the value in out put data(OutPutDataFile)
		FileOutputStream fos = new FileOutputStream(PropertyHandler.getProperty("OutPutDataFile"));
		wb.write(fos);	 
	}
	public void setOverallResult(int sheetNo, int rowNum , int columeNum , int data) throws InvalidFormatException, IOException{
		
		FileInputStream fis = new FileInputStream(PropertyHandler.getProperty("OVERALLRESULT"));   //("result"));
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheetAt(sheetNo);
		Row r = sh.getRow(rowNum);
		Cell c = r.createCell(columeNum);
		c.setCellType(Cell.CELL_TYPE_NUMERIC);
		c.setCellValue(data);
		//set the value in out put data(OutPutDataFile)
		FileOutputStream fos = new FileOutputStream(PropertyHandler.getProperty("OVERALLRESULT")); //("result"));
		wb.write(fos);	 
	}	
	private HSSFSheet getHssfSheet(Integer sheetNumber){
		//create HSSFSheet object.
		Log.info("creat object Of Hssfsheet");
		HSSFSheet hssfSheet = null;	
		try {
			//excel sheet data input in FileInputStream and create object file
			Log.info("loade file of excel sheet");
			FileInputStream file = new FileInputStream(new File(PropertyHandler.getProperty("InPutDataFile")));
			//create hssfWorkbook for  xls file excel sheet.
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file);
			//get the sheet of excel sheet and store in hssfSheet
			hssfSheet = hssfWorkbook.getSheetAt(sheetNumber);
		} catch (FileNotFoundException e) {		
			Log.info("exception in reading  data from list");
		} catch (IOException e) {
			Log.info("exception in reading  data from list");
		}
		return hssfSheet;
	}
	//Useardetaildata lode in excel sheet method

	public List<MamberData> getMamberDataList(Integer sheetNumber){
		List<MamberData> MamberDataList = null;		

		try{	

			HSSFSheet hssfSheet = getHssfSheet(sheetNumber);
			if(hssfSheet == null){
				return null;
			}

			//Row number starts with 0
			int totalRows = hssfSheet.getLastRowNum();			
			if(totalRows!=0){
				//column number starts with 1				
				MamberDataList = new ArrayList<MamberData>();				
				//0th row contains column headings
				for (int i = 1; i <= totalRows; i++) {
					MamberData mamberData = new MamberData();					
					Row row = hssfSheet.getRow(i);
					//set the data to the variables defined in peopleData class
					mamberData.setAcountId( row.getCell(0).getStringCellValue()!=null ? row.getCell(0).getStringCellValue() : null );
					mamberData.setPassWord( row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue() : null );
					SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
					String cellvalue =dateformat.format(row.getCell(2).getDateCellValue()!=null ? row.getCell(2).getDateCellValue() :null); 
					//String cellvalue=row.getCell(1).getStringCellValue()!=null ? row.getCell(1).getStringCellValue():null;
					System.out.println("dateformat:"+cellvalue);
					mamberData.setRundate(cellvalue);
					//adding values to Peopledatalist
					MamberDataList.add(mamberData);					
				}
			}				
		}catch(Exception e){
			Log.info("exception in reading people data list");
		}		
		return MamberDataList;
	}


}
