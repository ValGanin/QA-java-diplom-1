import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static praktikum.IngredientType.*;

public class BurgerTest {

    private Burger burger;

    private final Ingredient sauce = new Ingredient(SAUCE, "hot sauce", 100);
    private final Ingredient filling = new Ingredient(FILLING, "cutlet", 100);
    private final Bun bun = new Bun("red bun", 300);

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("Установка булки")
    public void setBunTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    @DisplayName("Добавление ингредиентов")
    public void addIngredientsTest() {
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    @DisplayName("Удаление ингредиента по индексу")
    public void removeIngredientTest() {
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(sauce, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Перемещение ингредиента")
    public void moveIngredientTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(1, 0);
        assertEquals(filling, burger.ingredients.get(0));
    }

    @ParameterizedTest(name = "bun: {0}, ingredients: {1}, expectedPrice: {2}")
    @MethodSource("priceProvider")
    @DisplayName("Расчёт стоимости бургера с моками")
    public void getPriceTest(float bunPrice, float[] ingredientPrices, float expectedPrice) {
        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bunMock);

        for (float price : ingredientPrices) {
            Ingredient ingredientMock = mock(Ingredient.class);
            when(ingredientMock.getPrice()).thenReturn(price);
            burger.addIngredient(ingredientMock);
        }

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    private static Stream<Arguments> priceProvider() {
        return Stream.of(
                Arguments.of(1.0f, new float[]{1.0f, 0.5f, 2.0f}, 5.5f),
                Arguments.of(2.0f, new float[]{1.0f, 1.0f, 1.0f}, 7.0f),
                Arguments.of(1.5f, new float[]{0.5f, 0.5f, 0.5f}, 4.5f)
        );
    }

    @Test
    @DisplayName("Генерация корректного рецепта")
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        String receipt = burger.getReceipt();

        float expectedPrice = bun.getPrice() * 2 + filling.getPrice() + sauce.getPrice();
        String expectedPriceString = String.format("%f", expectedPrice);

        assertAll(
                () -> assertTrue(receipt.contains("red bun")),
                () -> assertTrue(receipt.contains("cutlet")),
                () -> assertTrue(receipt.contains("hot sauce")),
                () -> assertTrue(receipt.contains(expectedPriceString))
        );
    }
}
