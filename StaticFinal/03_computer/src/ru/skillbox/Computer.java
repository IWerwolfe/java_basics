package ru.skillbox;    /*
 *created by WerWolfe on Computer
 */

public class Computer {

    private final String vendor;
    private final String name;
    private Cpu cpu;
    private Ram ram;
    private Keyboard keyboard;
    private Storage storage;
    private Display display;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }

    public Computer(String vendor, String name, Cpu cpu, Ram ram, Keyboard keyboard, Storage storage, Display display) {
        this(vendor, name);
        this.cpu = cpu;
        this.ram = ram;
        this.keyboard = keyboard;
        this.storage = storage;
        this.display = display;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Storage getStorage() {
        return storage;
    }

    public Display getDisplay() {
        return display;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public double getTotalWeight(){
        return cpu.getWeight() + ram.getWeight() + display.getWeight() + keyboard.getWeight() + storage.getWeight();
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Информация о компьютере: \n" +
                "Название: " + getName() + "\n" +
                "Производитель: " + getVendor() + "\n" +
                cpu.toString() + "\n" +
                ram.toString()  + "\n" +
                display.toString()  + "\n" +
                keyboard.toString()  + "\n" +
                storage.toString() + "\n";
    }
}
