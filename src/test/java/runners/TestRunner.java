package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "C:\\Users\\daiya\\IdeaProjects\\iqrashoppingdemo_v9\\src\\test\\java\\features\\CustomerWorkFlow.feature", // Path to feature files
        glue = "stepdefinitions",                // Package containing step definitions
        plugin = {
                "pretty",                              // Pretty console output
                "html:target/cucumber-reports/cucumber.html", // HTML report
                "json:target/cucumber-reports/cucumber.json"  // JSON report
        },
        monochrome = true                      // Clean console output
//        tags = "@SmokeTest"                        // Tags to filter which scenarios to run
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // No additional code needed; AbstractTestNGCucumberTests handles execution.
}

