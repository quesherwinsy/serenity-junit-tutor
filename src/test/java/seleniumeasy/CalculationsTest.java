package seleniumeasy;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.Qualifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import seleniumeasy.pageobjects.TwoInputFieldForm;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
@DisplayName("When adding two numbers and get the sum")
@Concurrent
public class CalculationsTest {
    @Managed(driver= "chrome", uniqueSession = true)
    WebDriver driver;

    TwoInputFieldForm twoInputFieldForm;

    // Serenity 5.0 don't use constructor and @TestData annotation
    /*
    private final String a;
    private final String b;
    private final String total;

    // constructor for parameters
    public CalculationsTest(String a, String b, String total) {
        this.a = a;
        this.b = b;
        this.total = total;
    }

    // test data as array of objects for parameter
    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(
            new Object[][] {
                    {"1", "2", "3"},
                    {"10", "20", "30"},
                    {"10", "0", "10"},
                    {"0", "10", "10"},
                    {"10000", "20000", "30000"}
            }
        );
    }
     */

    /**
     * Basic form fields (part 2). Enter two values and calculate the result
     * https://demo.seleniumeasy.com/basic-first-form-demo.html
     * Use @ParameterizedTest annotation for Serenity 5.0
     * Use @CsvSource annotation to read CSV data per row
     */
    @Qualifier
    public String qualifier(String a, String b, String total) {
        return a + " + " + b + " be equal to " + total;
    }
    @ParameterizedTest(name="Should add values {0} and {1}. Total must be {2}")
    @CsvSource({
            "1, 2, 3",
            "10, 20, 30",
            "10, 0, 10",
            "0, 10, 10",
            "1000, 2000, 3000"
    })
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
