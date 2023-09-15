package io.headspin.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {
    @Given("User is on the Login page")
    public void userIsOnTheLoginPage() {
        System.out.println("User is on the Login page");
    }

    @When("User enter the valid credentials")
    public void userEnterTheValidCredentials() {
        System.out.println("User enter the valid credentials");
    }

    @And("hits submit")
    public void hitsSubmit() {
        System.out.println("hits submit");
    }

    @Then("the user should be in login page")
    public void theUserShouldBeInLoginPage() {
        System.out.println("the user should be in login page");
    }

    @When("User enter {string} and {string}")
    public void userEnterAnd(String id, String password) {
        System.out.println("user id "+id+" and password "+password);
    }
}
