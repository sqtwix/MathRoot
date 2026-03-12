package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class СhordMethod {
    // Class for findind root of equation by chord method

    public СhordMethod() { }

    public void printRoot(String equation, double a, double b, double epsilon) {
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

        if (Math.abs(fa) < epsilon){
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
        double x;

        while (true) {
            iteration++;

            if (fa * fb <= 0) {
                x = x = b - fb * (b - a) / (fb - fa);
            } else {
                // это уже не должно происходить, но на всякий случай
                System.out.println("Знаки стали одинаковыми — ошибка логики");
                break;
            }

            double fx;
            try {
                fx = expr.setVariable("x", x).evaluate();
            } catch (Exception e) {
                System.out.println("Ошибка вычисления в точке " + x);
                return;
            }

            double delta = Math.abs(x - b);

            String fixedPoint = (fa * fx < 0) ? "a" : "b";
            String action = (fa * fx < 0) ? "b → x" : "a → x";

            System.out.printf("%-4d %-14.10f %-18.10e %-12.2e %-10s %-10s\n",
                    iteration, x, fx, delta, fixedPoint, action);

            if (Math.abs(fx) < epsilon || Math.abs(x - b) < epsilon || Math.abs(x - a) < epsilon) {
                printResult(x, fx, iteration, fixedPoint);
                return;
            }

            // Обновляем только ту границу, у которой знак совпадает со знаком в новой точке
            if (fa * fx < 0) {
                // корень между a и x → фиксируем a, двигаем b
                b = x;
                fb = fx;
            } else {
                // корень между x и b → фиксируем b, двигаем a
                a = x;
                fa = fx;
            }

            if (iteration > 1000) {
                System.out.println("!!!Превышено максимальное число итераций!!!");
                printResult(x, fx, iteration, "—");
                return;
            }
        }
    }

    private static void printResult(double x, double fx, int iter, String fixed) {
        System.out.println("-".repeat(70));
        System.out.println("Результат:");
        System.out.printf("  Корень:              %.10f\n", x);
        System.out.printf("  f(x) ≈               %.2e\n", fx);
        System.out.printf("  Итераций:            %d\n", iter);
        System.out.printf("  Последний фиксированный конец: %s\n", fixed);
        System.out.println("=".repeat(70));
    }
}
