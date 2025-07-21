package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.files.DownloadActions.click;

@Log4j2
public class MilestonePage extends BasePage{

    private static final String ADD_MILESTONE_BUTTON =
            "//div[@id='sidebar']/descendant::a[starts-with(normalize-space(.), 'Add Milestone')]",
            SUCCESS_MESSAGE_TITLE = "//*[@data-testid='messageSuccessDivBox']",
            EDIT_BUTTON = "//table//tr//td//a[contains(., '%s')]/following::div[1]",
            MILESTONE_PILL = "//table//tr//td//a[contains(., '%s')]";

    @Override
    public MilestonePage openPage() {
        return this;
    }

    @Override
    public MilestonePage isPageOpened() {
        $x(ADD_MILESTONE_BUTTON).isDisplayed();
        return this;
    }

    @Step("Get title for the 'Milestone' page")
    public boolean isSuccessMessageVisible() {
        log.info("Get title for the 'Milestone' page.");
        $x(SUCCESS_MESSAGE_TITLE).isDisplayed();
        return true;
    }

    @Step("Click on the 'Add Milestone' button.")
    public AddMilestonePage clickAddMilestone() {
        log.info("Click on the 'Add Milestone' button.");
        $x(ADD_MILESTONE_BUTTON).click();
        return new AddMilestonePage();
    }

    @Step("Editing the Milestone.")
    public void openEditMilestone(String malestoneName) {
        log.info("Editing the '{}' Milestone.", malestoneName);
        $x(String.format(MILESTONE_PILL, malestoneName)).hover();
        $x(String.format(EDIT_BUTTON, malestoneName)).click();
    }
}
