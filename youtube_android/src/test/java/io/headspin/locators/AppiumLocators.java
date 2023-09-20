package io.headspin.locators;

import org.openqa.selenium.By;

public class AppiumLocators {
    public static By youtubelogo= By.xpath("//android.widget.ImageView[@content-desc='YouTube']");
    public static By searchicon= By.xpath("//android.widget.ImageView[@content-desc='Search']");
    public static By searchtab=By.xpath("//android.widget.EditText[contains(@text,'Search')]");
    public static By searchvideos=By.xpath("(//android.widget.TextView[contains(@text,'salaar')])[1]");
    public static By playvideo =By.xpath("(//android.view.ViewGroup)[17]");
    public static By videoplayer=By.xpath("//android.widget.FrameLayout[@content-desc='Video player']");
    public static By sharebutton=By.xpath("//android.view.ViewGroup[@content-desc='Share']");
}
