package seleniumeasy;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import seleniumeasy.actions.FormPage;
import seleniumeasy.actions.NavigateActions;
import seleniumeasy.pageobjects.*;
import java.util.List;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is a series of exercises designed to explore how to use
 * Serenity BDD to test various kinds of HTML elements
 */
@ExtendWith(SerenityJUnit5Extension.class)
public class WhenInteractingWithInputForms {
    @Managed(driver= "chrome", uniqueSession = true)
    WebDriver driver;

    @Steps
    NavigateActions navigate;

    SingleInputFieldForm singleInputFieldForm;
    TwoInputFieldForm twoInputFieldForm;
    CheckboxForm checkboxForm;
    RadioButtonsForm radioButtonsForm;
    MultipleRadioButtonsForm multipleRadioButtonsForm;
    SelectListForm selectListForm;
    MultiSelectListForm multiSelectListForm;
    HoverPage hoverPage;

    /**
     * Basic form fields:
     * Enter a message and check that the message is correctly displayed
     * https://demo.seleniumeasy.com/basic-first-form-demo.html
     */
    @Test
    public void basicForms() {
        //singleInputFieldForm.open();
        navigate.to(FormPage.SingleInputFieldForm);
        singleInputFieldForm.enterMessage("hi there");
        singleInputFieldForm.showMessage();
        assertThat( singleInputFieldForm.displayedMessage()).isEqualTo("hi there");
    }

    /**
     * Basic form fields (part 2)
     * Enter two values and calculate the result
     * https://demo.seleniumeasy.com/basic-first-form-demo.html
     */
    @Test
    public void basicFormsWithMultipleFields() {
        //twoInputFieldForm.open();
        navigate.to(FormPage.TwoInputFieldForm);
        twoInputFieldForm.enterA("2");
        twoInputFieldForm.enterB("3");
        twoInputFieldForm.getTotal();
        assertThat(twoInputFieldForm.displayedTotal()).isEqualTo("5");
    }

    /**
     * Checkboxes
     * Check that a message appears when you click the checkbox
     * https://demo.seleniumeasy.com/basic-checkbox-demo.html
     */
    private static final List<String> ALL_THE_OPTIONS = asList("Option 1", "Option 2", "Option 3", "Option 4");

    @Test
    public void singleCheckbox() {
        checkboxForm.open();
        checkboxForm.setAgeSelected();
        assertThat(checkboxForm.ageText()).isEqualTo("Success - Check box is checked");
    }

    @Test
    public void multipleCheckboxes() {
        checkboxForm.open();
        assertThat(ALL_THE_OPTIONS).allMatch(
                option -> !checkboxForm.optionIsCheckedFor(option)
        );
        checkboxForm.checkAll();
        assertThat(ALL_THE_OPTIONS).allMatch(
                option -> checkboxForm.optionIsCheckedFor(option)
        );
    }

    /**
     * Radio buttons
     * Check that a message appears when you click the radio button
     * https://demo.seleniumeasy.com/basic-radiobutton-demo.html
     */
    @Test
    public void radioButtons() {
        radioButtonsForm.open();
        radioButtonsForm.selectOption("Male");
        radioButtonsForm.getCheckedValue();
        assertThat(radioButtonsForm.getResult()).isEqualTo("Radio button 'Male' is checked");
    }

    @Test
    public void multipleRadioButtons() {
        multipleRadioButtonsForm.open();
        multipleRadioButtonsForm.selectGender("Female");
        multipleRadioButtonsForm.selectAgeGroup("15 - 50");
        multipleRadioButtonsForm.getValues();
        assertThat(multipleRadioButtonsForm.getResult()).contains("Sex : Female").contains("Age group: 15 - 50");
    }

    /**
     * Dropdown lists
     * https://demo.seleniumeasy.com/basic-select-dropdown-demo.html
     */
    @Test
    public void selectList() {
        selectListForm.open();
        assertThat(selectListForm.selectedDay()).isEmpty();
        selectListForm.selectDay("Wednesday");
        assertThat(selectListForm.selectedDay()).isEqualTo("Wednesday");
    }

    /**
     * Multi-Select Dropdown lists
     * https://demo.seleniumeasy.com/basic-select-dropdown-demo.html
     */
    @Test
    public void multiSelectList() {
        multiSelectListForm.open();
        assertThat(multiSelectListForm.selectedStates()).isEmpty();
        multiSelectListForm.selectStates("Florida", "Ohio", "Texas");
        assertThat(multiSelectListForm.selectedStates()).containsExactly("Florida", "Ohio", "Texas");
    }

    /**
     * Hover and other mouse action
     * https://the-internet.herokuapp.com/hovers
     */
    @Test
    public void hover() {
        hoverPage.open();
        hoverPage.hoverOverFigure(1);
        hoverPage.captionForFigure(1).shouldBeVisible();
        hoverPage.captionForFigure(1).shouldContainText("user1");
        hoverPage.hoverOverFigure(2);
        hoverPage.captionForFigure(2).shouldBeVisible();
        hoverPage.captionForFigure(2).shouldContainText("user2");
    }
}
