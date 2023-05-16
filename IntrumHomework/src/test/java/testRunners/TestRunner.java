package testRunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class )
@CucumberOptions(
		features = {"src/main/resources/Features"},
		glue = {"stepDefinitions"},
		publish = true,
		plugin = {"pretty", "html:target/html-reports/report.html"}
		)
public class TestRunner {

}
