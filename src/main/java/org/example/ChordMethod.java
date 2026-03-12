package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ChordMethod {
    // Class for findind root of equation by chord method

    public ChordMethod() {
    }

    public static void printRoot(String equation, double a, double b, double epsilon) {
        equation = equation.replace(',', '.');

        if (equation == null || equation.trim().isEmpty()) {
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

        double fa, fb;
        try {
            fa = expr.setVariable("x", a).evaluate();
            fb = expr.setVariable("x", b).evaluate();
        } catch (Exception e) {
            System.out.println("ERROR. Ошибка вычисления: " + e.getMessage());
            return;
        }

        if (fa * fb >= 0) {
            System.out.println("ERROR. f(a) и f(b) должны иметь разные знаки");
            System.out.printf("f(%.6f) = %.6f,  f(%.6f) = %.6f\n", a, fa, b, fb);
            return;
        }

        if (Math.abs(fa) < epsilon) {
            printResult(a, fa, 0, "a");
            return;
        }

        if (Math.abs(fb) < epsilon) {
            printResult(b, fb, 0, "b");
            return;
        }

        System.out.println("\n=== МЕТОД ХОРД ===");
        System.out.printf("Уравнение: %s\n", equation);
        System.out.printf("Интервал:   [%.6f, %.6f]\n", a, b);
        System.out.printf("Точность:   %.2e (по |f(x)| и по изменению x)\n", epsilon);
        System.out.println("=".repeat(70));
        System.out.printf("%-4s %-14s %-18s %-12s %-10s %-10s\n",
                "№", "x", "f(x)", "Δx", "фиксир.", "действие");

        int iteration = 0;
        double prevX = b;

        while (true) {
            iteration++;

            double x;
            String fixed = "";

            if (fixed.equals("a")) {
                // фиксирована точка a → хорда от a к b
                x = b - fb * (b - a) / (fb - fa);
            } else if (fixed.equals("b")) {
                // фиксирована точка b → хорда от b к a
                x = a - fa * (b - a) / (fb- fa);
            } else {
                // первая итерация — без фиксированной точки, берём любую
                x = b - fb * (b - a) / (fb - fa);
            }

            double fx = safeEvaluate(expr, x);
            if (Double.isNaN(fx)) return;

            double delta = Math.abs(x - prevX);
            prevX = x;

            String currentFixed = fixed;

            if (fa * fx < 0) {
                // корень лежит между a и x → фиксируем a, двигаем b
                b = x;
                fb = fx;
                fixed = "a";
            } else {
                // корень лежит между x и b → фиксируем b, двигаем a
                a = x;
                fa = fx;
                fixed = "b";
            }

            String action = fixed.equals("a") ? "фиксируем a, двигаем b" : "фиксируем b, двигаем a";

            System.out.printf("%-5d %-14.10f %-18.10e %-14.2e %-10s %-25s\n",
                    iteration, x, fx, delta, currentFixed.isEmpty() ? "—" : currentFixed, action);

            if (Math.abs(fx) < epsilon || delta < epsilon) {
                printResult(x, fx, iteration, fixed);
                return;
            }

            if (iteration > 1000) {
                System.out.println("!!! Превышено максимальное число итераций !!!");
                printResult(x, fx, iteration, fixed);
                return;
            }
        }
    }

    public static double safeEvaluate(Expression expr, double val) {
        try {
            return expr.setVariable("x", val).evaluate();
        } catch (Exception e) {
            System.out.println("Ошибка вычисления в точке " + val + ": " + e.getMessage());
            return Double.NaN;
        }
    }

    private static void printResult(double x, double fx, int iter, String fixed) {
        System.out.println("-".repeat(70));
        System.out.println("Результат:");
        System.out.printf("  Корень:              %.10f\n", x);
        System.out.printf("  f(x) =               %.2e\n", fx);
        System.out.printf("  Итераций:            %d\n", iter);
        System.out.printf("  Последний фиксированный конец: %s\n", fixed);
        System.out.println("=".repeat(70));
    }
}
