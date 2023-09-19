package io.headspin.steps;
import io.cucumber.java.Before;
import io.headspin.pages.AppiumPages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;

public class AppiumSteps {
    AppiumPages ap1 = new AppiumPages();
    @Given("User open web browser and go to URL")
    public void userOpenWebBrowserAndGoToURL() throws MalformedURLException, InterruptedException {
        ap1.user_open_browser();
    }

    @When("User enters correct credentials")
    public void userEntersCorrectCredentials() throws InterruptedException {
        ap1.user_enter_credentials();
    }

    @Then("User should be able to validate the output")
    public void userShouldBeAbleToValidateTheOutput() {
        ap1.verify();
    }
}
