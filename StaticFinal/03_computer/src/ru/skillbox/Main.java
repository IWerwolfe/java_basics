package ru.skillbox;

public class Main {

    public static void main(String[] args) {

        Cpu iCore5 = new Cpu(3.4, 4, "Intel", 0.458);
        Ram adata = new Ram("DDR4", 4, 0.102);
        Storage samsung = new Storage(TypeStorage.SSD, 250, 0.152);
        Keyboard sven = new Keyboard("Desktop", false, 0.358);
        Display lg = new Display(15.2, TypeDisplay.IPS, 3.458);

        Cpu celeron = new Cpu(2.4, 2, "Intel", 0.358);
        Ram samsungRam = new Ram("DDR3", 2, 0.100);
        Storage seagete = new Storage(TypeStorage.HHD, 500, 0.552);
        Keyboard ryzen = new Keyboard("Desktop", true, 0.458);
        Display xiaomy = new Display(22.5, TypeDisplay.VA, 2.458);

        Computer middle = new Computer("Samsung",
                "Middle desktop",
                celeron,
                samsungRam,
                sven,
                seagete,
                lg);

        Computer premium = new Computer("Samsung",
                "Premium desktop",
                iCore5,
                adata,
                ryzen,
                samsung,
                xiaomy);

        Computer low = new Computer("Samsung",
                "Low desktop",
                celeron,
                samsungRam,
                sven,
                samsung,
                lg);

        System.out.println("Вес " + low.getName() +
                " от производителя " + low.getVendor() +
                " составляет " + low.getTotalWeight() + " кг \n");

        System.out.println(low.toString());
//        low.getStringInfo();

        System.out.println("Вес " + middle.getName() +
                " от производителя " + middle.getVendor() +
                " составляет " + middle.getTotalWeight() + " кг \n");

        System.out.println(middle.toString());
//        middle.getStringInfo();

        System.out.println("Вес " + premium.getName() +
                " от производителя " + premium.getVendor() +
                " составляет " + premium.getTotalWeight() + " кг \n");

        System.out.println(premium.toString());
//        premium.getStringInfo();

    }
}
