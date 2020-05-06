import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class HelloTargetTest {
    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello(" hachi"), is("Hello hachi"));

        Hello hi = new HelloTarget();
        assertThat(hi.sayHi(" hachi"), is("Hi hachi"));

        Hello thankyou = new HelloTarget();
        assertThat(thankyou.sayThankYou(" hachi"), is("Thank You hachi"));
    }


}