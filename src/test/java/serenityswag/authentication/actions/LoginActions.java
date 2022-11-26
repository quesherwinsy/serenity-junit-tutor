package serenityswag.authentication.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import serenityswag.authentication.User;

public class LoginActions extends UIInteractionSteps {
    // implementation of actions of a page, like a POM
    @Step("Login as {0}")
    public void as(User users) {
        //driver.get("https://www.saucedemo.com");
        //driver.findElement(By.cssSelector("[data-test='username']")).sendKeys("standard_user");
        //driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        //driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        openUrl("https://www.saucedemo.com");
        find("[data-test='username']").sendKeys(users.getUsername());
        find("[data-test='password']").sendKeys(users.getPassword());
        find("[data-test='login-button']").click();
    }
}
