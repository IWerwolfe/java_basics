public class Basket {

    private static int count = 0;
    private static int globalPrice = 0;
    private static int globalCountItems = 0;
    private String items = "";
    private int totalPrice = 0;
    private int limit;

    private double totalWeight = 0;

    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int getCount() {
        return count;
    }

    private static void setGlobalPriceAndGlobalCountItems(int count, int price){
        globalPrice = globalPrice + price;
        globalCountItems = globalCountItems + count;
    }

    public static double getMedialPriceBaskets(){
        return globalPrice / globalCountItems;
    }

    public static double getMedialPrice(){
        return globalPrice / count;
    }

    public static int getGlobalPrice() {
        return globalPrice;
    }

    public static int getGlobalCountItems() {
        return globalCountItems;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        totalWeight = totalWeight + count * weight;
    }

    public void add(String name, int price, double weight) {
        add(name, price, 1, weight);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
            count + " шт. - " + price;
        totalPrice = totalPrice + count * price;

        setGlobalPriceAndGlobalCountItems(count, price);
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
        if (getTotalWeight() > 0){
            System.out.println("Общий вес корзины: " + getTotalWeight());
        }
    }
}
