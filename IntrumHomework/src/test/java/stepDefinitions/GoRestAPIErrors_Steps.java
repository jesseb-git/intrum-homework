package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

public class GoRestAPIErrors_Steps {

	private final String AccessToken = "?access-token=b69231ddbf1d785a5acd5742a3bf63bfe23cfedaa712e149e091c37fd0442349";
	private final String BASE_URL="https://gorest.co.in/public/v2/users";
	private Response response;
	private String[] newUser;
	JSONObject requestJSonObj = new JSONObject();
	
	@Given("an email from {string} that already exists for a user")
	public void an_email_from_that_already_exists_for_a_user(String string) {
	
	
	}
	
	@When("I attempt to add a new user with this data")
	public void i_attempt_to_add_a_new_user_with_this_data() {
	
	
	}
	
	@Then("the correct error code should be returned for duplicate data")
	public void the_correct_error_code_should_be_returned_for_duplicate_data() {
	
	
	}
	
	
	@Given("an email from {string} that is not valid")
	public void an_email_from_that_is_not_valid(String string) {
	
	
	}
	
	@When("I attempt to add a new user with this invalid email")
	public void i_attempt_to_add_a_new_user_with_invalid_email() {
	
	
	}
	@Then("the correct error code should be returned for invalid data")
	public void the_correct_error_code_should_be_returned_for_invalid_data() {
	
	
	}
	
	
	@Given("valid new user data from the CSV file {string} and row number {int}")
	public void valid_new_user_data_from_the_csv_file_and_row_number(String string, Integer int1) {
	
	
	}
	
	@When("I attempt to add a new user with this data without an AccessToken")
	public void i_attempt_to_add_a_new_user_with_this_data_without_an_access_token() {
	
	
	}
	
	@Then("the correct error code should be returned")
	public void the_correct_error_code_should_be_returned() {
	
	
	}
}
