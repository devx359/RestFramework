package API_Test_cases;

import static com.jayway.restassured.RestAssured.given;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

import Utilities.IOExcel;
import Utilities.Log;
import Utilities.ExtentManager;

public class E4_resource_allocate 
{
	
	static int i;
	static int j=0;
	int row =1;int col =0;
	Connection con;
	Statement stmt;
	ResultSet rs;
	int r=1; int c=0;
	ExtentReports reports;
	ExtentTest test;
	String testCaseName;

  @BeforeClass
  public void setBaseUri () {

	  reports = ExtentManager.GetExtent("API Test Results of http://34.214.158.70:32845/impp/imerit/resource/allocate/0 ");
	  
		
	 RestAssured.baseURI="http://34.214.158.70:32845/impp/imerit/resource/allocate/new/0"; //ITEST
	  
	  Log.startLogForThisCase("API Testing Resource /resource/allocate/");
	  String Basepath="E:\\testdata\\API\\";
	  IOExcel.excelSetup(Basepath+"E4_resoure_allocate.xlsx");
	  
	  
	
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

  public void postString (String userCode,String allocateMembers,String unAllocateMembers,String nodeId,String engagementDetailCode ) 
	{
	  
	  test.info("Starting API 34.214.158.70:32845/impp/imerit/resource/allocate/0");
	 
	Response res  =
    given()//.log().all()
    .body ("{\"userCode\":\"techteam@imerit.net\","
    +"\"memberCode\":\"animesh@imerit.net\"}"
    )
    .body ("{\"userCode\":\""+userCode+"\","
    	    +"\"allocateMembers\":[\""+allocateMembers+"\"],"
    	    +"\"unAllocateMembers\":[\""+unAllocateMembers+"\"],"
    	    +"\"nodeId\":\""+nodeId+"\","
    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\"}"
    	   )
    .when ()
    .contentType (ContentType.JSON)
    .post ()
    .then()
    .contentType(ContentType.JSON)
    .extract()
	.response();
	 
	
	 test.info("JSON REQUEST : {\"userCode\":\""+userCode+"\","
	    	    +"\"allocateMembers\":[\""+allocateMembers+"\"],"
	    	    +"\"unAllocateMembers\":[\""+unAllocateMembers+"\"],"
	    	    +"\"nodeId\":\""+nodeId+"\","
	    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\"}");
	 System.out.println("{\"userCode\":\""+userCode+"\","
	    	    +"\"allocateMembers\":[\""+allocateMembers+"\"],"
	    	    +"\"unAllocateMembers\":[\""+unAllocateMembers+"\"],"
	    	    +"\"nodeId\":\""+nodeId+"\","
	    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\"}");
	 
	
	IOExcel.setExcelStringData(row, col, "{\"userCode\":\""+userCode+"\","
	    	    +"\"allocateMembers\":\""+allocateMembers+"\","
	    	    +"\"unAllocateMembers\":\""+unAllocateMembers+"\","
	    	    +"\"nodeId\":\""+nodeId+"\","
	    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\"}", "Sheet2"); 
	 col++;
	 System.out.println ("Status code "+res.statusLine());
	 IOExcel.setExcelStringData(row, col, res.statusLine(), "Sheet2");
	 col++;
	 System.out.println (res.asString());
	 IOExcel.setExcelStringData(row, col, res.asString(), "Sheet2"); 
	 test.info("JSON RESPONSE "+res.asString());
	 col++;
	 int time = (int)res.getTimeIn(TimeUnit.MILLISECONDS);
	 test.info("API Response time : "+Integer.toString(time)+" ms");
	 IOExcel.setExcelStringData(row,col,Integer.toString(time),"Sheet2");
	 col++;
	 
	 
	 col=0;
	 row++;
	 
	 
	 if(res.statusCode()==200)
	 {
		 test.pass("Test Case Passed  ");
	 }
	 else
	 {
		 test.fail("Test failed due to error code "+res.statusCode());
	 }
	 Assert.assertTrue(res.statusCode()==200);
  
	
	// int jsoncol=col;
	 
	// System.out.println("nodeId"+res.body().path("[0].nodeId"));
	// System.out.println("nodeName"+res.body().path("[0].nodeName"));
	 //json detail extraction
	 
	// System.out.println("Json array count "+res.body().path("$.size()"));
	/* int jsonarrcount =res.body().path("$.size()");
	 if(jsonarrcount==0)
	 {
		 row++;
	 }
	 else
	 {
		 for(i=0;i<jsonarrcount;i++)
		 {
					 
			 String index = Integer.toString(i);
			 String data=res.body().path("["+index+"].nodeId").toString();
			 IOExcel.setExcelStringData(row, col, data, "Sheet2"); 
			 col++;
			 
			 String data2=res.body().path("["+index+"].nodeName");
			 IOExcel.setExcelStringData(row, col, data2, "Sheet2");	
			 col++;
			 
			 try {
				IOExcel.setExcelStringData(row, col, rs.getString(1).toString(), "Sheet2"); 
				col++;
				IOExcel.setExcelStringData(row, col, rs.getString(2).toString(), "Sheet2"); 
				col++;
			} catch (SQLException e) {
				System.out.println(e);
			}
			 
			 //reset the col back
			 col=jsoncol;
			 row++;
		 }
	 }
	 
	 
	
		 col=0;
		 System.out.println("row "+row);
	*/
	 
	}

 @DataProvider(name="DataSource")
 
  public static Object[][] exceldatasource()
  {
	 int count=IOExcel.Getrowcount("Sheet1");
	 System.out.println("row count in Excel data sheet "+count);
	 
	  Object arr[][]=new Object[count][5];
	 
	  int n=0;int k=0;
	
	  for( i=1;i<=count;i++)
	  {
		  k=0;
		  for( j=0;j<=4;j++)
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
	 try {
		con.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}  
 }


}
