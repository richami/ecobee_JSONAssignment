package testcases;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;




public class TC_005_TotalResult {
	@Test
	public void readJson() throws IOException, ParseException
	{
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("C:\\Users\\p_wav\\eclipse-workspace\\ecobee_JSONAssignment\\src\\test\\java\\datasource\\test_results.json");
		
		Object obj = jsonParser.parse(reader);
		JSONObject userloginsJsonobj=(JSONObject) obj;
		JSONArray userloginsArray=(JSONArray)userloginsJsonobj.get("test_suites");
		
		
		int count=userloginsArray.size();
		
		for (int i=0;i<count;i++)
		{
			JSONObject users=(JSONObject)userloginsArray.get(i);
			String name =(String) users.get("suite_name");
			System.out.println("Test Suite Name:	"+name);
			
		}
		
		String pass[][];
		String fail[][];
		String blocked[][];
		int p=0,f=0,b=0;
		for(int i=0;i<count;i++)
		{
			JSONObject users=(JSONObject)userloginsArray.get(i);
			JSONArray results=(JSONArray)users.get("results");
			int c=results.size();
			//calling result size
			System.out.println("Size of results object: "+c);
			p=0;
			b=0;
			f=0;
			pass=new String[c][3];
			fail=new String[c][3];
			blocked=new String[c][3];
			int x=0,y=0,z=0;
			for(int j=0;j<c;j++)
			{
				JSONObject r=(JSONObject) results.get(j);
				String tn=(String) r.get("test_name");
				String time=(String) r.get("time");
				String status=(String) r.get("status");
				if(status.equalsIgnoreCase("pass"))
				{
				p++;
				pass[x][0]=tn;
				pass[x][1]=time;
				pass[x][2]=status;
				x++;
				}
				else if(status.equalsIgnoreCase("fail"))
				{
				f++;
				fail[y][0]=tn;
				fail[y][1]=time;
				fail[y][2]=status;
				y++;
				}
				else if(status.equalsIgnoreCase("blocked"))
				{
				b++;
				blocked[z][0]=tn;
				blocked[z][1]=time;
				blocked[z][2]=status;
				z++;
				}
			} //end of results.size loop
			
			System.out.println("For Passed: "+"No of passed:    "+p);
			System.out.println("Test Name    Time    Status");
			for(int k=0;k<p;k++)
			{	
				System.out.print(pass[k][0]);
				System.out.print("\t"+pass[k][1]);
				System.out.println("\t"+pass[k][2]);
				
			}
			System.out.println("For Failed: "+"No of Failed    "+f);
			System.out.println("Test Name    Time    Status");
			for(int k=0;k<f;k++)
			{
				System.out.print(fail[k][0]);
				System.out.print("\t"+fail[k][1]);
				System.out.println("\t"+fail[k][2]);
			}
			System.out.println("For Blocked: "+"No of blocked:    "+b);
			System.out.println("Test Name    Time    Status");
			for(int k=0;k<b;k++)
			{
				System.out.print(blocked[k][0]);
				System.out.print("\t"+blocked[k][1]);
				System.out.println("\t"+blocked[k][2]);
				
			}
	
		

		}	//End of testSuit count loop
		
	}// end of jsonRead method
	
	
	
	
	
	
}//end of class

