import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import praktikum.Bun;
import org.junit.Test;

public class BunTest {

    Bun bun = new Bun("black bun", 100);

    @Test
    @DisplayName("Проверка геттера getName")
    public void testGetName() {
        Assertions.assertEquals("black bun", bun.getName());
    }

    @Test
    @DisplayName("Проверка геттера getPrice")
    public void testGetPrice() {
        Assertions.assertEquals(100, bun.getPrice());
    }
}
