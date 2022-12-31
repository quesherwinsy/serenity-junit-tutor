package seleniumeasy;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.Qualifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import seleniumeasy.pageobjects.TwoInputFieldForm;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
@DisplayName("When adding two numbers and get the sum")
@Concurrent
public class CalculationsTestReadCSV {
    @Managed(uniqueSession = true)
    WebDriver driver;

    TwoInputFieldForm twoInputFieldForm;

    /**
     * Basic form fields (part 2). Enter two values and calculate the result
     * https://demo.seleniumeasy.com/basic-first-form-demo.html
     * Use @ParameterizedTest annotation for Serenity 5.0
     * Use @CsvFileSource annotation to read CSV in given path location (files)
     */
    @ParameterizedTest(name="Should add values {0} and {1}. Total must be {2}")
    @CsvFileSource(files="src/test/resources/test-data/calculations.csv")
    public void shouldCalculateTheCorrectTotal(String a, String b, String total) {
        twoInputFieldForm.open();
        twoInputFieldForm.enterA(a);
        twoInputFieldForm.enterB(b);
        twoInputFieldForm.getTotal();
        // assertThat(twoInputFieldForm.displayedTotal()).isEqualTo(total);
        Serenity.reportThat(a + " + " + b + " be equal to " + total,
                ()-> assertThat(twoInputFieldForm.displayedTotal()).isEqualTo(total)
        );
    }

    private List<String> listFrom(String items) {
        return Arrays.asList(items.split(";"));
    }
}
