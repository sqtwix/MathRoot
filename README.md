# MathRoot - Bisection Method for Solving Equations
# MathRoot - Метод бисекции для решения уравнений

## English

### Description
MathRoot is a Java application that implements the bisection method for finding roots of mathematical equations. 
It uses the exp4j library to parse and evaluate mathematical expressions.

### Описание 
MathRoot это приложение на Java, которое находит корень уравнения методом половинного деления (бисекции). 
Оно использует exp4j для парсинга строка с уравнением.

### Features
- Решает уравнения методом бисекции
- Поддерживает разные математические функции (sin, cos, exp, log, etc.)
- User-friendly конкольный интерфейс
- Детализированный и подробный вывод
- Правильная работа с ошибками
- JUnit для тестирования

### Requirements
- Java 17 or higher
- Maven

### Dependencies
- exp4j (0.4.8) - for mathematical expression parsing
- JUnit 5 (5.9.2) - for testing

### Installation
1. Clone the repository
2. Build with Maven:
   ```bash
   mvn clean install
  ```
3. Usage

Run the Main class:
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

Follow the prompts:

    Enter your equation (e.g., "x^2 - 4", "sin(x)", "exp(x) - 2")

    Enter the left boundary (a)

    Enter the right boundary (b)

    Enter precision (epsilon)

Example
text

Enter equation: x^2 - 4
Enter a: 0
Enter b: 3
Enter epsilon: 0.0001

**Result: ~2.0**

4. Testing

Run tests with Maven:
```bash
mvn test
```
