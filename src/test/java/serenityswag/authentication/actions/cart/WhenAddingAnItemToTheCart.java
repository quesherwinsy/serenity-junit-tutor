package serenityswag.authentication.actions.cart;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.actions.LoginActions;
import serenityswag.inventory.ProductList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static serenityswag.authentication.User.STANDARD_USER;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenAddingAnItemToTheCart {

    @Managed(driver= "chrome")
    WebDriver driver;

    // serenity steps - describes actions class we do in a page
    @Steps
    LoginActions login;

    @Steps
    CartActions cart;

    @BeforeEach
    public void login() {
        login.as(STANDARD_USER);
    }

    // POM
    ShoppingCartIcon shoppingCartIcon;
    ProductList productList;
    CartPageObject cartPage;


    @Test
    public void theCorrectItemCountShouldBeShown() {
        // check cart badge is empty
        Serenity.reportThat("The shopping cart badge should be empty",
                () -> assertThat(shoppingCartIcon.badgeCount()).isEmpty()
        );
        // add an item to the cart
        cart.addItem("Sauce Labs Backpack");
        // the shopping cart badge should be 1
        Serenity.reportThat("The shopping cart badge should now be '1'",
                () -> assertThat(shoppingCartIcon.badgeCount()).isEqualTo("1")
        );
    }

    @Test
    public void allTheItemsShouldAppearInTheCart() {
        // add several item to cart, view from index 0-2
        List<String> selectedItems = firstThreeProductTitlesDisplayed();
        // open cart page
        cart.addItems(selectedItems);
        // the shopping cart badge should be 3
        String cartBadgeCount = Integer.toString(selectedItems.size());
        Serenity.reportThat("The shopping cart badge should now be " + cartBadgeCount,
                () -> assertThat(shoppingCartIcon.badgeCount()).isEqualTo(cartBadgeCount)
        );
        // open and click cart icon link
        cart.openCart();
        // view all item listed in cart page
        Serenity.reportThat("See all of item listed",
                ()-> assertThat(cart.displayedItems()).containsExactlyElementsOf(selectedItems)
        );
    }

    @Test
    public void pricesForEachItemShouldBeShownInTheCart() {
        // add items to the shopping cart
        cart.addItems(firstThreeProductTitlesDisplayed());
        // open cart page - open() is POM defined method, uses DefaultUrl() annotation
        cartPage.open();
        // check each item in cart has a price, CartItem record class
        List<CartItem> items = cartPage.items();
        // assertj import - allows multiple assertions
        assertThat(items).hasSize(3).allMatch(item -> item.price() > 0.0)
                .allMatch(item -> !item.description().isEmpty());
    }

    private List<String> firstThreeProductTitlesDisplayed() {
        return productList.getTitles().subList(0, 3);
    }

}
