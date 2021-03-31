package Utilites;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.asserts.SoftAssert;



import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;


public class fileExistAfterDownload {
	static WebDriver driver;
	WebDriverWait wait;
	
	private static String downloadPath = System.getProperty("user.dir")+"\\filedownload";
	public static SoftAssert softassertion = new SoftAssert();
	
	public fileExistAfterDownload(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}
	
	public void deleteExistingFile() throws Exception{
		
	    File dir = new File(downloadPath);
	    File[] files = dir.listFiles();
	    System.out.println("dir path : "+downloadPath);
	    System.out.println("length : "+files.length);
	    if (files == null || files.length == 0) {
	       
	        System.out.println("NO files : "+files);
	    }
	    else {
	    	for (int i = 0; i < files.length; i++) {
		    	System.out.println("File Name : "+files[i].getName());
		    	String filename=files[i].getName();
		    	System.out.println("String File Name : "+filename);
		    	System.out.println("Start Deleting");
		    	try {
		    	FileUtils.cleanDirectory(dir);
		    	}catch(IOException e) {
		    		System.out.println("IO Exception");
		    		Thread.sleep(15000);
		    		FileUtils.cleanDirectory(dir);
		    	}
		    }
	    }
	   
	    }
	   
	
	private boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    System.out.println("dir path : "+dirPath);
	    System.out.println("ext : "+ext);
	    System.out.println("length : "+files.length);
	    if (files == null || files.length == 0) {
	        flag = false;
	        System.out.println("files : "+files);
	    }
	    
	    for (int i = 0; i < files.length; i++) {
	    	System.out.println("Flag true file : "+files[i].getName());
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	}
	    }
	    return flag;
	}
	
	public void fileExist(String format) {
		
		if(isFileDownloaded_Ext(downloadPath, ""+format+"")==false) {
			System.out.println("File not downloaded");
			softassertion.assertTrue(false);
		}
		else {
			System.out.println("File successfully downloaded");
			softassertion.assertTrue(true);
		}
//		softassertion.assertAll();
//		System.out.println(isFileDownloaded_Ext(downloadPath, ".pdf"));
//		Assert.assertTrue(isFileDownloaded_Ext(downloadPath, ".pdf"), "Failed to download document which has extension .pdf");
		
		
	}
	
//public static void main(String args[])throws Exception {
//		
//		fileExistAfterDownload f=new fileExistAfterDownload();
//		f.deleteExistingFile();
//}
}
