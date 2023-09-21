package io.headspin.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.headspin.pages.AppiumPages;


import java.net.MalformedURLException;

public class Steps {
    AppiumPages ap1 = new AppiumPages();
    @Given("Launch the app {string} and {string}")
    public void launchTheAppAnd(String arg0, String arg1) throws MalformedURLException {
        ap1.launchapp(arg0,arg1);
        System.out.println("Launch the app....");
    }
    @When("Search for movie")
    public void searchForMovie() {
        System.out.println("Search for movie....");
        ap1.searchformovie();
    }

    @And("Choose the top option")
    public void chooseTheTopOption() {
        System.out.println("Choose the top option....");
        ap1.choosethemovie();
    }

    @Then("Wait for the video Load")
    public void waitForTheVideoLoad() {
        System.out.println("Wait for the video Load");
        ap1.endthesession();

    }
}
