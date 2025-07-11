package tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

public class tTest extends BaseTest{

    @Test
    public void test() {
        int a = 2;
        Assert.assertEquals(a, 2);
    }
}
