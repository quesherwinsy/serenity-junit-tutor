package seleniumeasy.actions;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class NavigateActions extends UIInteractionSteps {
    @Step("Navigate to FormPage enum class")
    public void to(FormPage formPage){
        // FormPage enum class - contains name of pages{} value to be used in opening URL
        // openPageNamed() - serenity predefined open url method
        // parameter value taken from serenity.conf, pages{} is mandatory
        openPageNamed(formPage.name());
    }
}
