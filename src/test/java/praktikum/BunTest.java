package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTest {
    private final static float DELTA = (float)0.00000001;

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getSumData() {
        return new Object[][] {
                { "bun1", (float)10.0},
                { "bun2", (float)5.01},
                { null, (float)10.0},
                { "", (float)10.0},
                { "asdasd".repeat(300), (float)10.0},
                { "asd#$&!@.?,+()!%asd", (float)10.0},
                { "bun with negative price", (float)-1.0},
                { "bun with minimal positive price", (float)-1.0},
                { "bun with minimal positive price", Float.MIN_VALUE},
                { "bun with minimal normal positive price", Float.MIN_NORMAL},
                { "bun with max price", Float.MAX_VALUE},
        };
    }

    @Test
    public void getName() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void getPrice() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(price, bun.getPrice(), DELTA);
    }
}