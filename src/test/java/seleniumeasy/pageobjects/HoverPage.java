package seleniumeasy.pageobjects;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://the-internet.herokuapp.com/hovers")
public class HoverPage extends PageObject {

    public static final String FIGURE_IMAGE = "(//*[@class='figure'])[{0}]";
    public static final String FIGURE_CAPTION = "(//*[@class='figcaption'])[{0}]";

    public void hoverOverFigure(int number) {
        WebElementFacade figure = $(FIGURE_IMAGE, number);
        //serenity native move and hover action
        withAction().moveToElement(figure).perform();
        //selenium native move and hover action
        //new Actions(getDriver()).moveToElement(figure).perform();
    }

    public WebElementState captionForFigure(int number) {
        return $(FIGURE_CAPTION, number);
    }
}
