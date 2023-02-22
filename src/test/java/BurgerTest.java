import org.junit.Assert;
import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    IngredientType type;
    Database database = new Database();
    Burger burger = new Burger();
    Bun bun;

    @Test
    public void setBunsTest(){
        String bunName = "Булочка с кунжутом";
        float bunPrice = 666.6F;

        bun = new Bun(bunName, bunPrice);
        burger.setBuns(bun);

        String actualName = bun.getName();
        float actualPrice = bun.getPrice();

        assertEquals("Ошибка, имя не соответствует ожидаемому", bunName, actualName);
        assertEquals("Ошибка, цена не соответствует ожидаемой", bunPrice, actualPrice, 0);
    }

    @Test
    public void addIngredientTest() {
        Ingredient ingredient = database.availableIngredients().get(3);
        burger.addIngredient(ingredient);
        boolean isContainsActual = burger.ingredients.contains(ingredient);

        assertTrue("Ошибка, в списке нет добавленного ингредиента", isContainsActual);
    }

    @Test
    public void removeIngredientTest() {
        Ingredient ingredient = database.availableIngredients().get(1);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        boolean isContainsActual = burger.ingredients.contains(ingredient);

        assertFalse("Ошибка, ингредиент не удален", isContainsActual);
    }

    @Test
    public void moveIngredientTest() {
        Ingredient ingredient1 = database.availableIngredients().get(0);
        Ingredient ingredient2 = database.availableIngredients().get(3);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        int expectedIndex = burger.ingredients.indexOf(ingredient1);

        burger.moveIngredient(1, 0);

        int actualIndex = burger.ingredients.indexOf(ingredient1);

        assertNotEquals("Ошибка, ингредиент не на своем месте", expectedIndex, actualIndex);
    }

    @Test
    public void getPriceTest(){
        bun = new Bun("Булчище", 100);
        burger.setBuns(bun);

        Ingredient ingredient1 = database.availableIngredients().get(2); //300р
        Ingredient ingredient2 = database.availableIngredients().get(4); //200р
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expected = 700;
        float actual = burger.getPrice();

        assertEquals("Ошибка, цена не соответствует ожидаемой", expected, actual, 0);
    }


    @Spy
    Burger burgerSpy = new Burger();

    @Test
    public void getReceiptTest(){
        bun = new Bun("Булчище", 100);
        burgerSpy.setBuns(bun);

        Ingredient ingredient1 = database.availableIngredients().get(2); //300р
        Ingredient ingredient2 = database.availableIngredients().get(4); //200р
        burgerSpy.addIngredient(ingredient1);
        burgerSpy.addIngredient(ingredient2);

        Mockito.when(burgerSpy.getPrice()).thenReturn(700F);

        String expectedReceipt = "(==== Булчище ====)\r\n"
                + "= sauce chili sauce =\r\n" + "= filling dinosaur =\r\n"
                + "(==== Булчище ====)\r\n" + "\r\n" + "Price: 700,000000\r\n";

        String actualReceipt = burgerSpy.getReceipt();
        assertEquals("Ошибка в чеке", expectedReceipt, actualReceipt);


    }
}
