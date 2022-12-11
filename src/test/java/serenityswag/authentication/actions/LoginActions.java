package serenityswag.authentication.actions;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import serenityswag.authentication.User;

public class LoginActions extends UIInteractionSteps {
    // Step - implementation of actions of a page, will be included in reports
    @Step("Login as {0}")
    public void as(User users) {
        //driver.get("https://www.saucedemo.com");
        //driver.findElement(By.cssSelector("[data-test='username']")).sendKeys("standard_user");
        //driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        //driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        openUrl("https://www.saucedemo.com");
        find(LoginForm.USER_NAME).sendKeys(users.getUsername());
        find(LoginForm.PASSWORD).sendKeys(users.getPassword());
        find(LoginForm.LOGIN_BUTTON).click();

        // find element by id
        /*
        find(By.id("user-name")).sendKeys(users.getUsername());
        find(By.id("password")).sendKeys(users.getPassword());
        find(By.id("login-button")).click();
         */
        // find element by name
        /*
        find(By.name("user-name")).sendKeys(users.getUsername());
        find(By.name("password")).sendKeys(users.getPassword());
        find(By.name("login-button")).click();
        */
        // find element by class

    }
}
