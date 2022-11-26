package serenityswag.authentication;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.actions.LoginActions;
import serenityswag.inventory.InventoryPage;
import static org.assertj.core.api.Assertions.assertThat;
import static serenityswag.authentication.User.STANDARD_USER;

// junit runwith -
@RunWith(SerenityRunner.class)
public class WhenLoggingOn extends UIInteractionSteps {
    // serenity managed - instantiate web driver
    @Managed
    WebDriver driver;

    // serenity steps - describes actions we do in a page
    @Steps
    LoginActions login;

    // page object POM - describes reading information from a page
    InventoryPage inventoryPage;

    // junit test -
    @Test
    public void usersCanLogOnViaHomePage(){
        //step to call the login
        login.as(STANDARD_USER);
        // serenity generates report about assert
        Serenity.reportThat("Inventory page displays correct title",
                ()-> assertThat(inventoryPage.getHeading()).isEqualToIgnoringCase("Products")
        );
    }
}
