package seleniumeasy.pageobjects;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@DefaultUrl("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html")
public class DynamicDataPage extends SeleniumEasyForm{
    private static final By USER_DETAILS_PANEL = By.id("loading");
    public void getNewUser() {
        // FormButton is user created class, method to locate button via xpath
        $(FormButton.withLabel("Get New User")).click();
        // wait up to 30 sec. for element text to appear
        withTimeoutOf(Duration.ofSeconds(30))
                .waitFor(
                        ExpectedConditions.invisibilityOfElementWithText(USER_DETAILS_PANEL, "loading...")
                );
    }

    public String userDescription() {
        return $(USER_DETAILS_PANEL).getText();
    }
}
