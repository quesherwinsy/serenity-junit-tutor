package serenityswag.authentication.actions.cart;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import serenityswag.inventory.ProductList;

import java.util.List;

public class CartActions extends UIInteractionSteps {
    // POM instantiate
    //ShoppingCartIcon shoppingCartIcon;

    @Step("Add '{0}' to the cart")
    public void addItem(String itemName) {
        $(ProductList.addToCartButtonFor(itemName)).click();
    }

    @Step("Add '{0}' multiple item to cart")
    public void addItems(List<String> items) {
        // loop each item and click via item()
        //items.forEach(this::item);
        for(String itemName : items){
            addItem(itemName);
        }
    }

    @Step("Open the shopping cart page")
    public void openCart() {
        // find page object of shopping cart link, 2nd code is static
        //$(shoppingCartIcon.link()).click();
        $(ShoppingCartIcon.link()).click();
    }

    public List<String> displayedItems() {
        // findAll - get all web element and all texts
        return findAll(".inventory_item_name").texts();
    }
}
