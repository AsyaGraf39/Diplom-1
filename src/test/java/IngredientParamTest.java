import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class IngredientParamTest {
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientParamTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getIngredient(){
        return new Object[][]{
                {IngredientType.SAUCE, "Кетченез", 80},
                {IngredientType.SAUCE, "Чесночный", 100500},
                {IngredientType.FILLING, "Котлетка", 300},
                {IngredientType.FILLING, "Огурчик", 50}
        };
    }

    @Test
    public void createIngredientTest(){
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType expectedType = type;
        String expectedName = name;
        float expectedPrice = price;

        IngredientType actualType = ingredient.getType();
        String actualName = ingredient.getName();
        float actualPrice = ingredient.getPrice();
        assertEquals("Ошибка, тип ингредиента не соответствует ожидаемому", expectedType, actualType);
        assertEquals("Ошибка, имя не соответствует ожидаемому", expectedName, actualName);
        assertEquals("Ошибка, цена не соответствует ожидаемой", expectedPrice, actualPrice, 0);
    }
}
