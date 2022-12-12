package serenityswag.authentication.actions.cart;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;
// DefaultUrl() annotation - will direct to specific URL

@DefaultUrl("https://www.saucedemo.com/cart.html")
public class CartPageObject extends PageObject {
    // FindBy() annotation - selenium serenity support to find element
    @FindBy(css = "[data-test=checkout]")
    WebElementFacade checkoutButton;

    @FindBy(css = ".title")
    WebElementFacade title;

    @FindBy(className = "cart_item")
    List<WebElementFacade> cartItemElements;

    // find element by not using FindBy() annotation
    private static By CHECKOUT_BUTTON = By.id("checkout");
    private static By TITLE = By.cssSelector(".title");
    private static By CART_ITEMS = By.cssSelector(".cart_item");

    public List<CartItem> items() {
        // instantiate CartItem record class, traverse webElementS and place inside record class
        List<CartItem> cartItems = new ArrayList<>();
        // for(WebElementFacade cartItemElement : findAll(CART_ITEMS)){
        /* for(WebElementFacade cartItemElement : cartItemElements){
            String name = cartItemElement.findBy(".inventory_item_name").getText();
            String description = cartItemElement.findBy(".inventory_item_desc").getText();
            Double price = priceFrom(cartItemElement.findBy(".inventory_item_price").getText());
            cartItems.add(new CartItem(name, description, price));
        }*/

        // map() - convert an item into something else
        return findAll(CART_ITEMS).map(
                item -> new CartItem(
                        item.findBy(".inventory_item_name").getText(),
                        item.findBy(".inventory_item_desc").getText(),
                        priceFrom(item.findBy(".inventory_item_price").getText())
                )
        );
        //return cartItems;
    }

    private Double priceFrom(String textValue) {
        return Double.parseDouble(textValue.replace("$", ""));
    }

    public void checkOut(){
        checkoutButton.click();
    }

    public String getTitleText(){
        return title.getText();
    }
}
