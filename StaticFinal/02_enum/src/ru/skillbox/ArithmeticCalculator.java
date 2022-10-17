package ru.skillbox;    /*
 *created by WerWolfe on ArithmeticCalculator
 */

public class ArithmeticCalculator {

    private int num1;
    private int num2;

    public ArithmeticCalculator(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public double calculate(Operation operation) {

        switch (operation) {
            case ADD -> {
                return num1 + num2;
            }
            case MULTIPLY -> {
                return num1 * num2;
            }
            case SUBTRACT -> {
                return num1 - num2;
            }
            default -> {
                return 0;
            }
        }
    }
}
