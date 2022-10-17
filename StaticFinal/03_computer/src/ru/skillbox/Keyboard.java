package ru.skillbox;    /*
 *created by WerWolfe on Keyboard
 */

public class Keyboard {

    private final String type;
    private final boolean isLighting;
    private final double weight;

    public Keyboard(String type, boolean isLighting, double weight) {
        this.type = type;
        this.isLighting = isLighting;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public boolean isLighting() {
        return isLighting;
    }

    public double getWeight() {
        return weight;
    }

    public String toString(){
        return "Клавитатура: \n" +
                "   Тип: " + type + "\n" +
                "   Есть подсветка: " + isLighting + "\n" +
                "   Вес: " + weight;
    }
}
