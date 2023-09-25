package io.headspin.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "io.headspin.steps",
        tags = "@Appium"
)
public class RunnerTest extends AbstractTestNGCucumberTests {

}
