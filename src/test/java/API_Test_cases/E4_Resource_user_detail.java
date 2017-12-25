package API_Test_cases;

/*
 * /impp/imerit/resource/user/details/0
This api is used to get details of a resource

*/

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import Utilities.ExtentManager;
import Utilities.IOExcel;
import Utilities.Log;

public class E4_Resource_user_detail {
	static int i;
	static int j=0;
	int row =1;int col =0;
	ExtentReports reports;
	ExtentTest test;
	String testCaseName;

  @BeforeClass
  public void setBaseUri () {

	  reports = ExtentManager.GetExtent("API Test Results of http://34.214.158.70:32845/impp/imerit/resource/user/details/0");
	  RestAssured.baseURI="http://34.214.158.70:32845/impp/imerit/resource/user/details/0";
	  Log.startLogForThisCase("Resource resource/user/details");
	  IOExcel.excelSetup("E:\\testdata\\API\\API_resource_user_details.xlsx");

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

  public void postString (String userCode,String memberCode ) 
	{
	  test.info("Starting API http://34.214.158.70:32845/impp/imerit/resource/user/details/0");
	//"http://34.214.158.70:32845/impp/imerit/action/access/0"
	 Response res  =
    given()//.log().all()
   /* .body ("{\"userCode\":\"techteam@imerit.net\","
    +"\"memberCode\":\"animesh@imerit.net\"}"
    )*/
    .body ("{\"userCode\":\""+userCode+"\","
    	    +"\"memberCode\":\""+memberCode+"\"}"
    	   )
    .when ()
    .contentType (ContentType.JSON)
    .post ()
    .then()
    .contentType(ContentType.JSON)
    .extract()
	.response();
	 
	 test.info("JSON REQUEST {\"userCode\":\""+userCode+"\","
	    	    +"\"memberCode\":\""+memberCode+"\"}");
	 IOExcel.setExcelStringData(row, col,"{\"userCode\":\""+userCode+"\","
	    	    +"\"memberCode\":\""+memberCode+"\"}", "Sheet2"); 
	 col++;
	 System.out.println ("Status code "+res.statusLine()); //STATUS CODE
	 test.info("Status Code "+res.statusLine());
	 IOExcel.setExcelStringData(row, col, res.statusLine(), "Sheet2");
	 col++;
//	 System.out.println (res.asString()); //JSON RESPONSE
	 test.info("JSON RESPONSE "+res.asString());
	 IOExcel.setExcelStringData(row, col, res.asString(), "Sheet2"); 
	 col++;
	 int time = (int)res.getTimeIn(TimeUnit.MILLISECONDS); //RESPONSE TIME
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
	 
	 //System.out.println("memberCode"+res.body().path("userDetails.memberCode"));
	 String memberCoded=res.body().path("userDetails.memberCode").toString();	 
	 IOExcel.setExcelStringData(row, col,memberCode , "Sheet2");
	 col++;
	 
	 //System.out.println("firstName"+res.body().path("userDetails.firstName"));
	 String firstNamed=res.body().path("userDetails.firstName").toString();	
	 IOExcel.setExcelStringData(row, col,firstNamed , "Sheet2");
	 col++;
	 
	 
	 //System.out.println("lastName"+res.body().path("userDetails.lastName"));
	 String lastNamed=res.body().path("userDetails.lastName").toString();	
	 IOExcel.setExcelStringData(row, col,lastNamed , "Sheet2");
	 col++;
	 
//	 System.out.println("departmentName"+res.body().path("userDetails.departmentName"));
	 String departmentNamed=res.body().path("userDetails.departmentName").toString();	
	 IOExcel.setExcelStringData(row, col,departmentNamed , "Sheet2");
	 col++;
	 
	// System.out.println("branchName"+res.body().path("userDetails.branchName"));
	 String branchNamed=res.body().path("userDetails.branchName").toString();	
	 IOExcel.setExcelStringData(row, col,branchNamed , "Sheet2");
	 col++;
	 
	// System.out.println("branchCode"+res.body().path("userDetails.branchCode"));
	 String branchCoded=res.body().path("userDetails.branchCode").toString();	
	 IOExcel.setExcelStringData(row, col,branchCoded , "Sheet2");
	 col++;
	 
	 int jsoncol=col;
	 //json detail extraction
	 
	// System.out.println("Json array count "+res.body().path("assignedEngagement.size()"));
	/* System.out.println("engagementDetailCode "+res.body().path("assignedEngagement[0].engagementDetailCode"));
	 System.out.println("projectCode "+res.body().path("assignedEngagement[0].projectCode"));
	 System.out.println("projectTitle "+res.body().path("assignedEngagement[0].projectTitle"));
	 System.out.println("engagementTitle "+res.body().path("assignedEngagement[0].engagementTitle"));
	 System.out.println("name "+res.body().path("assignedEngagement[0].name"));
	 System.out.println("noOfRunningJob "+res.body().path("assignedEngagement[0].noOfRunningJob")); */
	// System.out.println("noOfRunningJob "+res.body().path("assignedEngagement[0].allocation"));
	 int jsonarrcount =res.body().path("assignedEngagement.size()");
	 if(jsonarrcount==0)
	 {
		 row++;
	 }
	 else
	 {
		 for(i=0;i<jsonarrcount;i++)
		 	{
					 
			 String index = Integer.toString(i);
			 
			 String engagementDetailCoded=res.body().path("assignedEngagement["+index+"].engagementDetailCode").toString();
			 IOExcel.setExcelStringData(row, col, engagementDetailCoded, "Sheet2"); 
			 col++;
			 
			 String projectCoded=res.body().path("assignedEngagement["+index+"].projectCode");
			 IOExcel.setExcelStringData(row, col, projectCoded, "Sheet2");	
			 col++;
			 
			 String projectTitled=res.body().path("assignedEngagement["+index+"].projectTitle");
			 IOExcel.setExcelStringData(row, col, projectTitled, "Sheet2");	
			 col++;
			 
			 String engagementTitled=res.body().path("assignedEngagement["+index+"].engagementTitle");
			 IOExcel.setExcelStringData(row, col, engagementTitled, "Sheet2");	
			 col++;
			 
			 String named=res.body().path("assignedEngagement["+index+"].name");
			 IOExcel.setExcelStringData(row, col, named, "Sheet2");	
			 col++;
			
			 int allocationcount =res.body().path("assignedEngagement["+index+"].allocation.size()");
			// System.out.println("inner array count "+allocationcount);
			 int inncol=col;
			 if(allocationcount>0)
			 {
				 for(int m=0;m<allocationcount;m++)
				 {
					 
				 String index2 = Integer.toString(m);	 
				// System.out.println("nodeName "+res.body().path("assignedEngagement["+index+"].allocation["+index2+"].nodeName"));
				// System.out.println("nodeId "+res.body().path("assignedEngagement["+index+"].allocation["+index2+"].nodeId"));
				String allocationd=res.body().path("assignedEngagement["+index+"].allocation["+index2+"].nodeName").toString();
				 IOExcel.setExcelStringData(row, col, allocationd, "Sheet2");	
				 col++;
				
				String nodeIdD=res.body().path("assignedEngagement["+index+"].allocation["+index2+"].nodeId").toString();
				 IOExcel.setExcelStringData(row, col, nodeIdD, "Sheet2");	
				 col++;
				 col=inncol;
				 row++;
				 }
			 }
			 
			/* String noOfRunningJobd=res.body().path("assignedEngagement["+index+"].noOfRunningJob");
			 IOExcel.setExcelStringData(row, col, noOfRunningJobd, "Sheet2");	
			 col++;
			 		 */
			 //reset the col back
			 col=jsoncol;
			// row++;
		 	}
	 }
	 
	
		 col=0;
		
		// System.out.println("row "+row);
	
  }

 @DataProvider(name="DataSource")
 
  public static Object[][] exceldatasource()
  {
	 int count=IOExcel.Getrowcount("Sheet1");
	 System.out.println("row count"+count);
	 
	  Object arr[][]=new Object[count][2];
	 
	  int n=0;int k=0;
	
	  for( i=1;i<=count;i++)
	  {
		  k=0;
		  for( j=0;j<=1;j++)
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
