package douglas.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * JUnit runner class for running the tests
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {"douglas/tests/stepdefs"}, plugin = {"json:target/cucumber.json"})

public class RunCucumberTest {
}

