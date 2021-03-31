package Modules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class configurationTime {

	private String database_connection_string = "jdbc:postgresql://172.19.71.110:5432/Traffview_0.4.5";

	private String database_user_name = "traffview";

	private String database_user_password = "traffview";

	/**
	 * 
	 * By using below code we are connecting to the database and returning the
	 * connection object.
	 * 
	 * @return
	 * 
	 */
	private static List<String> hrminutes;
	private static List<String> dayplan_id;
	private static List<String> listMode;
	private static String getswitch;
	private static int count;
	private static int count_Mode;
	private static String getMode;
	
	public String get_WeekDay() {
		
		String weekDay=LocalDate.now().getDayOfWeek().name();
		System.out.println("Weekday : "+weekDay.toLowerCase() );
		
		return weekDay;
	}
	
	public Connection connect(String controllerCode) {
		hrminutes=new ArrayList<>();
		dayplan_id=new ArrayList<>();
		Connection conn = null;
		System.out.println("test 1");
		String select_dayPlanId="select wp.id from weekday_plan_assignment wa join weekday_plans wp on wa.site_id=wp.site_id\r\n" + 
				"where wp.site_id="+controllerCode+" and \r\n" + 
				"wp.plan_no in (select "+get_WeekDay()+" from weekday_plan_assignment where site_id="+controllerCode+") ";
//		String select_planQuery = "select site_id from weekday_plans where plan_no=18 and site_id=7914";
	//	String select_hourMinQuery = "select * from weekday_plan_switches where dayplan_id=33379";
		System.out.println("test 2");	
//		String select_hourMinQuery = "select CONCAT(switch_hour,':',switch_min)  result from weekday_plan_switches where dayplan_id="+select_dayPlanId+" order by switch_no";
		
		try {
	
			conn = DriverManager.getConnection(database_connection_string, database_user_name, database_user_password);

			System.out.println("You are successfully connected to the PostgreSQL database server.");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(select_dayPlanId);
			while (rs.next()) {

				//	String siteID = rs.getString(1);
					String wekDay=rs.getString(1);
				//	System.out.println("Site ID is " + siteID);
				//	System.out.println("Site ID is " + hrmin);
					dayplan_id.add(wekDay);
					
				}
			
			ResultSet hs = stmt.executeQuery("select CONCAT(switch_hour,':',switch_min)  result from weekday_plan_switches where "
					+ "dayplan_id="+dayplan_id.get(0)+" order by switch_no");
			System.out.println("Day plan Id in connect : "+dayplan_id.get(0));
			while (hs.next()) {

			//	String siteID = rs.getString(1);
				String hrmin=hs.getString(1);
			//	System.out.println("Site ID is " + siteID);
			//	System.out.println("Site ID is " + hrmin);
				hrminutes.add(hrmin);
				
			}
		} catch (SQLException e)

		{

			System.out.println("conn : " + e.getMessage());

		}
		count=hrminutes.size();
		
		return conn;

	}
	public Connection selectMode() {
		listMode=new ArrayList<>();
		Connection connMode = null;
System.out.println("test 1");
		  String str = getswitch; 
	      String[] arrOfStr = str.split(":", 2); 
	  
	        for (String a : arrOfStr) 
	            System.out.println(a); 
	        String hr=arrOfStr[0]; 
	        String min=arrOfStr[1]; 
	        System.out.println("test 2");        
		String mode="select mode from weekday_plan_switches where switch_hour="+hr+" and switch_min="+min+" and dayplan_id="+dayplan_id.get(0)+"";	
		System.out.println("Day plan Id in SelectMode : "+dayplan_id.get(0));
		System.out.println("test 3");
		try {
	
			connMode = DriverManager.getConnection(database_connection_string, database_user_name, database_user_password);

			System.out.println("You are successfully connected to the PostgreSQL database server.");

			Statement stmt = connMode.createStatement();

			ResultSet mde = stmt.executeQuery(mode);
			
			
			while (mde.next()) {

			//	String siteID = rs.getString(1);
				String modes=mde.getString(1);
			//	System.out.println("Site ID is " + siteID);
			//	System.out.println("Site ID is " + hrmin);
				listMode.add(modes);
				
			}
		} catch (SQLException e)

		{

			System.out.println("conn : " + e.getMessage());

		}
		count_Mode=listMode.size();
	System.out.println("size of mode :"+count_Mode);
		return connMode;

	}

	
	public String getSwitchTime(String rtcTime) throws Exception {
		System.out.println("Insde get"+count);
		String time = rtcTime;
		System.out.println("RTC time inside getSwitchtime in Configuration time class : "+rtcTime);
		DateFormat sdf = new SimpleDateFormat("HH:mm");
		Date datet = sdf.parse(time);
		String timea = sdf.format(datet);
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.format(date);
		
	int flag=0;
		for(int i = 0; i < count-1; i++)
		{
		if (dateFormat.parse(hrminutes.get(i)).before(dateFormat.parse(timea))
				&& dateFormat.parse(hrminutes.get(i + 1)).after(dateFormat.parse(timea)) && i < count -1 ) {
			flag=1;
			getswitch=hrminutes.get(i);
		}
		else {
			if(dateFormat.parse(hrminutes.get(0)).after(dateFormat.parse(timea)) && count == 1) {
				flag=1;
				System.out.println("NOT TESTED");
			getswitch=hrminutes.get(0);
			}
		}
		}
			if(flag==1)
			{
			System.out.println("Already fetched");
		}
		else
			{
				if (dateFormat.parse(hrminutes.get(count - 1)).before(dateFormat.parse(timea))) {
					getswitch=hrminutes.get(count - 1);
				}		
			}
		return getswitch;
	}
	
	public String getMode() {
	
		System.out.println("Insde getMode"+count_Mode);
		
		for(int i = 0; i <=count_Mode ; i++ ) {
			getMode=listMode.get(0);
		}
		System.out.println("Mode inside : "+getMode);
		return getMode;
	}
	
	public static void main(String[] args) throws Exception

	{
		
		configurationTime conn = new configurationTime();
		conn.connect("7914");
		
		String switchTime=conn.getSwitchTime("12:06");
		System.out.println("Switch time is "+switchTime);
		conn.selectMode();
		String switchMode=conn.getMode();
		System.out.println("Mode in void main : "+switchMode);

	}

}
