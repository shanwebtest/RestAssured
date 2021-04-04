package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class ExcelData {

	public static FileInputStream fileinput ;
	public static XSSFWorkbook wb ;
	public static XSSFSheet sh;
	public static XSSFRow row;
	public static XSSFCell cell;





	public ExcelData(String path, String sheetName) throws IOException{

		//  String path = System.getProperty("user.dir")+("\\Excel\\Webservices.xlsx");
		File file = new File(path);
		fileinput = new FileInputStream(file);
		wb = new XSSFWorkbook(fileinput);
		sh = wb.getSheet(sheetName);
	}

	public static String filepath() {
		String path = System.getProperty("user.dir")+("\\Excel\\Webservices.xlsx");
		return path;
	}
	
	
	//@Test
	public void Read() throws IOException {


		int rcnt = sh.getPhysicalNumberOfRows();  

		System.out.println("Number or rows : "+rcnt);

		//  System.out.println("Number columns : "+ccnt);
		for(int i=1; i<rcnt; i++) {
			int ccnt = sh.getRow(i).getPhysicalNumberOfCells();
			for(int j=0; j<ccnt; j++) {

				XSSFRow row = sh.getRow(i);
				DataFormatter format = new DataFormatter();
				Cell txt = row.getCell(j);
				String text = format.formatCellValue(txt);
				System.out.print(" "+text);		  
			}

			System.out.println("");		  
		}	  

	}


	public static int rowcount() {

		int rcnt = 0;
		try {
			rcnt = sh.getPhysicalNumberOfRows();
		} catch (Exception e) {
			System.out.println("Row exception message :"+e.getMessage());
			e.printStackTrace();
		}

		return rcnt;

	}

	public static int columcount() {	  

		int ccnt = 0;	  
		try {
			ccnt = sh.getRow(0).getPhysicalNumberOfCells();
		} catch (Exception e) {
			System.out.println("Column exception message :"+e.getMessage());
			e.printStackTrace();
		}

		return ccnt;


	}

	public static String getcellData(int rowNum, int cellNum) {

		cell = sh.getRow(rowNum).getCell(cellNum);

		DataFormatter format = new DataFormatter();  

		String ss = format.formatCellValue(cell);

		return ss;




	}


	@Test
	public void callDatatoTest() {

		int rows = rowcount();
		int cols = columcount();


		for(int i=0; i<=rows-1; i++) {

			for(int j=0; j<=cols-1; j++) {

				String txt = getcellData(i, 1); // get data from the cell 

				if(txt!=null) {
					System.out.print(" values :"+txt);

					break;
				}

			}
			System.out.println(" ");
		}

	   }
	
	
	//create object array to pass values to data provider
	public static Object[][] dataObject(String path, String sheetName) throws IOException {
		ExcelData exel = new ExcelData(path, sheetName);// pass file path and sheet name to constructor.
		
		int rows = rowcount();
		int cols = columcount();
             
		Object data[][] = new Object[rows-1][cols]; 
		
		for(int i=1; i<=rows-1; i++) {

			for(int j=0; j<=cols-1; j++) {

				String txt = getcellData(i, j); // get data from the cell 
				
				data[i-1][j] = txt;

				if(txt!=null) {
					System.out.print(" values :"+txt);

					break;
				}

			}
			System.out.println(" ");
		}
		
		return data;
	}



}
