import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        String regexName = "[А-Я]{1}[а-я]+\\s[А-Я]{1}[а-я]+";
        String regexMail = "([a-zA-Z0-9._-]+)@([a-zA-Z0-9._-]+)\\.([a-zA-Z0-9]{1,4})";
        String regexPhone = "^(\\+7)?\\d{10}$";

        if (components.length != 4) {
            throw new IllegalArgumentException("Некорректный формат, правильно: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        String email = components[INDEX_EMAIL];
        String phone = components[INDEX_PHONE];

        if (!name.matches(regexName)) {
            throw new IllegalArgumentException("Имя указано некорректно, правильно: " +
                    "Василий Петров");
        }
        if (!email.matches(regexMail)) {
            throw new IllegalArgumentException("E-mail указан некорректно, правильно: " +
                    "vasily.petrov@gmail.com");
        }
        if (!phone.matches(regexPhone)) {
            throw new IllegalArgumentException("Телефон указан некорректно, правильно: " +
                    "+79215637722");
        }
        storage.put(name, new Customer(name, phone, email));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}