import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.json.simple.JSONObject;




public class PutPatchDeleteExamples {
  @SuppressWarnings("unchecked")
  @Test
  public void put_Test() {
	  
	 JSONObject request = new JSONObject();
	 request.put("name", "morpheus");
	 request.put("Job", "Teacher");
		
	  System.out.println(request.toJSONString());
	  
	  baseURI = "https://reqres.in/api";
	  
	  given().
	    header("Content-Type","application/json").
	       contentType(ContentType.JSON).
	       accept(ContentType.JSON).
	       body(request.toJSONString()).
	   when().
	       put("/users/2").     // end node url
	   then().
	       statusCode(200)
	       .log().all();
  }
  
  
  @SuppressWarnings("unchecked")
@Test
  public void patch_Test() {
	  
	  JSONObject request = new JSONObject();
		 request.put("name", "morpheus");
		 request.put("Job", "Teacher");
			
		  System.out.println(request.toJSONString());
		  
		  baseURI = "https://reqres.in/api";
		  
		  given().
		    header("Content-Type","application/json").
		       contentType(ContentType.JSON).
		       accept(ContentType.JSON).
		       body(request.toJSONString()).
		   when().
		       patch("/users/2").     // end node url
		   then().
		       statusCode(200)
		       .log().all();
	  
  }
  
  
  @Test
  public void delete_Test() {
	  
	  baseURI = "https://reqres.in/api";
	  
	  when().
	     delete("/users/2").
	  then().and().
	      statusCode(204)
	      .log().all();
	  
  }
  
  
}
