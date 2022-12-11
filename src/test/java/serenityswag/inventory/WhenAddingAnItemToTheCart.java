package serenityswag.inventory;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.actions.LoginActions;
import serenityswag.cart.ShoppingCartBadge;
import static org.assertj.core.api.Assertions.assertThat;
import static serenityswag.authentication.User.STANDARD_USER;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenAddingAnItemToTheCart {

    @Managed(driver= "chrome")
    WebDriver driver;

    // serenity steps - describes actions class we do in a page
    @Steps
    LoginActions login;

    // POM
    ShoppingCartBadge shoppingCartBadge;


    @Test
    public void theCorrectItemCountShouldBeShown() {
        login.as(STANDARD_USER);
        // check cart badge is empty
        assertThat(shoppingCartBadge.count()).isEmpty();
        // add an item to the cart

        // the shopping cart badge should be 1
        driver.close();
    }

    @Test
    public void allTheItemsShouldAppearInTheCart() {

    }

}
