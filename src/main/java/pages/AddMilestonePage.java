package pages;

import dto.milestone.Milestone;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.Input;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AddMilestonePage extends BasePage {

    private static final String TITLE_ADD_MILESTONE = "//div[@data-testid='testCaseContentHeaderTitle']",
            ADD_MILESTONE_BUTTON = "//*[@data-testid='milestoneButtonOk']",
            MILESTONE_NAME = "Name",
            MILESTONE_REFERENCES = "References";

    @Override
    public BasePage openPage() {
        return null;
    }

    @Override
    public AddMilestonePage isPageOpened() {
        $x(TITLE_ADD_MILESTONE).isDisplayed();
        return this;
    }

    @Step("Click on the 'Add Milestone' button on the 'Add Milestone' page.")
    public MilestonePage clickAddMilestoneButton() {
        log.info("Click on the 'Add Milestone' button on the 'Add Milestone' page.");
        $x(ADD_MILESTONE_BUTTON).click();
        return new MilestonePage();
    }

    @Step("Creating an Milestone.")
    public AddMilestonePage createNewMilestone(Milestone milestone) {
        log.info("Creating an milestone with name '{}'.", milestone.getMilestoneName());
        new Input(MILESTONE_NAME).anotherWrite(milestone.getMilestoneName());
        new Input(MILESTONE_REFERENCES).anotherWrite(milestone.getMilestoneReferences());
        clickAddMilestoneButton();
        return this;
    }
}
