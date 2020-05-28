package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
      (
        features= ".//Features/",
        glue="stepDefinitions",
        dryRun=false,
        monochrome=true,
        plugin= {"pretty","html:test-output"},
        tags= {"@sanity,@regression"}// OR operator means "either" "or"
                         //{"@sanity", "@regression"} AND operator means "both"
      )

public class TestRunner 
{

}
