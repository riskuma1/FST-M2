package githubProject;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class GitHubApiTest {
	
	
	RequestSpecification reqSpec;
	String sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQDwXD/ukoYvKuK2MFWG+7K7O6XCpVEV2uuWblhl1DT/QnBBfZTkSHGJNH26HWO4F6M1rqymUDedg90HSRMsyoiRxeZUTmB4G/5OlCI4YfKAdM6KJdYS12TR5OUgfk/NEiF4xzJnftYCU7icFu/+lACKeIDKIo9ri1Ij3oFmP+/s4Q=="; 
    int sshID;
	
	@BeforeClass
	public void setUp()
	{
		reqSpec = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "token ghp_P35O8k15ovWq76vQtMhFFmC")
				.setBaseUri("https://api.github.com")
				.build();
		        
	}
	

  @Test(priority=1)
  //@Test(enabled=false)
  public void apiPost() {
	  
	  String reqBody = "{\"title\": \"TestAPIKey\",\"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQDwXD/ukoYvKuK2MFWG+7K7O6XCpVEV2uuWblhl1DT/QnBBfZTkSHGJNH26HWO4F6M1rqymUDedg90HSRMsyoiRxeZUTmB4G/5OlCI4YfKAdM6KJdYS12TR5OUgfk/NEiF4xzJnftYCU7icFu/+lACKeIDKIo9ri1Ij3oFmP+/s4Q==\"}";
	  Response response = given().log()
			  .all().spec(reqSpec).body(reqBody).when().post("/user/keys");
	  
	  //Assertion
	  System.out.println(response.asString());
	  sshID = response.then().extract().path("id");
	  System.out.println("sshID is " +  sshID);
	  
	  //Assertion
	  response.then().statusCode(201);
	  
	  
  }
      @Test(priority=2)
      //@Test(enabled=false)
	  public void apiGET() {
		  
	 Response response1 = given().log().all().spec(reqSpec).when().get("/user/keys");
	 System.out.println(response1.asString());
	 
	 //Assertion
	 response1.then().statusCode(200);
	  }
	  
      @Test(priority=3)
      //@Test(enabled=false)
	  public void apiDelete() {
		  
	 Response response2 = given().log().all().spec(reqSpec)
			 .when().pathParam("keyId", sshID)
			 .delete("/user/keys/" + "{keyId}");
			
	 System.out.println(response2.asString());
	 
	 //Assertion
	 response2.then().statusCode(204);
	 
  }
}

