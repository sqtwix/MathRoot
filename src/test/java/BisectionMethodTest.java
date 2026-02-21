import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.junit.jupiter.api.*;
import org.example.BisectionMethod;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BisectionMethodTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @Order(1)
    @DisplayName("Тест 1: Простой полином x^2 - 4")
    public void testSimplePolynomial() {
        BisectionMethod.printRoot("x^2 - 4", 0, 3, 0.0001);
        String output = outContent.toString();

        // Проверяем, что программа вообще что-то вывела
        assertFalse(output.isEmpty(), "Программа должна что-то вывести");

        // Проверяем, что есть сообщение о корне
        assertTrue(output.contains("КОРЕНЬ") || output.contains("корень"),
                "Должно быть сообщение о корне");
    }

    @Test
    @Order(2)
    @DisplayName("Тест 2: Проверка обработки ошибок")
    public void testErrorHandling() {
        BisectionMethod.printRoot("x^2 + 1", -1, 1, 0.0001);
        String output = outContent.toString();

        assertTrue(output.contains("ERROR"), "Должна быть ошибка");
    }

    @Test
    @Order(3)
    @DisplayName("Тест 3: Проверка пустого уравнения")
    public void testEmptyEquation() {
        BisectionMethod.printRoot("", 0, 1, 0.0001);
        String output = outContent.toString();

        assertTrue(output.contains("ERROR"), "Должна быть ошибка");
    }

    @Test
    @Order(4)
    @DisplayName("Тест 4: Проверка неверного формата")
    public void testInvalidFormat() {
        BisectionMethod.printRoot("x^^2", 0, 2, 0.0001);
        String output = outContent.toString();

        assertTrue(output.contains("ERROR"), "Должна быть ошибка");
    }

    @Test
    @Order(5)
    @DisplayName("Тест 5: Проверка вывода информации")
    public void testOutputInfo() {
        BisectionMethod.printRoot("x - 2", 1, 3, 0.0001);
        String output = outContent.toString();

        // Проверяем, что вывод содержит ожидаемые элементы
        assertTrue(output.contains("==="), "Должен быть заголовок");
        assertTrue(output.contains("Интервал"), "Должен быть интервал");
        assertTrue(output.contains("точность"), "Должна быть точность");
    }
}