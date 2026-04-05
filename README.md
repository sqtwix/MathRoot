# MathRoot - solving equations and system of equations by defferent ways | решения уравнений и систем уравнений разными способами

## _Русский_

### Описание
**MathRoot** — это консольное Java-приложение для решения **нелинейных уравнений** и **систем линейных уравнений** численными методами.

### Реализованные методы
Приложение поддерживает четыре метода:

1. Метод бисекции (метод половинного деления)
2. Метод хорд (метод ложного положения)
3. Метод касательных (метод Ньютона)
4. Метод Гаусса — для решения систем линейных уравнений

Для разбора и вычисления математических выражений используется библиотека **exp4j**.

### Возможности

- Решение нелинейных уравнений тремя различными методами
- Решение систем линейных уравнений методом Гаусса
- Поддержка широкого спектра математических функций (sin, cos, exp, log, sqrt и др.)
- Удобный интерактивный консольный интерфейс
- Подробный пошаговый вывод итераций для каждого метода
- Корректная обработка ошибок и валидация ввода
- Поддержка как точки, так и запятой в качестве десятичного разделителя
- Юнит-тесты (JUnit 5)

### Требования

- Java 17 или выше
- Maven 3.6+

### Зависимости

- exp4j (0.4.8) — парсинг и вычисление математических выражений
- JUnit 5 (5.9.2) — для тестирования

### Установка

Клонируйте репозиторий:

```
git clone <repository-url>
cd MathRoot
```

Соберите проект:
```
mvn clean install
```

### Запуск
```
mvn exec:java -Dexec.mainClass="org.example.Main"
```

После запуска выберите один из четырёх доступных методов.
Пример использования (Метод бисекции)

```Выберите метод решения:
Выберите метод решения:
1. Метод бисекции
2. Метод хорд
3. Метод касательной
4. Метод Гаусса (СИСТЕМА УРАВНЕНИЙ)
Ваш выбор: 1

Введите уравнение: x^2 - 4
Введите a: 0
Введите b: 3
Введите epsilon: 0.0001

Result: x ≈ 2.000000
```

### Тестирование
```
mvn test
```

## _English_
MathRoot is a Java console application designed to solve **nonlinear equations** and **systems of linear equations** using various numerical methods.

### Description

**MathRoot implements four numerical methods:**

- Bisection Method (Method of Half Division)
- Chord Method (False Position / Regula Falsi)
- Tangent Method (Newton-Raphson Method)
- Gauss Method for solving systems of linear equations

The application uses the **exp4j library** to parse and evaluate mathematical expressions, providing a user-friendly console interface with detailed iteration output.

### Features

- Solving nonlinear equations using three different methods (Bisection, Chord, Newton)
- Solving systems of linear equations using the Gauss elimination method
- Support for a wide range of mathematical functions (sin, cos, exp, log, sqrt, etc.)
- User-friendly interactive console interface
- Detailed step-by-step output for each method
- Robust error handling and input validation
- Support for both dot and comma as decimal separators
- JUnit 5 tests for verification

### Requirements

- Java 17 or higher
- Maven 3.6+


### Dependencies

- exp4j (0.4.8) — mathematical expression parsing and evaluation
- JUnit 5 (5.9.2) — unit testing

### Installation

Clone the repository:

```
git clone <repository-url>
cd MathRoot
```

Build the project:

```
mvn clean install
```

### Usage
Run the application:

```
mvn exec:java -Dexec.mainClass="org.example.Main"
```

Follow the prompts in the console. You can choose one of four methods:

1. Bisection Method
2. Chord Method
3. Tangent Method (Newton-Raphson)
4. Gauss Method (for systems of linear equations)

### Example (Bisection Method)

```
Выберите метод решения:
1. Метод бисекции
2. Метод хорд
3. Метод касательной
4. Метод Гаусса (СИСТЕМА УРАВНЕНИЙ)
Ваш выбор: 1

Введите уравнение: x^2 - 4
Введите a: 0
Введите b: 3
Введите epsilon: 0.0001

Result: x ≈ 2.000000
```

### Testing

```
mvn test
```
