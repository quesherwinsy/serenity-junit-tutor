package serenityswag.authentication;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.actions.LoginActions;
import serenityswag.inventory.InventoryPage;
import static org.assertj.core.api.Assertions.assertThat;
import static serenityswag.authentication.User.STANDARD_USER;

// extendwith - need for junit 5
//@RunWith(SerenityRunner.class) - runwith no need for junit 5
@ExtendWith(SerenityJUnit5Extension.class)
class WhenLoggingOn extends UIInteractionSteps {
    // serenity managed - instantiate web driver
    @Managed
    WebDriver driver;

    // serenity steps - describes actions we do in a page
    @Steps
    LoginActions login;

    // page object POM - describes reading information from a page
    InventoryPage inventoryPage;

    // junit test - used junit 5 import
    @Test
    void usersCanLogOnViaHomePage(){
        //step to call the login
        login.as(STANDARD_USER);
        // serenity generates report about user credential used
        Serenity.recordReportData().withTitle("User credentials").andContents("User: " + STANDARD_USER);
        // serenity generates report about assert
        Serenity.reportThat("Inventory page displays correct title",
                ()-> assertThat(inventoryPage.getHeading()).isEqualToIgnoringCase("Products")
        );
        driver.close();
    }
}
