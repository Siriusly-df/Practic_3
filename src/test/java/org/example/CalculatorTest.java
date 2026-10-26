package org.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        assertEquals(5.0, calculator.add(2.0, 3.0));
    }

    @Test
    void testSubtract() {
        assertEquals(1.0, calculator.subtract(3.0, 2.0));
    }

    @Test
    void testMultiply() {
        assertEquals(6.0, calculator.multiply(2.0, 3.0));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, calculator.divide(6.0, 3.0));
    }

    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(5.0, 0.0);
        });
        assertEquals("Помилка: Ділення на нуль неможливе!", exception.getMessage());
    }

    @Test
    void testSquareRootValid() throws InvalidInputException {
        assertEquals(4.0, calculator.squareRoot(16.0));
    }

    @Test
    void testSquareRootInvalidInput() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            calculator.squareRoot(-9.0);
        });
        assertEquals("Помилка: Неможливо обчислити квадратний корінь з від'ємного числа!", exception.getMessage());
    }

    @Test
    void testMainHandlesTextInsteadOfNumber() {
        var originalIn = System.in;
        var originalOut = System.out;
        var output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream("+\nabc\n".getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));

            Main.main(new String[0]);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        String consoleOutput = output.toString(StandardCharsets.UTF_8);
        assertTrue(consoleOutput.contains("Помилка вводу: Будь ласка, введіть числове значення замість тексту."));
        assertTrue(consoleOutput.contains("Завершення обробки запиту."));
    }
}
