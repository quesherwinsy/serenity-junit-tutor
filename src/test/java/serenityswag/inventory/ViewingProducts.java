package serenityswag.inventory;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.actions.LoginActions;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static serenityswag.authentication.User.STANDARD_USER;

@ExtendWith(SerenityJUnit5Extension.class)
public class ViewingProducts extends UIInteractionSteps {
    @Managed(driver= "chrome")
    WebDriver driver;

    // serenity steps - describes actions class we do in a page
    @Steps
    LoginActions login;
    @Steps
    ViewProductDetailsAction viewProductDetails;
    // page object POM - describes reading information from a page
    ProductList productList;
    ProductDetails productDetails;


    @Test
    void displayHighlightedProducts(){
        //step to call the login
        login.as(STANDARD_USER);
        List<String> productsOnDisplay = productList.getTitles();

        assertThat(productsOnDisplay).hasSize(6).contains("Sauce Labs Backpack");
        driver.close();
    }

    @Test
    void displayCorrectProductDetailsPage(){
        login.as(STANDARD_USER);
        String firstItemName = productList.getTitles().get(0);
        //productList.openProductDetailsFor(firstItemName);
        viewProductDetails.forProductWithName(firstItemName);
        // check if opened product name detail is same
        // serenity.reportThat - will force report, same with @step annotation.
        Serenity.reportThat("Product name should display correctly",
                ()-> assertThat(productDetails.productName()).isEqualTo(firstItemName)
        );
        // check if product details contains visible image
        Serenity.reportThat("Product image should have correct alt text",
                ()-> productDetails.productImageWithAltValueOf(firstItemName).shouldBeVisible()
        );
    }

    @Test
    void highlightedProductsShouldDisplayTheCorrespondingImages(){
        login.as(STANDARD_USER);
        List<String> productsOnDisplay = productList.getTitles();
        // soft assertion - will not error when 1 or more have issue, it will produce error report at the end
        SoftAssertions softly = new SoftAssertions();
        productsOnDisplay.forEach(
                productName -> softly.assertThat(productList.imageTextForProducts(productName)).isEqualTo(productName)
        );
        softly.assertAll();
    }
}
