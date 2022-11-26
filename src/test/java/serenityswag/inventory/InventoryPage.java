package serenityswag.inventory;

import net.serenitybdd.core.pages.PageObject;

public class InventoryPage extends PageObject {
    // implementation of POM page object model
    public String getHeading() {
        return find(".title").getText();
    }
}
