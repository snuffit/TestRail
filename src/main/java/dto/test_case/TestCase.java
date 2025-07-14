package dto.test_case;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TestCase {

    String title;
    @Builder.Default
    String template = "Test Case (Text)";
    @Builder.Default
    String type = "Other";
    @Builder.Default
    String priority = "Medium";
    @Builder.Default
    String assignedTo = "None";
    @Builder.Default
    String estimate = "";
    @Builder.Default
    String automationType = "None";
    @Builder.Default
    String references = "";
    @Builder.Default
    String preconditions = "";
    @Builder.Default
    String steps = "";
    @Builder.Default
    String expectedResult = "";
}
