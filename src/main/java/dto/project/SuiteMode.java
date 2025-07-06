package dto.project;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuiteMode {

    SINGLE_FOR_ALL_CASES("suite_mode_single"),
    SINGLE_WITH_BASELINE("suite_mode_single_baseline"),
    MULTIPLE("suite_mode_multi");

    private final String suiteMode;
}
