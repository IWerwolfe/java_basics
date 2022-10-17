package ru.skillbox;    /*
 *created by WerWolfe on Ram
 */

public class Ram {

    private final String type;
    private final int volume;
    private final double weight;

    public Ram(String type, int volume, double weight) {

        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public String toString(){
        return "Оперативной память: \n" +
                "   Тип: " + type + "\n" +
                "   Объем: " + volume + "\n" +
                "   Вес: " + weight;
    }
}
