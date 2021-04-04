package test_Local;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.ExcelData;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import io.restassured.response.Response;

public class parameterization  {
  




@Test(dataProvider= "testdata")
  public void GET(String url) {
	  
	  Response response = get(url);
	  System.out.println(response.getStatusCode());
	  int statuscode = response.getStatusCode();
	  Assert.assertEquals(statuscode,200); 
	  	 
	 // System.out.println(response.getBody().asString());
  }
  
  
  
  @DataProvider(name="testdata")
  public static  Object[][]  getdata() throws IOException {
	  
	  String filepath = ExcelData.filepath();

	  
	  Object data[][] = ExcelData.dataObject(filepath, "Sheet1");
	  
	  return data;
  }
}
