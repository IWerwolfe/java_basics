import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {
    private static HashMap<String, Account> accounts;
    private static final int operations = 1450;
    private static final int account = 150;
    private static ArrayList<String> forRandom = new ArrayList<>();
    private static final Random random = new Random();

    public static void main(String[] args) {

        createAccount(account);
        Bank bank = new Bank(accounts);

        for (int i = 1; i < operations; i++) {
         new Thread(() -> randomOperation(bank)).start();
        }
    }

    public static String getRandomAccount(){
        return forRandom.get((int) ((forRandom.size() - 2) * Math.random()));
    }

    public static void printBalance(String accountNum, long balance) {

        StringBuilder message = new StringBuilder();

        if (balance == -1) {
            message.append("Счет ")
                    .append(accountNum)
                    .append(" заблокирован. Для разблокировки обратитесь в банк");
        } else {
            message.append("Баланс счета ")
                    .append(accountNum)
                    .append(" составляет: ")
                    .append(balance)
                    .append(" руб.");
        }
        System.out.println(message);
    }

    public static void randomOperation(Bank bank) {

        if (random.nextBoolean()) {
            try {
                bank.transfer(getRandomAccount(), getRandomAccount(), Math.round(1 + 60000 * Math.random()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            String accountNum = getRandomAccount();
            printBalance(accountNum, bank.getBalance(accountNum));
        }
    }

    private static void getStatusAccounts() {
        accounts.forEach((s, account1) -> System.out.println(account1.toString()));
    }

    private static void createAccount(int count) {
        accounts = new HashMap<>();

        for (int i = 1; i < count; i++) {

            String numberAccount = String.valueOf(Math.round(10000 + 9999 * Math.random()));
            Account account = new Account(numberAccount, Math.round(0 + 99999 * Math.random()));

            accounts.put(numberAccount, account);
            forRandom.add(numberAccount);
        }
    }
}
