package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public  class  BisectionMethod {

    // Class for finding root of equation by bisection method
    public BisectionMethod() { }

    public static void printRoot(String equation, double a, double b, double epsilon){
        if (equation == null || equation.trim().isEmpty())  {
            System.out.println("ERROR. Уравнение пустое");
        }

        Expression expr;
        try {
            expr = new ExpressionBuilder(equation)
                    .variable("x")
                    .build();
        } catch (Exception e) {
            System.out.println("ERROR. Неверный формат уравнения: " + e.getMessage());
            return;
        }

        // Finding value of functions by coordinats
        double fa, fb;
        try {
            fa = expr.setVariable("x", a).evaluate();
            fb = expr.setVariable("x", b).evaluate();
        } catch (Exception ex) {
            System.out.println("ERROR. Ошибка вычисления: " + ex.getMessage());
            return;
        }

        // Checking difference of signs
        if (fa * fb > 0) {
            System.out.println("ERROR. Функция должна иметь разные знаки на концах интервала");
            System.out.printf("f(a)=f(%f)=%f, f(b)=f(%f)=%f\n", a, fa, b, fb);
            return;
        }

        int index = 0;
        double answer = 0;

        System.out.println("===МЕТОД БИСЕКЦИИ===");
        System.out.printf("Интервал [%f, %f], точность %f\n", a, b, epsilon);
        System.out.println("-".repeat(50));

        while ((b-a) > epsilon) {
            System.out.printf("\n" + "-".repeat(30) + "\n");
            System.out.println("Левая граница: " + a);
            System.out.println("Правая граница: " + b);

            double c = (a + b) / 2;
            double fc = expr.setVariable("x", c).evaluate();

            // если модуль функции маленький — это хороший кандидат на корень
            if (Math.abs(fc) < epsilon) {
                answer = c;
                System.out.println("\nТОЧНЫЙ КОРЕНЬ НАЙДЕН (по значению функции): " + c);
                break;
            }

            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }

            answer = (a + b) / 2;
            index++;
        }

        // Printing result
        System.out.println("\n" + "=".repeat(30));
        System.out.printf("ПРИБЛИЖЕННЫЙ КОРЕНЬ: %f\n", answer);
        System.out.printf("Достигнутая точность: %e\n", b - a);
        System.out.printf("Количество итераций: %d\n", index);

        double finalValue = expr.setVariable("x", answer).evaluate();
        System.out.printf("Значение функции: %e\n", finalValue);
    }
}
