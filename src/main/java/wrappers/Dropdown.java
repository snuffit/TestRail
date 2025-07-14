package wrappers;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Dropdown {

    String label;
    String selectPattern = "//label[contains(text(),'%s')]/following-sibling::select";
    SelenideElement dropdown;

    public Dropdown(String label) {
        this.label = label;
        this.dropdown = $x(String.format(selectPattern, label));
    }

    public void select(String option) {
        log.info("Select '{}' in dropdown '{}'", option, label);
        dropdown.selectOption(option);
    }
}
