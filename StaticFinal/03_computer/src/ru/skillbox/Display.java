package ru.skillbox;    /*
 *created by WerWolfe on Display
 */

public class Display {

    private final double diagonal;
    private final TypeDisplay typeDisplay;
    private final double weight;

    public Display(double diagonal, TypeDisplay typeDisplay, double weight) {
        this.diagonal = diagonal;
        this.typeDisplay = typeDisplay;
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public TypeDisplay getTypeDisplay() {
        return typeDisplay;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Дисплей: \n" +
                "   Диагональ: " + diagonal + "\n" +
                "   Тип матрицы: " + typeDisplay + "\n" +
                "   Вес: " + weight;
    }
}
