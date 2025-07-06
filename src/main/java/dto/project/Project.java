package dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Project {

    String name;
    String announcement;
    String suiteMode;
    boolean isShowAnnouncement;
    boolean isEnableApprovals;
}
