package org.example;

import org.example.exception.CalorieRangeException;
import org.example.vegetable.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaladTest {
    private Salad salad;

    @BeforeEach
    void setUp() {
        salad = new Salad();
        salad.addVegetable(new Tomato(100.0));      // 18 cal/100g
        salad.addVegetable(new Cucumber(150.0));    // 15 cal/100g
        salad.addVegetable(new Carrot(75.0));       // 41 cal/100g
        salad.addVegetable(new Onion(50.0));        // 40 cal/100g
        salad.addVegetable(new Lettuce(100.0));     // 15 cal/100g
        salad.addVegetable(new BellPepper(80.0));   // 31 cal/100g
    }

    @Test
    void testCalculateTotalCalories() {
        double expected = 131.05; // See calculation in comments below
        double actual = salad.calculateTotalCalories();
        assertEquals(expected, actual, 0.01,
                String.format("Total calories calculation incorrect.\nExpected: %.2f\nActual: %.2f",
                        expected, actual));
        /* Calculation breakdown:
         * Tomato: 18 * 100/100 = 18
         * Cucumber: 15 * 150/100 = 22.5
         * Carrot: 41 * 75/100 = 30.75
         * Onion: 40 * 50/100 = 20
         * Lettuce: 15 * 100/100 = 15
         * Bell Pepper: 31 * 80/100 = 24.8
         * Total: 131.05
         */
    }

    @Test
    void testEmptySalad() {
        Salad emptySalad = new Salad();
        assertEquals(0.0, emptySalad.calculateTotalCalories(), 0.01,
                "Empty salad should have 0 calories");
        assertTrue(emptySalad.getVegetables().isEmpty(),
                "Empty salad should have no vegetables");
    }

    @ParameterizedTest(name = "Range {0}-{1} should find {2} vegetables")
    @CsvSource({
            "15, 20, 3",  // Cucumber, Lettuce, Tomato
            "30, 35, 1",  // Bell Pepper
            "40, 45, 2",  // Onion, Carrot
            "0, 100, 6"   // All vegetables
    })
    void testFindVegetablesInCalorieRange(double min, double max, int expectedCount)
            throws CalorieRangeException {
        List<Vegetable> inRange = salad.findVegetablesInCalorieRange(min, max);
        assertEquals(expectedCount, inRange.size(),
                String.format("Expected %d vegetables in range %.1f-%.1f but found %d",
                        expectedCount, min, max, inRange.size()));
    }

    @Test
    void testAddNull() {
        assertThrows(NullPointerException.class,
                () -> salad.addVegetable(null),
                "Adding null vegetable should throw NullPointerException");
    }

    @Test
    void testSortingEmptySalad() {
        Salad emptySalad = new Salad();

        // These operations should not throw exceptions
        assertDoesNotThrow(emptySalad::sortByCalories,
                "Sorting empty salad by calories should not throw exception");
        assertDoesNotThrow(emptySalad::sortByWeight,
                "Sorting empty salad by weight should not throw exception");
        assertDoesNotThrow(emptySalad::sortByName,
                "Sorting empty salad by name should not throw exception");
    }

    @Test
    void testSortByPrice() {
        salad.sortByPrice();
        List<Vegetable> sorted = salad.getVegetables();

        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(sorted.get(i).getPrice() <= sorted.get(i + 1).getPrice(),
                    String.format("Price sorting failed at index %d: %.2f > %.2f",
                            i, sorted.get(i).getPrice(), sorted.get(i + 1).getPrice()));
        }
    }

    @Test
    void testFindVegetablesWithNoResults() throws CalorieRangeException {
        List<Vegetable> inRange = salad.findVegetablesInCalorieRange(1000, 2000);
        assertTrue(inRange.isEmpty(),
                "No vegetables should be found in range 1000-2000 calories");
    }
}
