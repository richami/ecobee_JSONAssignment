package testcases;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class TC_004_TotalBlocked {
	@Test
	public void readJson() throws IOException, ParseException
	{
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("C:\\Users\\p_wav\\eclipse-workspace\\ecobee_JSONAssignment\\src\\test\\java\\datasource\\test_results.json");

		Object obj = jsonParser.parse(reader);
		JSONObject userloginsJsonobj=(JSONObject) obj;
		JSONArray userloginsArray=(JSONArray)userloginsJsonobj.get("test_suites");


		int count=userloginsArray.size();
		//printing Test suite name
		for (int i=0;i<count;i++)
		{
		JSONObject users=(JSONObject)userloginsArray.get(i);
		String name =(String) users.get("suite_name");
		System.out.println("Test Suite Name: "+name);
		System.out.println("---------------------------------------");

		}

		String blocked[][];
	
		int b=0;
		for(int i=0;i<count;i++)
		{
		JSONObject users=(JSONObject)userloginsArray.get(i);
		JSONArray results=(JSONArray)users.get("results");
		int c=results.size();
		//calling result size

		System.out.println("Size of results object: "+c);
		System.out.println("-----------------------------------------");
		b=0;
		int x=0,y=0,z=0;
		blocked=new String[c][3];
		for(int j=0;j<c;j++)
		{
		
		JSONObject r=(JSONObject) results.get(j);
		String tn=(String) r.get("test_name");
		String time=(String) r.get("time");
		String status=(String) r.get("status");
		if(status.equalsIgnoreCase("blocked"))
		{

		blocked[x][0]=tn;
		blocked[x][1]=time;
		blocked[x][2]=status;
		x++;
		b++;
		}
		} //end of results.size loop

		System.out.println("For Blocked: "+"No of blocked: "+b);
		System.out.println("-----------------------------------------");

		System.out.println("Test Name Time Status");
		System.out.println("-----------------------------------------");

		for(int k=0;k<b;k++)
		{
		System.out.print(blocked[k][0]);
		System.out.print("\t"+blocked[k][1]);
		System.out.println("\t"+blocked[k][2]);

		}

		} //End of testSuit count loop

		}// end of jsonRead method


}
