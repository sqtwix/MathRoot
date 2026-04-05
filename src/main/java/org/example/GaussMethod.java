package org.example;

import java.util.Scanner;

public class GaussMethod {

    /*
    Класс GaussMethod реализует решение системы линейных алгебраических уравнений методом
    Гаусса с выбором главного элемента.

     Метод Гаусса состоит из двух основных этапов:
        1.Прямой ход (Forward Elimination)
        2.Обратный ход (Back Substitution)

     Прямой ход (прямое исключение)
        Цель этапа — привести расширенную матрицу системы к верхнетреугольному виду.
        Для этого:

        - На каждом шаге выбирается ведущий элемент в текущем столбце.
        - Производится перестановка строк (при необходимости).
        - Ведущий элемент нормализуется до единицы.
        - С помощью элементарных преобразований обнуляются все элементы
         под ведущим элементом в данном столбце.

        В результате система принимает вид, при котором каждое следующее у
        равнение содержит на одну неизвестную меньше, чем предыдущее.

      Обратный ход (обратная подстановка)
        После приведения матрицы к треугольному виду начинается процесс последовательного
        нахождения неизвестных, начиная с последней переменной и двигаясь вверх.
        Каждое предыдущее уравнение используется для нахождения очередной переменной
        после подстановки уже найденных значений.
        Такой подход позволяет эффективно решать системы линейных
        уравнений с относительно небольшим количеством уравнений (до нескольких сотен).
    * */

    public static void solve() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== РЕШЕНИЕ СИСТЕМЫ ЛИНЕЙНЫХ УРАВНЕНИЙ МЕТОДОМ ГАУССА ===");

        System.out.print("Введите количество уравнений (2–5): ");
        int n = readInt(sc);

        if (n < 2 || n > 5) {
            System.out.println("ERROR: Количество уравнений должно быть от 2 до 5!");
            return;
        }

        double[][] A = new double[n][n + 1];

        System.out.println("\nВведите коэффициенты системы:");

        for (int i = 0; i < n; i++) {
            System.out.printf("\nУравнение %d:\n", i + 1);
            for (int j = 0; j < n; j++) {
                System.out.printf("   Коэффициент при x%d: ", j + 1);
                A[i][j] = sc.nextDouble();
            }
            System.out.print("   Свободный член: ");
            A[i][n] = sc.nextDouble();
            sc.nextLine(); // очистка буфера
        }

        System.out.println("\nИсходная система:");
        printSystem(A);

        // Прямой ход Гаусса
        forwardElimination(A);

        System.out.println("\nСистема после прямого хода (верхнетреугольная форма):");
        printSystem(A);

        // Обратный ход
        double[] solution = backSubstitution(A);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("РЕШЕНИЕ СИСТЕМЫ:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.6f\n", i + 1, solution[i]);
        }
        System.out.println("=".repeat(60));
    }

    // ПРЯМОЙ ХОД ГАУССА
    // Преобразует матрицу к верхнетреугольному виду путём последовательного
    // исключения переменных снизу вверх.
    private static void forwardElimination(double[][] A) {
        int n = A.length;

        for (int col = 0; col < n; col++) {

            // Частичный выбор главного элемента (pivot)
            int maxRow = col;
            for (int row = col + 1; row < n; row++) {
                if (Math.abs(A[row][col]) > Math.abs(A[maxRow][col])) {
                    maxRow = row;
                }
            }
            swapRows(A, col, maxRow);

            double pivot = A[col][col];
            if (Math.abs(pivot) < 1e-12) {
                System.out.println("ERROR: Система вырождена (нет единственного решения)");
                System.exit(0);
            }

            // Нормализуем текущую строку (делаем pivot = 1)
            for (int j = col; j <= n; j++) {
                A[col][j] /= pivot;
            }

            // Обнуляем все элементы ниже в этом столбце
            for (int row = col + 1; row < n; row++) {
                double factor = A[row][col];   // теперь A[col][col] == 1
                for (int j = col; j <= n; j++) {
                    A[row][j] -= factor * A[col][j];
                }
            }
        }
    }

    // ОБРАТНЫЙ ХОД (ОБРАТНАЯ ПОДСТАНОВКА)
    // Находит значения неизвестных, начиная с последней переменной
    // и последовательно подставляя найденные значения в предыдущие уравнения.
    private static double[] backSubstitution(double[][] A) {
        /*
        * */
        int n = A.length;
        double[] x = new double[n];

        for (int i = n - 1; i >= 0; i--) {
            x[i] = A[i][n];                    // свободный член
            for (int j = i + 1; j < n; j++) {
                x[i] -= A[i][j] * x[j];
            }
            // A[i][i] уже равен 1 после нормализации
        }
        return x;
    }

    private static void swapRows(double[][] A, int i, int j) {
        double[] temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static void printSystem(double[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%8.3f ", A[i][j]);
            }
            System.out.printf("| %8.3f\n", A[i][n]);
        }
    }

    private static int readInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("ERROR: Некорректный ввод числа");
            return -1;
        }
    }
}