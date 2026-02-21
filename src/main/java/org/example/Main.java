package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== РЕШЕНИЕ УРАВНЕНИЙ МЕТОДОМ БИСЕКЦИИ ===");

        System.out.println("Введите уравнение (по типу x^2 + x - 3):");
        String equation = sc.nextLine();

        double a = 0.0, b = 0.0, epsilon = 0.0;
        boolean inputValid = true;

        try {
            System.out.println("Введите a:");
            a = sc.nextDouble();
        } catch (Exception ex) {
            System.out.println("Ошибка при вводе a: " + ex.getMessage());
            inputValid = false;
        }

        if (inputValid) {
            try {
                System.out.println("Введите b:");
                b = sc.nextDouble();
            } catch (Exception ex) {
                System.out.println("Ошибка при вводе b: " + ex.getMessage());
                inputValid = false;
            }
        }

        if (inputValid) {
            try {
                System.out.println("Введите epsilon:");
                epsilon = sc.nextDouble();
            } catch (Exception ex) {
                System.out.println("Ошибка при вводе epsilon: " + ex.getMessage());
                inputValid = false;
            }
        }

        if (inputValid) {
            BisectionMethod.printRoot(equation, a, b, epsilon);
        } else {
            System.out.println("Программа завершена из-за ошибок ввода.");
        }

        sc.close();
    }
}