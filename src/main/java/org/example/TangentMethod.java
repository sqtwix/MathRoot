package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TangentMethod {

    public static void printRoot(String equation, double a, double b, double epsilon) {
        if (equation == null || equation.trim().isEmpty()) {
            System.out.println("ERROR. Уравнение пустое");
            return;
        }

        // Заменяем запятые на точки (на всякий случай)
        equation = equation.replace(',', '.');

        Expression expr;
        try {
            expr = new ExpressionBuilder(equation)
                    .variable("x")
                    .build();
        } catch (Exception e) {
            System.out.println("ERROR. Неверный формат уравнения: " + e.getMessage());
            return;
        }

        double fa = safeEvaluate(expr, a);
        double fb = safeEvaluate(expr, b);
        if (Double.isNaN(fa) || Double.isNaN(fb)) return;

        if (fa * fb >= 0) {
            System.out.println("ERROR. f(a) и f(b) должны иметь разные знаки");
            System.out.printf("f(%.6f) = %.6f,  f(%.6f) = %.6f\n", a, fa, b, fb);
            return;
        }

        if (Math.abs(fa) < epsilon) {
            printResult(a, fa, 0);
            return;
        }
        if (Math.abs(fb) < epsilon) {
            printResult(b, fb, 0);
            return;
        }

        // Начальное приближение — середина интервала (самый надёжный выбор)
        double x = (a + b) / 2.0;

        System.out.println("\n=== МЕТОД КАСАТЕЛЬНЫХ (НЬЮТОНА) ===");
        System.out.printf("Уравнение: %s\n", equation);
        System.out.printf("Интервал:   [%.6f, %.6f]\n", a, b);
        System.out.printf("Начальное приближение: x₀ = %.6f (середина интервала)\n", x);
        System.out.printf("Точность:   %.2e\n", epsilon);
        System.out.println("=".repeat(80));

        System.out.printf("%-5s %-16s %-20s %-20s %-16s\n",
                "№", "xₙ", "f(xₙ)", "f'(xₙ)", "Δx = |xₙ - xₙ₋₁|");

        int iteration = 0;
        double prevX = x + 2 * epsilon;  // искусственное начальное значение для первой итерации

        while (true) {
            iteration++;

            double fx = safeEvaluate(expr, x);
            if (Double.isNaN(fx)) return;

            // Числовая производная (центральная разность)
            double h = 1e-8;
            double fxPlus = safeEvaluate(expr, x + h);
            double fxMinus = safeEvaluate(expr, x - h);
            if (Double.isNaN(fxPlus) || Double.isNaN(fxMinus)) return;

            double fpx = (fxPlus - fxMinus) / (2 * h);

            if (Math.abs(fpx) < 1e-12) {
                System.out.println("ERROR: Производная близка к нулю → метод может расходиться");
                printResult(x, fx, iteration);
                return;
            }

            double delta = fx / fpx;
            double newX = x - delta;

            System.out.printf("%-5d %-16.10f %-20.10e %-20.10e %-16.2e\n",
                    iteration, x, fx, fpx, Math.abs(delta));

            if (Math.abs(fx) < epsilon || Math.abs(delta) < epsilon) {
                printResult(newX, safeEvaluate(expr, newX), iteration);
                return;
            }

            // Проверка выхода за интервал (опционально, но полезно)
            if (newX < a - epsilon || newX > b + epsilon) {
                System.out.println("Предупреждение: новое приближение вышло за пределы исходного интервала");
            }

            x = newX;
            prevX = x;  // для следующей итерации

            if (iteration > 200) {
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
        System.out.println("-".repeat(80));
        System.out.println("Результат:");
        System.out.printf("  Корень:              %.10f\n", x);
        System.out.printf("  f(x) ≈               %.2e\n", fx);
        System.out.printf("  Итераций:            %d\n", iter);
        System.out.println("=".repeat(80));
    }
}