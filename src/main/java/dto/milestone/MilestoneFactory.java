package dto.milestone;

import com.github.javafaker.Faker;

import java.util.concurrent.TimeUnit;

public class MilestoneFactory {

    public static Milestone getMilestone() {
        Faker faker = new Faker();
        return new Milestone(faker.animal().name(),
                faker.color().name(),
                faker.witcher().monster(),
                faker.date().past(12, TimeUnit.DAYS),
                faker.date().future(12, TimeUnit.DAYS)
        );
    }
}
