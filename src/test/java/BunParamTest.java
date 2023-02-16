import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class BunParamTest {
    private final String name;
    private final float price;

    public BunParamTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getBun(){
        return new Object[][]{
                {"big bun", 150},
                {"123!@#$", 0},
                {"", -8}
        };
    }

    @Test
    public void createBunTest(){
        Bun bun = new Bun(name, price);
        String expectedName = name;
        String actualName = bun.getName();
        float expectedPrice = price;
        float actualPrice = bun.getPrice();

        assertEquals("Ошибка, имя не соответствует ожидаемому", expectedName, actualName);
        assertEquals("Ошибка, цена не соответствует ожидаемой", expectedPrice, actualPrice, 0);
    }
}
