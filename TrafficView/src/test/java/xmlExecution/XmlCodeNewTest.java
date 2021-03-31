package xmlExecution;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import Utilites.Listeners;
import test.listeners.ListenerAssert;
import test.listeners.ListenerInXmlTest;

import org.testng.xml.XmlSuite.ParallelMode;

public class XmlCodeNewTest {

	public ArrayList<String> readexceldata(int colno) throws IOException {
		String path=System.getProperty("user.dir")+"\\excel\\te.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("TestCase");
		
		Iterator<Row> rowIterators=sheet.iterator();
		rowIterators.next();
		
		ArrayList<String> list=new ArrayList<String>();
		while(rowIterators.hasNext()) {
			list.add(rowIterators.next().getCell(colno).getStringCellValue());
		}
		System.out.println("List :::"+list);
		return list;
	}
	public void testNgXmlSuite() throws Exception {
		System.out.println("est main");
		ArrayList<String> testClass=readexceldata(0);
	//System.out.println("Test Case count : "+testClass.size());
		for(int i = 0; i < testClass.size(); i++ ) {
//ITestContext context;
			String clas=testClass.get(i);
			System.out.println("string class "+clas);
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        List<XmlClass> classes = new ArrayList<XmlClass>();
        @SuppressWarnings("rawtypes")
 //       List<Class<? extends ITestNGListener>> listenerList=
 //       		new ArrayList<Class<? extends ITestNGListener>>();
        
        List<Class> listenerList=new ArrayList<Class>();
               
        XmlSuite suite = new XmlSuite();
        suite.setName("Suite");

        
        XmlTest test = new XmlTest(suite);
        test.setName("Test 1");
        
        
       
        		
        XmlClass clss1 = new XmlClass(""+clas+"");
        classes.add(clss1);
        
        System.out.println("xml class test1");

        
        
//        XmlTest test2 = new XmlTest(suite);
//        test2.setName("Test 2");
//        test2.setThreadCount(2);
//        XmlClass clss2 = new XmlClass("com.MainPackage.Main");
//        classes.add(clss2);
//        System.out.println("xml class test2");
        listenerList.add(Listeners.class);
        test.setXmlClasses(classes);
//        test2.setXmlClasses(classes);
 
        suites.add(suite);
 
        TestNG tng = new TestNG();
 
        tng.setXmlSuites(suites);
        tng.setListenerClasses(listenerList);
        tng.run();
		}
}
	
public static void main(String args[])throws Exception {
		
		XmlCodeNewTest xmltest=new XmlCodeNewTest();
		xmltest.testNgXmlSuite();
		System.out.println("void main");
}
}
