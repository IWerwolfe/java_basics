package ru.skillbox;    /*
 *created by WerWolfe on Storage
 */

public class Storage {

    private final TypeStorage typeStorage;
    private final int volume;
    private final double weight;

    public Storage(TypeStorage typeStorage, int volume, double weight) {
        this.typeStorage = typeStorage;
        this.volume = volume;
        this.weight = weight;
    }

    public TypeStorage getTypeStorage() {
        return typeStorage;
    }

    public int getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public String toString(){
        return "Диск: \n" +
                "   Тип: " + typeStorage + "\n" +
                "   Объем: " + volume + "\n" +
                "   Вес: " + weight;
    }
}
