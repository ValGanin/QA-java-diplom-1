import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import praktikum.IngredientType;

public class IngredientTypeTest {

    @Test
    @DisplayName("Проверка возвращаения енамов в String")
    public void testIngredientTypeToString() {
        Assertions.assertEquals("SAUCE", IngredientType.SAUCE.name());
        Assertions.assertEquals("FILLING", IngredientType.FILLING.name());
    }
}
