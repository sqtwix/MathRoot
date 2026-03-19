package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TangentMethod {
    public static void printRoot(String equation, double x0, double epsilon) {
        if (equation == null || equation.trim().isEmpty()) {
            System.out.println("ERROR. Уравнение пустое");
            return;
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

        System.out.println("\n=== МЕТОД КАСАТЕЛЬНЫХ т===");
        System.out.printf("Уравнение: %s\n", equation);
        System.out.printf("Начальное приближение: x0 = %.6f\n", x0);
        System.out.printf("Точность: %.2e\n", epsilon);
        System.out.println("=".repeat(70));

        System.out.printf("%-4s %-14s %-18s %-18s %-14s\n",
                "№", "x", "f(x)", "f'(x)", "Δx");

        int iteration = 0;
        double x = x0;
        double prevX = x0 + 2 * epsilon;  // для первой delta

        while (true) {
            iteration++;

            double fx = safeEvaluate(expr, x);
            if (Double.isNaN(fx)) return;

            // Числовая производная: f'(x) ≈ [f(x+h) - f(x-h)] / (2h)
            double h = 1e-8;  // маленький шаг
            double fxPlusH = safeEvaluate(expr, x + h);
            double fxMinusH = safeEvaluate(expr, x - h);
            if (Double.isNaN(fxPlusH) || Double.isNaN(fxMinusH)) return;

            double fPrimeX = (fxPlusH - fxMinusH) / (2 * h);

            if (Math.abs(fPrimeX) < 1e-12) {  // избегать деления на 0
                System.out.println("ERROR: производная близка к нулю — метод расходится");
                return;
            }

            double delta = fx / fPrimeX;
            double newX = x - delta;

            System.out.printf("%-4d %-14.10f %-18.10e %-18.10e %-14.2e\n",
                    iteration, x, fx, fPrimeX, Math.abs(delta));

            if (Math.abs(fx) < epsilon || Math.abs(delta) < epsilon) {
                printResult(newX, safeEvaluate(expr, newX), iteration);
                return;
            }

            x = newX;

            if (iteration > 500) {
                System.out.println("!!! Превышено максимальное число итераций !!!");
                printResult(x, fx, iteration);
                return;
            }
        }
    }

    private static double safeEvaluate(Expression expr, double val) {
        try {
            return expr.setVariable("x", val).evaluate();
        } catch (Exception e) {
            System.out.println("Ошибка вычисления в точке " + val + ": " + e.getMessage());
            return Double.NaN;
        }
    }

    private static void printResult(double x, double fx, int iter) {
        System.out.println("-".repeat(70));
        System.out.println("Результат:");
        System.out.printf("  Корень:              %.10f\n", x);
        System.out.printf("  f(x) ≈               %.2e\n", fx);
        System.out.printf("  Итераций:            %d\n", iter);
        System.out.println("=".repeat(70));
    }
}
