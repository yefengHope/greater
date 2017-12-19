package junit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by 韩峰 on 2017/12/12.
 */
public class FirstJUnit5Tests {

    @Test
    public void myFirstTest() {
        assertEquals(2, 1 + 1);
    }
}
