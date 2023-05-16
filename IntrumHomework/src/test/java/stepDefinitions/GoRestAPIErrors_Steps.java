package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.json.*;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GoRestAPIErrors_Steps {

	private final String AccessToken = "?access-token=b69231ddbf1d785a5acd5742a3bf63bfe23cfedaa712e149e091c37fd0442349";
	private final String BASE_URL="https://gorest.co.in/public/v2/users";
	private Response response;
	private String[] newUser;
	JSONObject requestJSonObj = new JSONObject();
	
	@Given("an email from {string} that already exists for a user")
	public void an_email_from_that_already_exists_for_a_user(String fileName) throws IOException {
		CSVParser csvParser = new CSVParserBuilder()
		        .withSeparator(',')
		        .withIgnoreQuotations(true)
		        .build();
		
		CSVReader csvReader = new CSVReaderBuilder(new FileReader("C:\\Intrum\\intrum-homework\\IntrumHomework\\src\\main\\resources\\" + fileName))
		        .withSkipLines(1)
		        .withCSVParser(csvParser)
		        .build();
		
		String[] nextLine;
		int x = 1;
	    while ((nextLine = csvReader.readNext()) != null) {
	      if (nextLine != null && x == 2) {
	        System.out.println(Arrays.toString(nextLine));
	        newUser = nextLine;
	      }
	      x++;
	    }
	
	
	}
	
	@When("I attempt to add a new user with this data")
	public void i_attempt_to_add_a_new_user_with_this_data() {
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
	
	@Then("the correct error code should be returned for duplicate data")
	public void the_correct_error_code_should_be_returned_for_duplicate_data() {
		assertEquals(422, response.getStatusCode());
	}
	
	
	@Given("an email from {string} that is not valid")
	public void an_email_from_that_is_not_valid(String fileName) throws IOException {
		CSVParser csvParser = new CSVParserBuilder()
		        .withSeparator(',')
		        .withIgnoreQuotations(true)
		        .build();
		
		CSVReader csvReader = new CSVReaderBuilder(new FileReader("C:\\Intrum\\intrum-homework\\IntrumHomework\\src\\main\\resources\\" + fileName))
		        .withSkipLines(1)
		        .withCSVParser(csvParser)
		        .build();
		
		String[] nextLine;
		int x = 1;
	    while ((nextLine = csvReader.readNext()) != null) {
	      if (nextLine != null && x == 3) {
	        System.out.println(Arrays.toString(nextLine));
	        newUser = nextLine;
	      }
	      x++;
	    }	
	}
	
	@When("I attempt to add a new user with this invalid email")
	public void i_attempt_to_add_a_new_user_with_invalid_email() {
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
	@Then("the correct error code should be returned for invalid data")
	public void the_correct_error_code_should_be_returned_for_invalid_data() {
		assertEquals(422, response.getStatusCode());	
	}
	
	
	@Given("valid new user data from the CSV file {string} and row number {int}")
	public void valid_new_user_data_from_the_csv_file_and_row_number(String fileName, Integer row)  throws IOException {
		CSVParser csvParser = new CSVParserBuilder()
		        .withSeparator(',')
		        .withIgnoreQuotations(true)
		        .build();
		
		CSVReader csvReader = new CSVReaderBuilder(new FileReader("C:\\Intrum\\intrum-homework\\IntrumHomework\\src\\main\\resources\\" + fileName))
		        .withSkipLines(1)
		        .withCSVParser(csvParser)
		        .build();
		
		String[] nextLine;
		int x = 1;
	    while ((nextLine = csvReader.readNext()) != null) {
	      if (nextLine != null && x == row) {
	        System.out.println(Arrays.toString(nextLine));
	        newUser = nextLine;
	      }
	      x++;
	    }
	
	
	}
	
	@When("I attempt to add a new user with this data without an AccessToken")
	public void i_attempt_to_add_a_new_user_with_this_data_without_an_access_token() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON);
		String jsonStringFromRequest = requestJSonObj.toString();

		response = request.body(jsonStringFromRequest)
				.post(BASE_URL);
	
	}
	
	@Then("the correct error code should be returned")
	public void the_correct_error_code_should_be_returned() {
		assertEquals(401, response.getStatusCode());	
	}
}
