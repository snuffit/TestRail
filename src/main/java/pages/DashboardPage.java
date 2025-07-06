package pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage extends BasePage {

    private final String URI = "projects/overview/1";

    @Override
    public BasePage openPage() {
        return null;
    }

    @Override
    public DashboardPage isPageOpened() {
        $(byText("Dashboard")).isDisplayed();
        return this;
    }
}
