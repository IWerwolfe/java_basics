package ru.skillbox;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {

            System.out.print("Введите первое число: ");
            int num1 = new Scanner(System.in).nextInt();
            System.out.print("Введите второе число: ");
            int num2 = new Scanner(System.in).nextInt();

            ArithmeticCalculator calculator = new ArithmeticCalculator(num1, num2);
            System.out.println("Сумма чисел равна: " + calculator.calculate(Operation.ADD));
            System.out.println("Разница чисел равна: " + calculator.calculate(Operation.SUBTRACT));
            System.out.println("Произведение чисел равно: " + calculator.calculate(Operation.MULTIPLY));
        }
    }
}
