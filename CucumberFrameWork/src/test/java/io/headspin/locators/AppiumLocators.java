package io.headspin.locators;

import org.openqa.selenium.By;

public class AppiumLocators {
    public static By form_auth_link = By.linkText("Form Authentication");
    public static By username = By.cssSelector("#username");
    public static By password = By.cssSelector("#password");

    public static By submitButton = By.cssSelector("button[type=submit]");

    public static By logOut = By.linkText("Logout");

    public static By flash = By.cssSelector("#flash");
}
