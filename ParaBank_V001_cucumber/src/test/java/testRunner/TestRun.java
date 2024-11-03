/**
 * 
 */
package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * checks for every step in the feature file having steps created in LoginSteps.java
 */

@RunWith(Cucumber.class)
@CucumberOptions
		(
				features = {".//Features/"}, //feature file location
				glue = "stepDefinitions", //steps implementation location
				dryRun = false, //it checks for each feature step corresponding method
				monochrome = true, // removes unnecessary characters from console window
				plugin = {"pretty", //gives output on console window
						"html:target/cucumber-reports/reports_html.html", //generate the report under test-output folder
						"json:target/cucumber-reports/report_json.json"
				}, //report
				tags = "@sanity" //"@sanity or @regression" / "@sanity and @regression"
		)
public class TestRun {
	

}
