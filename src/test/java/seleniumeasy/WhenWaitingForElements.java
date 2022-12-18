package seleniumeasy;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import seleniumeasy.pageobjects.AlertMessagePage;
import seleniumeasy.pageobjects.DynamicDataPage;
import seleniumeasy.pageobjects.ModelDialogPage;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenWaitingForElements {
    @Managed(driver= "firefox", uniqueSession = true)
    WebDriver driver;

    ModelDialogPage modalDialogPage;
    AlertMessagePage alertMessagePage;
    DynamicDataPage dynamicDataPage;

    /**
     * Wait for item to appear and disappear
     * https://demo.seleniumeasy.com/bootstrap-modal-demo.html
     */
    @Test
    public void waitingForAModalDialog() {
        // implicit wait is default, configurable in serenity.conf file, in webdriver{timeouts{implicitlywait}}
        modalDialogPage.open();
        modalDialogPage.saveChangesButton().shouldNotBeVisible();
        modalDialogPage.openModal();
        modalDialogPage.saveChangesButton().shouldBeVisible();
        modalDialogPage.saveChanges();
        modalDialogPage.saveChangesButton().shouldNotBeVisible();
    }

    /**
     * Implicit and explicit wait.
     * https://demo.seleniumeasy.com/bootstrap-alert-messages-demo.html
     */
    @Test
    public void waitingForAMessageToClose() {
        alertMessagePage.open();
        alertMessagePage.openSuccessMessage();
        assertThat(alertMessagePage.alertSuccessMessageText()).contains("I'm an autocloseable success message.");
        // will wait for message to disappear
        // configure serenity.conf, in webdriver{timeouts{fluentwait}}
        alertMessagePage.waitForMessageToDisappear();
        alertMessagePage.alertSuccessMessage().shouldNotBeVisible();
    }

    /**
     * Dynamic data waits
     * https://demo.seleniumeasy.com/dynamic-data-loading-demo.html
     */
    @Test
    public void waitingForElementsToAppear() {
        dynamicDataPage.open();
        dynamicDataPage.getNewUser();
        assertThat(dynamicDataPage.userDescription()).contains("First Name").contains("Last Name");
    }

    @Test
    public void waitingForElementsToDisappear() {

    }
}
