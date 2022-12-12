package serenityswag.inventory;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import java.util.List;

public class ProductList extends PageObject {

    public static By addToCartButtonFor(String itemName) {
        return By.xpath("//div[@class='inventory_item'][contains(.,'" + itemName + "')]//button");
    }

    public List<String> getTitles() {
        // find all web elementS by classname, get text content
        return findAll(By.className("inventory_item_name")).textContents();
        // find all web elementS by css classname
        //return findAll(By.className(".inventory_item_name")).textContents();
    }
    public String imageTextForProducts(String productName) {
        // find web element by xpath
        return find(By.xpath("//div[@class='inventory_item'][contains(.,'"+ productName +"')]//img")).getAttribute("alt");
    }
    public static By productDetailsLinkFor(String ItemName) {
        return By.linkText(ItemName);
    }
}
