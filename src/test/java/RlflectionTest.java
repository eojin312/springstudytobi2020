import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class RlflectionTest {

    @Test
    /**
     * String 클래스의 length 메소드와 charAt 메소드 코드에서 직접 호출하는 방법과 메소드를 이용해 리플렉션 방식으로 호출하는 방법
     */
    public void invokeMethod() throws Exception {
        String name = "Spring";

        //legth()
        assertThat(name.length(), is(6));

        Method lengthMethod = String.class.getMethod("length");
        assertThat((Integer)lengthMethod.invoke(name), is(6));

        assertThat(name.charAt(0), is('S'));

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertThat((Character)charAtMethod.invoke(name, 0), is('S'));
    }
}
