package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public  class  BisectionMethod {

    // Class for finding root of equation by bisection method
    public BisectionMethod() { }

    public static void printRoot(String equation, double a, double b, double epsilon){
        Expression expr = new ExpressionBuilder(equation)
                .variable("x")
                .build();

        double fa = expr.setVariable("x", a).evaluate();
        double fb = expr.setVariable("x", b).evaluate();

        int index = 0;
        double answer = 0;

        while ((b-a) > epsilon) {
            System.out.printf("\n" + "-".repeat(30) + "\n");
            System.out.println("Левая граница: " + a);
            System.out.println("Правая граница: " + b);

            double c = (a + b) / 2;

            System.out.printf("Серединная координата x%d = %f", index, c);

            double fc = expr.setVariable("x", c).evaluate();

            System.out.printf("\nЗначение функции f(x%d)=f(%f)= %f \n", index, c, fc);

            if (fc == 0) {
                System.out.println("ОТВЕТ:" + c);
            }

            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }

            answer = c;
            index++;
        }

        System.out.println("ОТВЕТ:" + answer);
    }
}
