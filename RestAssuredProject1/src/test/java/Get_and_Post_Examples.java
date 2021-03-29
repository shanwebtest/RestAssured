import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class Get_and_Post_Examples {

	//@Test
	public void GET() {
		baseURI = "https://reqres.in/api";

		given().
		get("/users?page=2").
		then().
		statusCode(200).
		body("data[1].first_name", equalTo("Lindsay")).
		body("data.first_name",hasItems("George","Rachel"));


	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPOST() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "morpheus");
		map.put("Job", "Teacher");		
	//	System.out.println(map);
		
		
       // create json object to post the request
		JSONObject request = new JSONObject();
		request.put("name", "morpheus");
		request.put("Job", "Teacher");
		
		System.out.println(request.toJSONString());
		//System.out.println(request);
		
		baseURI = "https://reqres.in/api";
		
		given().
		  header("Content-Type","application/json;charset=utf-8").
		  contentType(ContentType.JSON).
		  accept(ContentType.JSON).
		  body(request.toJSONString()).
	
		when().
		   post("/users").		 
		then().
		   statusCode(201)
		  .log().all();

	}

}
