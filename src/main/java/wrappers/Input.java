package wrappers;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Input {

    String label;
    String inputPattern = "//label[contains(text(),'%s')]/following-sibling::input";
    SelenideElement input;

    public Input(String label) {
        this.label = label;
        this.input = $x(String.format(inputPattern, label));
    }

    public void write(String text) {
        log.info("Write '{}' in Input '{}'", text, label);
        input.setValue(text);
    }
}
