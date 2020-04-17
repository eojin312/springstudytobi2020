import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CalcumTest {

    @Test
    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        int sum = calculator.calSum(getClass().getResource(
                "numbers.text").getPath());
        Assert.assertEquals(sum, 10);
    }
}
