package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAllure {

    @Test
    public void test() {
        int sum = 2 + 4;
        Assert.assertEquals(sum, 6);
    }
}
