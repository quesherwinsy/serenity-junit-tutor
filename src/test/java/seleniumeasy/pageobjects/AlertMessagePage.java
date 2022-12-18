package seleniumeasy.pageobjects;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementState;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import java.time.Duration;

@DefaultUrl("https://demo.seleniumeasy.com/bootstrap-alert-messages-demo.html")
public class AlertMessagePage extends SeleniumEasyForm{
    private static final By ALERT_SUCESS_MESSAGE = By.cssSelector(".alert-autocloseable-success");

    public void openSuccessMessage() {
        $("#autoclosable-btn-success").click();
    }

    public String alertSuccessMessageText() {
        return $(ALERT_SUCESS_MESSAGE).getText();
    }

    public void waitForMessageToDisappear() {
        // configure serenity.conf, in webdriver{timeouts{fluentwait}}
        // waitForRenderedElementsToDisappear(ALERT_SUCESS_MESSAGE);
        // or use this configurable wait time method
        withTimeoutOf(Duration.ofSeconds(10)).waitForElementsToDisappear(ALERT_SUCESS_MESSAGE);
    }

    public WebElementState alertSuccessMessage() {
        // find() return WebElementState - locate the element
        return find(ALERT_SUCESS_MESSAGE);
    }
}
