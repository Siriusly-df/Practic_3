package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.println("Виберіть операцію (+, -, *, /, sqrt): ");
            String operation = scanner.next();

            if (operation.equals("sqrt")) {
                System.out.print("Введіть число: ");
                double a = scanner.nextDouble();
                double result = calculator.squareRoot(a);
                System.out.println("Результат: " + result);
            } else {
                System.out.print("Введіть перше число: ");
                double a = scanner.nextDouble();
                System.out.print("Введіть друге число: ");
                double b = scanner.nextDouble();

                double result = 0;
                switch (operation) {
                    case "+":
                        result = calculator.add(a, b);
                        break;
                    case "-":
                        result = calculator.subtract(a, b);
                        break;
                    case "*":
                        result = calculator.multiply(a, b);
                        break;
                    case "/":
                        result = calculator.divide(a, b);
                        break;
                    default:
                        throw new InvalidInputException("Помилка: Невідома операція!");
                }
                System.out.println("Результат: " + result);
            }

        } catch (ArithmeticException e) {
            System.out.println("Арифметична помилка: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Власна помилка валідації: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Помилка вводу: Будь ласка, введіть числове значення замість тексту.");
        } finally {
            System.out.println("Завершення обробки запиту.");
            scanner.close();
        }
    }
}