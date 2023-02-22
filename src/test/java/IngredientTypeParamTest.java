import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeParamTest {
    private final String type;

    public IngredientTypeParamTest(String type) {
        this.type = type;
    }

    @Parameterized.Parameters
    public static Object[][] getType(){
        return new Object[][]{
                {"SAUCE"},
                {"FILLING"}
        };
    }

    @Test
    public void ingredientTypeTest(){
        String expected = type;
        String actual = IngredientType.valueOf(type).toString();
        assertEquals("Ошибка, такого ингредиента нет", expected, actual);

    }
}
