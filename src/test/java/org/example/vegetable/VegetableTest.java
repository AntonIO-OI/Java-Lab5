package org.example.vegetable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class VegetableTest {

    @ParameterizedTest(name = "Test valid weight: {0}")
    @ValueSource(doubles = {1.0, 100.0, 1000.0})
    void testValidWeight(double weight) {
        Vegetable vegetable = new Tomato(weight);
        assertEquals(weight, vegetable.getWeight(),
                String.format("Weight should be %.2f but was %.2f", weight, vegetable.getWeight()));
    }

    @Test
    void testZeroWeight() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Tomato(0.0),
                "Creating vegetable with zero weight should throw IllegalArgumentException");
        assertEquals("Weight must be positive", exception.getMessage(),
                "Exception message doesn't match expected");
    }

    @Test
    void testNegativeWeight() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Tomato(-1.0),
                "Creating vegetable with negative weight should throw IllegalArgumentException");
        assertEquals("Weight must be positive", exception.getMessage(),
                "Exception message doesn't match expected");
    }

    @Test
    void testGetTotalCalories() {
        Vegetable vegetable = new Tomato(200.0); // 18 calories per 100g
        double expected = 36.0;
        double actual = vegetable.getTotalCalories();
        assertEquals(expected, actual, 0.01,
                String.format("Total calories for 200g should be %.2f but was %.2f", expected, actual));
    }

    @Test
    void testToString() {
        Vegetable vegetable = new Tomato(100.0);
        String expected = "Tomato (100.0g, 18.0 cal/100g, $2.99/kg)";
        String actual = vegetable.toString();
        assertEquals(expected, actual,
                String.format("toString() output incorrect.\nExpected: %s\nActual: %s", expected, actual));
    }
}
