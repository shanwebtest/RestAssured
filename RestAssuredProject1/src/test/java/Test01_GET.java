import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test01_GET {

	@Test
	void test_GET() {


		Response response = get("https://reqres.in/api/users?page=2");

		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());

		System.out.println(response.getBody().asString());
		System.out.println(response.body());

		int statusCode = response.getStatusCode();		
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void test02() {
		baseURI = "https://reqres.in/api";

		//assert status code in a single line 
		//given().get("/users?page=2").then().statusCode(200);

		//or bellow code also will work
		given().
		get("/users?page=2").
		then().
		statusCode(200);


	}


	@Test
	public void test03_bodyofResponse() {
		
		//validate the 2nd idex number in json repsonse
		System.out.println("Json body reponse content validation");
		baseURI ="https://reqres.in/api";
		given().
		   get("/users?page=2").
		//then().
		//body("data[1].id", equalTo(8)).log().all();
		then().
		body("data[2].email",equalToCompressingWhiteSpace("tobias.funke@reqres.in"));
		
		

	}


}
