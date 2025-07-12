package dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Project {

    String name;
    @Builder.Default
    String announcement = "null";
    @Builder.Default
    String suiteMode = SuiteMode.SINGLE_FOR_ALL_CASES.getSuiteMode();
    @Builder.Default
    boolean isShowAnnouncement = false;
    @Builder.Default
    boolean isEnableApprovals = false;
}
