package ru.skillbox;    /*
 *created by WerWolfe on Cpu
 */

public class Cpu {

    private final double frequency;
    private final int countCore;
    private final String vendor;
    private final double weight;

    public Cpu(double frequency, int countCore, String vendor, double weight) {
        this.frequency = frequency;
        this.countCore = countCore;
        this.vendor = vendor;
        this.weight = weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getCountCore() {
        return countCore;
    }

    public String getVendor() {
        return vendor;
    }

    public double getWeight() {
        return weight;
    }

    public String toString(){
        return "Процессор: \n" +
                "   Производитель: " + vendor + "\n" +
                "   Частота: " + frequency + "\n" +
                "   Количество ядер: " + countCore + "\n" +
                "   Вес: " + weight;
     }
}
