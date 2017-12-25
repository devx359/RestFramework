package API_Test_cases;

/*
 * 
 * 
 * http://34.214.158.70:32845/impp/imerit/resource/nodes/get/0
 * 
 * List all the nodes under a particular engagement
 * 
 */

import static com.jayway.restassured.RestAssured.given;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import Utilities.ExtentManager;
import Utilities.IOExcel;
import Utilities.Log;

public class E4_resource_nodes_get {
	
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

	  reports = ExtentManager.GetExtent("API Test Results of http://34.214.158.70:32845/impp/imerit/resource/nodes/get/0");
	  RestAssured.baseURI="http://34.214.158.70:32845/impp/imerit/resource/nodes/get/0";
	  Log.startLogForThisCase("API Testing Resource resource/user/details");
	  String Basepath="D:\\testdata\\API\\";
	  IOExcel.excelSetup(Basepath+"E4_resource_nodes_get.xlsx");
	  
	 /* try{  
		  Class.forName("com.mysql.cj.jdbc.Driver");  
		  con=DriverManager.getConnection("jdbc:mysql://testimppinstance.cy4e3hacstmv.us-west-2.rds.amazonaws.com:3306/impp_demo_v2_09122017","imppdb","rds@ws#2017");  		 
		  System.out.println("Database connected");	  		 		 
		  }
	  	catch(Exception e)
	  	{
	  		System.out.println(e); 
		}  
*/
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

  public void postString (String userCode,String engagementDetailCode ) 
	{
	 //DB****************************************************
	  
	 /* String query="SELECT distinct\r\n" + 
	  		"a.node_cid,\r\n" + 
	  		"JSON_EXTRACT(efj.flow_data, \"$.flow.flowBuilder\") cid\r\n" + 
	  		"\r\n" + 
	  		"FROM impp_demo_v2_09122017.impp_engagement_allocation a ,impp_demo_v2_09122017.impp_employee b,impp_demo_v2_09122017.impp_member c,\r\n" + 
	  		"impp_demo_v2_09122017.impp_member_branch_tr d,impp_demo_v2_09122017.impp_branch e,\r\n" + 
	  		"impp_demo_v2_09122017.impp_member_dept_desig_tr f,impp_demo_v2_09122017.impp_department g\r\n" + 
	  		",impp_demo_v2_09122017.impp_project_engagement_flow h,impp_demo_v2_09122017.impp_project_engagement_detail i,\r\n" + 
	  		"impp_demo_v2_09122017.impp_project pr ,impp_demo_v2_09122017.impp_project_engagement pe ,impp_project_engagement_flow_json efj\r\n" + 
	  		"\r\n" + 
	  		"WHERE \r\n" + 
	  		"b.member_id=c.id and c.id = a.member_id and b.member_id=d.member_id and e.id =d.branch_id and a.member_id=f.member_id\r\n" + 
	  		"and g.id=f.department_id and h.id=a.engagement_flow_id and i.id=h.engagement_detail_id  \r\n" + 
	  		"and pr.id=i.project_id  and pe.id=i.engagement_type_id and efj.engagement_flow_id=a.engagement_flow_id and\r\n" + 
	  		"i.engagement_detail_code='"+engagementDetailCode+"' \r\n" + 
	  		"\r\n" + 
	  		"order by a.node_cid\r\n"  
	  		;
	  
	 // System.out.println(query);
	  try {
		  
		 stmt=con.createStatement();  
		 rs=stmt.executeQuery(query);  
		  System.out.println("Query Executed");
		  while(rs.next())  
		  {
			  r++;
			//  System.out.println(rs.getString(1)+"  "+rs.getString(2));
			  IOExcel.setExcelStringData(r, c, rs.getString(1).toString(), "Sheet3");
			  c++;
			  IOExcel.setExcelStringData(r, c, rs.getString(2).toString(), "Sheet3");
			  c++;
			  if(c>1)
			  {
				  c=0;
				  r++;
			  }
			  
		  }
	} catch (Exception e) {
		
		System.out.println(e);
	}  
	  */
	  
	 //****************************************************** 
	  test.info("Starting API http://34.214.158.70:32845/impp/imerit/resource/nodes/get/0");
	//"http://34.214.158.70:32845/impp/imerit/action/access/0"
	 Response res  =
    given()//.log().body()
   /* .body ("{\"userCode\":\"techteam@imerit.net\","
    +"\"memberCode\":\"animesh@imerit.net\"}"
    )*/
    .body ("{\"userCode\":\""+userCode+"\","
    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\"}"
    	   )
    .when ()
    .contentType (ContentType.JSON)
    .post ()
    .then()
    .contentType(ContentType.JSON)
    .extract()
	.response();
	 
	
	 
	 test.info("JSON REQUEST: "+"{\"userCode\":\""+userCode+"\","
	    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\"}");
	 IOExcel.setExcelStringData(row, col, "{\"userCode\":\""+userCode+"\","
	    	    +"\"engagementDetailCode\":\""+engagementDetailCode+"\"}", "Sheet2"); 
	 col++;
	 System.out.println ("Status code "+res.statusLine());
	 test.info("Status Code "+res.statusLine());
	 IOExcel.setExcelStringData(row, col, res.statusLine(), "Sheet2");
	 col++;
	// System.out.println (res.asString());
	 IOExcel.setExcelStringData(row, col, res.asString(), "Sheet2"); 
	 test.info("JSON RESPONSE "+res.asString());
	 col++;
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
	 
	// System.out.println("nodeId"+res.body().path("[0].nodeId"));
	// System.out.println("nodeName"+res.body().path("[0].nodeName"));
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
			 String data=res.body().path("["+index+"].nodeId").toString();
			 IOExcel.setExcelStringData(row, col, data, "Sheet2"); 
			 col++;
			 
			 String data2=res.body().path("["+index+"].nodeName");
			 IOExcel.setExcelStringData(row, col, data2, "Sheet2");	
			 col++;
			 
			/* try {
				IOExcel.setExcelStringData(row, col, rs.getString(1).toString(), "Sheet2"); 
				col++;
				IOExcel.setExcelStringData(row, col, rs.getString(2).toString(), "Sheet2"); 
				col++;
			} catch (SQLException e) {
				System.out.println(e);
			}*/
			 
			 //reset the col back
			 col=jsoncol;
			 row++;
		 }
	 }
	 
	 
	
		 col=0;
		 System.out.println("row "+row);
		 
		 
	
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
	/* try {
		con.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}  */
 }

}
