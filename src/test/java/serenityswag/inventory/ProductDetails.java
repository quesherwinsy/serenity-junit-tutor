package serenityswag.inventory;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementState;
import org.openqa.selenium.By;

public class ProductDetails extends PageObject {
    public String productName() {
        return find(".inventory_details_name").getText();
    }

    public WebElementState productImageWithAltValueOf(String firstItemName) {
        // WebElementState - Expose the state of web element
        // find web element by css
        return find(By.cssSelector(".inventory_details_container img[alt='"+firstItemName+"']"));
    }
}
