package API_Test_cases;

import static com.jayway.restassured.RestAssured.given;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import Utilities.ExtentManager;
import Utilities.IOExcel;
import Utilities.Log;

public class E4_resource_get_users {
	static int i;
	static int j=0;
	int row =1;int col =0;
	ExtentReports reports;
	ExtentTest test;
	String testCaseName;

  @BeforeClass
  public void setBaseUri () {

	  reports = ExtentManager.GetExtent("API Test Results of http://34.214.158.70:32845/impp/imerit/resource/get/users/0");
	  RestAssured.baseURI="http://34.214.158.70:32845/impp/imerit/resource/get/users/0";
	  Log.startLogForThisCase("E4 Resource/get/users");
	  String Basepath="D:\\testdata\\API\\";
	  IOExcel.excelSetup(Basepath+"E4_resource_getusers_.xlsx");

  }
  
  @BeforeMethod
 	public void init(Method method)
 	{
 		testCaseName =method.getName();
 		Log.startLogForThisCase(testCaseName);
 		if(reports!=null)
 		{
 		test=reports.createTest(testCaseName);
 		//System.out.println("Report created");
 		}
 		else System.out.println("reports obj is null");
 		
 	}

  @Test(dataProvider="DataSource")

  public void postString (String userCode,String engagementDetailCode ,String nodeId ) 
	{
	  test.info("Starting API http://34.214.158.70:32845/impp/imerit/resource/get/users/0");
	//"http://34.214.158.70:32845/impp/imerit/action/access/0"
	 Response res  =
    given().log().all()
   /* .body ("{\"userCode\":\"techteam@imerit.net\","
    +"\"memberCode\":\"animesh@imerit.net\"}"
    )*/
    .body ("{\"userCode\":\""+userCode+"\","
    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\","  	    
    	    +"\"nodeId\":\""+nodeId+"\"}"
    	   )
    .when ()
    .contentType (ContentType.JSON)
    .post ()
    .then()
    .contentType(ContentType.JSON)
    .extract()
	.response();
	 
	
	 
	 test.info("{\"userCode\":\""+userCode+"\","
	    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\","  	    
	    	    +"\"nodeId\":\""+nodeId+"\",}");
	 IOExcel.setExcelStringData(row, col, "{\"userCode\":\""+userCode+"\","
	    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\","  	    
	    	    +"\"nodeId\":\""+nodeId+"\",}", "Sheet2"); 
	 col++;
	 System.out.println ("Status code "+res.statusLine());
	 test.info("Status Code "+res.statusLine());
	 IOExcel.setExcelStringData(row, col, res.statusLine(), "Sheet2");
	 col++;
	/* System.out.println (res.asString());
	 IOExcel.setExcelStringData(row, col, res.asString(), "Sheet2"); 
	 col++;*/
	 test.info("JSON RESPONSE "+res.asString());
	 int time = (int)res.getTimeIn(TimeUnit.MILLISECONDS);
	 test.info("API RESPONSE TIME "+Integer.toString(time)+"ms");
	 IOExcel.setExcelStringData(row,col,Integer.toString(time),"Sheet2");
	 col++;
	 if(res.statusCode()==200)
	 {
		 test.pass("Test Case Passed  ");
	 }
	 else
	 {
		 test.fail("Test failed due to error code "+res.statusCode());
		 Assert.assertEquals(res.statusCode(), 200);
	 }
	 Assert.assertTrue(res.statusCode()==200);
	 int jsoncol=col;
	 
	/* System.out.println("branchCode"+res.body().path("[0].branchCode"));
	 System.out.println("firstName"+res.body().path("[0].firstName"));*/
	 //json detail extraction
	 
	// System.out.println("Json array count "+res.body().path("$.size()"));
	 int jsonarrcount =res.body().path("$.size()");
	 if(jsonarrcount==0)
	 {
		 row++;
	 }
	 else
	 {
		 for(i=0;i<jsonarrcount;i++)
		 {
			
			 
			 String index = Integer.toString(i);
			 
			 String memberCoded=res.body().path("["+index+"].memberCode");
			 IOExcel.setExcelStringData(row, col, memberCoded, "Sheet2");	
			 col++;
			 
			 
			 
			 String data=res.body().path("["+index+"].branchCode").toString();
			 IOExcel.setExcelStringData(row, col, data, "Sheet2"); 
			 col++;
			 
			/* String branchNamed=res.body().path("["+index+"].branchName");
			 IOExcel.setExcelStringData(row, col, branchNamed, "Sheet2");	
			 col++;
			 
			 String data2=res.body().path("["+index+"].firstName");
			 IOExcel.setExcelStringData(row, col, data2, "Sheet2");	
			 col++;
			 
			 String lastNamed=res.body().path("["+index+"].lastName");
			 IOExcel.setExcelStringData(row, col, lastNamed, "Sheet2");	
			 col++;*/
			 
			 Object isAllocated = (res.body().path("["+index+"].isAllocated"));
			 IOExcel.setExcelStringData(row, col, isAllocated.toString(), "Sheet2");	
			 col++;
			 
			 //reset the col back
			 col=jsoncol;
			 row++;
		 }
	 }
	 
	 
	
		 col=0;row++;
		 System.out.println("row "+row);
	
	
  }

 @DataProvider(name="DataSource")
 
  public static Object[][] exceldatasource()
  {
	 int count=IOExcel.Getrowcount("Sheet1");
	 count=1;
	 System.out.println("Fetching "+count+" rows from testdata excel ");
	 int col=3;
	 System.out.println("Fetching "+col+" columns from testdata excel ");
	  Object arr[][]=new Object[count][col];
	 
	  int n=0;int k=0;
	
	  for( i=1;i<=count;i++)
	  {
		  k=0;
		  for( j=0;j<=(col-1);j++)
		  { 
			 arr[n][k]= IOExcel.getExcelStringData(i, j,"Sheet1");
			// System.out.println("i "+i+" j "+j+" arr[n][k]"+arr[n][k]+" n "+n+" k "+k);
			// System.out.println("i "+i+" j "+j);
			 k++;
			//System.out.println(arr[n][k]);
		  }
		  n++;
	  }
	
	  
	  return arr;
	
  }
 
 @AfterClass
 public void teardown()
 {
	  reports.flush();

 }
}
