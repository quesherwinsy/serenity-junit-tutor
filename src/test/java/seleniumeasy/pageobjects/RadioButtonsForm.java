package seleniumeasy.pageobjects;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://demo.seleniumeasy.com/basic-radiobutton-demo.html")
public class RadioButtonsForm extends SeleniumEasyForm {

    public void selectOption(String gender) {
        // $("//input[@name='optradio'][@value='{0}']", gender).click();
        // serenity radio button predefined method
        inRadioButtonGroup("optradio").selectByValue(gender);
    }

    public void getCheckedValue() {
        $("#buttoncheck").click();
    }

    public String getResult() {
        return $(".radiobutton").getText();
    }
}
