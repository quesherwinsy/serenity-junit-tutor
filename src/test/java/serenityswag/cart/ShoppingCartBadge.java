package serenityswag.cart;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class ShoppingCartBadge extends PageObject {
    public String count() {
        return find(By.cssSelector(".shopping_cart_link")).getText();
    }
}
