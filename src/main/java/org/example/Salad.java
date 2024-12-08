package org.example;

import org.example.exception.CalorieRangeException;
import org.example.vegetable.Vegetable;

import java.util.*;

/**
 * Class representing a salad composed of various vegetables.
 */
public class Salad {
    private final List<Vegetable> vegetables;

    public Salad() {
        this.vegetables = new ArrayList<>();
    }

    /**
     * Add a vegetable to the salad.
     *
     * @param vegetable The vegetable to add
     * @throws NullPointerException if vegetable is null
     */
    public void addVegetable(Vegetable vegetable) {
        if (vegetable == null) {
            throw new NullPointerException("Vegetable cannot be null");
        }
        vegetables.add(vegetable);
    }

    /**
     * Calculate total calories of the salad.
     *
     * @return Total calories
     */
    public double calculateTotalCalories() {
        return vegetables.stream()
                .mapToDouble(Vegetable::getTotalCalories)
                .sum();
    }

    /**
     * Sort vegetables by calories (per 100g)
     */
    public void sortByCalories() {
        vegetables.sort(Comparator.comparingDouble(Vegetable::getCalories));
    }

    /**
     * Sort vegetables by weight in grams
     */
    public void sortByWeight() {
        vegetables.sort(Comparator.comparingDouble(Vegetable::getWeight));
    }

    /**
     * Sort vegetables by price per kg
     */
    public void sortByPrice() {
        vegetables.sort(Comparator.comparingDouble(Vegetable::getPrice));
    }

    /**
     * Sort vegetables by name alphabetically
     */
    public void sortByName() {
        vegetables.sort(Comparator.comparing(Vegetable::getName));
    }

    /**
     * Find vegetables within specified calorie range.
     *
     * @param minCalories Minimum calories per 100g
     * @param maxCalories Maximum calories per 100g
     * @return List of vegetables within range
     * @throws CalorieRangeException if range is invalid
     */
    public List<Vegetable> findVegetablesInCalorieRange(double minCalories, double maxCalories) 
            throws CalorieRangeException {
        if (minCalories < 0 || maxCalories < minCalories) {
            throw new CalorieRangeException("Invalid calorie range");
        }

        return vegetables.stream()
                .filter(v -> v.getCalories() >= minCalories && v.getCalories() <= maxCalories)
                .toList();
    }

    /**
     * Get all vegetables in the salad.
     *
     * @return List of vegetables
     */
    public List<Vegetable> getVegetables() {
        return new ArrayList<>(vegetables);
    }
} 