package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunEqualsTest {
    private final static float DELTA = (float)0.00000001;

    private final Bun bunFirst;
    private final Bun bunSecond;
    private final boolean result;

    public BunEqualsTest(Bun bunFirst, Bun bunSecond, boolean result) {
        this.bunFirst = bunFirst;
        this.bunSecond = bunSecond;
        this.result = result;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getSumData() {
        return new Object[][] {
                { new Bun("bun1", (float)10.0), new Bun("bun1", (float)10.0), true},
                { new Bun(null, (float)10.0), new Bun(null, (float)10.0), true},
                { new Bun("bun1", (float)10.0), new Bun("bun1", (float)9.999999999999), true},
                { new Bun("bun1", (float)10.0), new Bun("bun1", (float)10.0000000001), true},
                { new Bun("bun1", (float)10.0), new Bun(null, (float)10.0), false},
                { new Bun("bun1", (float)10.0), new Bun("abc", (float)10.0), false},
                { new Bun("bun1", (float)10.0), new Bun("bun1", (float)10.1), false},
                { new Bun(null, (float)10.0), new Bun(null, (float)10.1), false},
                { new Bun(null, (float)10.0), new Bun("abc", (float)10.0), false},
                { new Bun("bun1", (float)10.0), new Bun("abc", (float)10.1), false},
                { new Bun("bun1", (float)10.0), null, false},
        };
    }

    @Test
    public void equals() {
        Assert.assertEquals(result, bunFirst.equals(bunSecond));
    }
}