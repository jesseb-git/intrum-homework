package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;


import org.json.*;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GoRestAPI_Steps {
	
	private final String AccessToken = "?access-token=b69231ddbf1d785a5acd5742a3bf63bfe23cfedaa712e149e091c37fd0442349";
	private final String BASE_URL="https://gorest.co.in/public/v2/users";
	private Response response;
	private String[] newUser;
	private int newUserID;
	private String newTestID;
	private String newName = "New Buddy";
	JSONObject requestJSonObj = new JSONObject();


	@When("a user calls the api with {string} http request")
	public void user_calls_with_http_request(String requestType) {
	    RestAssured.baseURI = BASE_URL;
		RequestSpecification httpRequest = RestAssured.given(); 
		response = httpRequest.request(Method.GET, "");
	}

	@Then("the api call returns with a status code {int}")
	public void the_api_call_returns_with_a_status_code(int statusReceived) {
	    System.out.println("Here is the status code: " + statusReceived);
	    assertEquals(statusReceived, response.getStatusCode());
	}

	@Then("the result contains users")
	public void result_contains_users() {
	    String sub = response.asString();
	    assertTrue(sub.contains("name"));

	}
	
	
	@Given("new user data from the CSV file {string} and row number {int}")
	public void new_user_data_from_the_csv_file_and_csv_row(String fileName, int rowNumber) throws IOException {
		CSVParser csvParser = new CSVParserBuilder()
		        .withSeparator(',')
		        .withIgnoreQuotations(true)
		        .build();
		
		CSVReader csvReader = new CSVReaderBuilder(new FileReader("C:\\Intrum\\intrum-homework\\IntrumHomework\\src\\main\\resources\\CleanUserData.csv"))
		        .withSkipLines(1)
		        .withCSVParser(csvParser)
		        .build();
		
		String[] nextLine;
		int x = 1;
	    while ((nextLine = csvReader.readNext()) != null) {
	      if (nextLine != null && x == rowNumber) {
	        System.out.println(Arrays.toString(nextLine));
	        newUser = nextLine;
	      }
	      x++;
	    }
	}

	@Then("add a new user with that data")
	public void add_a_new_user_with_that_data() {
		requestJSonObj.put("id", newUser[0]);
		requestJSonObj.put("name", newUser[1]);
		requestJSonObj.put("email",newUser[2]);
		requestJSonObj.put("gender",newUser[3]);
		requestJSonObj.put("status", newUser[4]);

		System.out.println(requestJSonObj);

		
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON);
		String jsonStringFromRequest = requestJSonObj.toString();

		response = request.body(jsonStringFromRequest)
				.post(BASE_URL+AccessToken);

	}
	@When("the new user is created")
	public void the_new_user_is_created() {
		String idFromJSON= response.asString();
		newUserID = Integer.parseInt(idFromJSON.substring(6,13));
		newTestID = idFromJSON.substring(6,13);
		System.out.println("The ID is " + newUserID);
	    assertEquals(201, response.getStatusCode());
	}

	@Then("this user should be returned by the API")
	public void this_user_should_be_returned_by_the_API() {
		RequestSpecification httpRequest = RestAssured.given(); 
		response = httpRequest.get(BASE_URL + "/" + newTestID + AccessToken);
		String bodyAsString = response.asString();
		assertEquals("Response body does not contain new user", bodyAsString.contains(newUser[2]), true);
	}
	@When("a change is made to this new user")
	public void a_change_is_made_to_this_new_user() {
		requestJSonObj.put("name", newName);	
		RestAssured.baseURI = BASE_URL + "/" + newUserID + AccessToken;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON);
		response = request.body(requestJSonObj.toString())
				.put(BASE_URL + "/" + newUserID + AccessToken);
		assertEquals(200, response.getStatusCode());
	}
	@Then("the updated user details should be returned by the API")
	public void the_updated_user_details_should_be_returned_by_the_api() {
		RequestSpecification httpRequest = RestAssured.given(); 
		response = httpRequest.get(BASE_URL + "/" + newTestID + AccessToken);
		String bodyAsString = response.asString();
		assertEquals("Response body does not contain new user", bodyAsString.contains(newName), true);
	}
	@When("the new user is deleted")
	public void the_new_user_is_deleted() {
		RestAssured.baseURI = BASE_URL + "/" + newUserID + AccessToken;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON);
		response = request.delete(BASE_URL + "/" + newUserID + AccessToken);
		assertEquals(response.getStatusCode(),204);

	}
	@Then("the user should no longer exist")
	public void the_user_should_no_longer_exist() {
		RequestSpecification httpRequest = RestAssured.given(); 
		response = httpRequest.get(BASE_URL + "/" + newTestID + AccessToken);
		assertEquals(response.getStatusCode(), 404);
	}


}
