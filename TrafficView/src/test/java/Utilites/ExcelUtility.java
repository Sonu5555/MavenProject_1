package Utilites;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ExcelUtility {
private static FileInputStream fis;
private static XSSFWorkbook workbook;
private static XSSFSheet sheet;
private static XSSFRow row;
static List<String> v;
	public static void loadexcel() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\excel\\te.xlsx";
		File file=new File(path);
		fis=new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet("Sheet2");
		System.out.println("111");
		fis.close();
	}
	
	public static Map<String, Map<String, String>> readallData() throws Exception
	{
		if(sheet==null) {
			loadexcel();
		}
		System.out.println("222");
		Map<String, Map<String, String>> superMap=new HashMap<String, Map<String, String>>();
		Map<String, String> myMap=new HashMap<String, String>();
		
		for(int i=0;i< sheet.getLastRowNum() + 1;i++ ) {
			row=sheet.getRow(i);
			String keyCell=row.getCell(0).getStringCellValue();
			
			int colNum=row.getLastCellNum();
//			System.out.println("Columncount :"+colNum);
			for(int j=1;j<colNum;j++) {
				String value=row.getCell(j).getStringCellValue();
//				System.out.println("VALUE IS"+value);
				myMap.put(keyCell, value);
			
			}
			superMap.put("MASTERDATA", myMap);
		}
		return superMap;
	}
	
	public static String getValue(String key) throws Exception {
		Map<String, String> myVal=readallData().get("MASTERDATA");
		String retValue=myVal.get(key);

		return retValue;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("444");
		System.out.println(getValue("POPPCMD"));
		System.out.println(getValue("FROM"));
		System.out.println(getValue("TO"));

	}
}
