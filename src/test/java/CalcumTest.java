import calcuator.Calculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CalcumTest {
    Calculator calculator;
    String numFilepath;

    @Before public void setUp() {
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("numbers.text").getPath();
    }
    @Test
    public void sumOfNumbers() throws IOException {
        Assert.assertEquals(this.numFilepath, 10);
    }
}
