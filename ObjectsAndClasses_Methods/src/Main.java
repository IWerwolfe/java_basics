public class Main {

    public static void main(String... args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.print("Просто корзина");

        System.out.println();

        Basket vasyaBasket = new Basket();
        vasyaBasket.add("Milk", 40);
        vasyaBasket.add("Apple", 78, 2,1.45);
        vasyaBasket.add("Water", 15, 1.0);
        vasyaBasket.print("Корзина Васи");

        System.out.println();
        System.out.println("Арифметика \n");

        Arithmetic vasyaCalculator = new Arithmetic(32, 56);
        Arithmetic mashaCalculator = new Arithmetic(15, 10);
        Arithmetic petyaCalculator = new Arithmetic(32, 32);

        System.out.println(vasyaCalculator.getRezultArithmetic("Числа Васи"));
        System.out.println(mashaCalculator.getRezultArithmetic("Числа Маши"));
        System.out.println(petyaCalculator.getRezultArithmetic("Числа Пети"));
    }
}
