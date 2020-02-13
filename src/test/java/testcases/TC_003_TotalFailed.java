package testcases;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class TC_003_TotalFailed {

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
		
		String fail[][];
	
		int f=0;
		for(int i=0;i<count;i++)
		{
			JSONObject users=(JSONObject)userloginsArray.get(i);
			JSONArray results=(JSONArray)users.get("results");
			int c=results.size();
			//calling result size
			System.out.println("Size of results object: "+c);
			f=0;
			fail=new String[c][3];
			int x=0,y=0,z=0;
			for(int j=0;j<c;j++)
			{
				JSONObject r=(JSONObject) results.get(j);
				String tn=(String) r.get("test_name");
				String time=(String) r.get("time");
				String status=(String) r.get("status");
			
				if(status.equalsIgnoreCase("fail"))
				{
				f++;
				fail[y][0]=tn;
				fail[y][1]=time;
				fail[y][2]=status;
				y++;
				}
				
			} //end of results.size loop
			
			System.out.println("For Failed: "+"No of Failed    "+f);
			System.out.println("Test Name    Time    Status");
			for(int k=0;k<f;k++)
			{
				System.out.print(fail[k][0]);
				System.out.print("\t"+fail[k][1]);
				System.out.println("\t"+fail[k][2]);
			}
				

		}	//End of testSuit count loop
		
	}// end of jsonRead method
	
	
	
}
