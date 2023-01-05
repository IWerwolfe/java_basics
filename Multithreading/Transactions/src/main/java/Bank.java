import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    private Map<String, Account> accounts;
    private final Random random = new Random();

    public Bank(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {

        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        synchronized (this) {
            if (!checkAccounts(fromAccount, toAccount, amount)) {
                return;
            }
            fromAccount.setMoney(-amount);
            toAccount.setMoney(amount);
        }

        System.out.println("Перевод " + amount + " руб. с "
                + fromAccountNum + " на " + toAccountNum + " успешно произведен");

        if (amount > 50000 && isFraud(fromAccountNum, toAccountNum, amount)) {
            toAccount.setBlocked(true);
            fromAccount.setBlocked(true);
            System.out.println("Счета " + fromAccountNum + " и " + toAccountNum
                    + " заблокированны службой безопасности");
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        return account.isBlocked() ? -1 : account.getMoney();
    }

    public long getSumAllAccounts() {

        AtomicLong balance = new AtomicLong();
        accounts.forEach((s, account) -> balance.addAndGet(account.getMoney()));

        return balance.get();
    }

    private boolean checkAccounts(Account fromAccount, Account toAccount, long amount) {

        if (fromAccount.isBlocked()) {
            System.out.println("Счет отправителя платежа заблокирован");
            return false;
        }

        if (toAccount.isBlocked()) {
            System.out.println("Счет получателя платежа заблокирован");
            return false;
        }

        if (fromAccount.getMoney() < amount) {
            System.out.println("На счете отправителя недостаточно средств");
            return false;
        }

        return true;
    }
}
