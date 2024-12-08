package org.example;

import org.example.exception.CalorieRangeException;
import org.example.vegetable.*;

/**
 * Main class demonstrating the Salad management system.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Salad salad = new Salad();

            salad.addVegetable(new Tomato(100.0));
            salad.addVegetable(new Cucumber(150.0));
            salad.addVegetable(new Carrot(75.0));
            salad.addVegetable(new Onion(50.0));
            salad.addVegetable(new Lettuce(100.0));
            salad.addVegetable(new BellPepper(80.0));

            System.out.println("Salad composition:");
            salad.getVegetables().forEach(System.out::println);

            System.out.printf("\nTotal calories: %.2f\n", salad.calculateTotalCalories());

            System.out.println("\nVegetables sorted by weight:");
            salad.sortByWeight();
            salad.getVegetables().forEach(System.out::println);

            System.out.println("\nVegetables sorted alphabetically by name:");
            salad.sortByName();
            salad.getVegetables().forEach(System.out::println);

            System.out.println("\nVegetables sorted by calories:");
            salad.sortByCalories();
            salad.getVegetables().forEach(System.out::println);

            System.out.println("\nVegetables with 15-20 calories per 100g:");
            salad.findVegetablesInCalorieRange(15, 20)
                    .forEach(System.out::println);

        } catch (CalorieRangeException e) {
            System.err.println("Calorie range error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
