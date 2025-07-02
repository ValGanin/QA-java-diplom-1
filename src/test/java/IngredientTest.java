import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import praktikum.Ingredient;

import static praktikum.IngredientType.SAUCE;

public class IngredientTest {

    Ingredient ingredient = new Ingredient(SAUCE, "dinosaur",200);

    @Test
    @DisplayName("Проверка геттера getType")
    public void testGetType() {
        Assertions.assertEquals(SAUCE, ingredient.getType());
    }

    @Test
    @DisplayName("Проверка геттера getName")
    public void testGetName() {
        Assertions.assertEquals("dinosaur", ingredient.getName());
    }

    @Test
    @DisplayName("Проверка геттера getPrice")
    public void testGetPrice() {
        Assertions.assertEquals(200, ingredient.getPrice());
    }
}
