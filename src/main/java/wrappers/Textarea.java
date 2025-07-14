package wrappers;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Textarea {

    String label;
    String textareaPattern = "//label//*[contains(text(),'%s')]/following-sibling::textarea";
    SelenideElement input;

    public Textarea(String label) {
        this.label = label;
        this.input = $x(String.format(textareaPattern, label));
    }

    public void write(String text) {
        log.info("Write '{}' in Textarea '{}'", text, label);
        input.setValue(text);
    }
}
