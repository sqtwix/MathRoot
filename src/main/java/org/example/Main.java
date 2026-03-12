package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Выберите метод решения: \n " +
                "1. Метод бисекции \n" +
                "2. Метод хорд");

        System.out.print("Ваш выбор: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: некорректный выбор");
            sc.close();
            return;
        }

        switch (choice) {
            case 1:
                bisectionSolving();
                break;

            case 2:
                break;

            default:
                System.out.println("ERROR. invalid input");
                break;
        }

        sc.close();
    }

    public static void bisectionSolving() {
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

    public static void chordSolving() {
        Scanner sc = new Scanner(System.in);

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
            ChordMethod.printRoot(equation, a, b, epsilon);
        } else {
            System.out.println("Программа завершена из-за ошибок ввода.");
        }

        sc.close();
    }
}