package seleniumeasy.pageobjects;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://demo.seleniumeasy.com/basic-radiobutton-demo.html")
public class MultipleRadioButtonsForm extends SeleniumEasyForm {

    public void selectGender(String gender) {
        // serenity radio button predefined method
        inRadioButtonGroup("gender").selectByValue(gender);
    }

    public void selectAgeGroup(String age) {
        // serenity radio button predefined method
        inRadioButtonGroup("ageGroup").selectByValue(age);
    }

    public void getValues() {
        $(FormButton.withLabel("Get values")).click();
    }

    public String getResult() {
        return $(".groupradiobutton").getText();
    }
}
