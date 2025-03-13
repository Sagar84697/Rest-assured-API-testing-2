package March13;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test; 

public class authentication {
	
	@Test(priority=1)
	void digest() {
		given()
		.auth().digest("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
	}
	
	@Test(priority=2)
	void basic() {
		given()
		.auth().basic("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();		
	}
	

	@Test(priority=3)
	void peeram() {
		given()
		.auth().preemptive().basic("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();		
	}
	
	@Test(priority=4)
	void tokens() {
		
		String bearerToken="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";
		
		given()
		.headers("Authorization","Bearer "+bearerToken)
		
		.when()
		.get("https://api.github.com/user/repos")
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
	
	@Test(priority=5)
	void oauth2() {
		given()
		.auth().oauth2("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")
		
		.when()
		.get("https://api.github.com/user/repos")
		
		.then()
		.statusCode(200)
		.log().all();
	}
	
	
}
