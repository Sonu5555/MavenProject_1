package Utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilityList {
	
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	
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
	
	public static List<Map<String, String>> readallData() throws Exception
	{
		if(sheet==null) {
			loadexcel();
		}
		System.out.println("222");
		
		List<Map<String, String>> mapLists=new ArrayList();
		
		int rows=sheet.getLastRowNum();
		row=sheet.getRow(0);
		
		for(int j=1;j< row.getLastCellNum();j++ ) {
			
			Map<String, String> myMap=new HashMap<>();
			
			for(int i=0;i<rows + 1;i++) {
				Row r=CellUtil.getRow(i, sheet);
				String key=CellUtil.getCell(r,0).getStringCellValue();
				String value=CellUtil.getCell(r,j).getStringCellValue();
				
				myMap.put(key, value);
				}
			mapLists.add(myMap);
		}
		return mapLists;
	}

	public void retriveData(List<Map<String, String>> readallData)
	{
		for(Map<String, String> map : readallData) {
			map.get("POPPCMD");
	}
	}	
		public static void main(String[] args) throws Exception {
	
		System.out.println();
		}



}
